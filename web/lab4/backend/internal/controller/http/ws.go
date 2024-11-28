package http

import (
	"encoding/json"
	"errors"
	"fmt"
	"github.com/armanokka/web_lab4/internal/helpers"
	"github.com/gin-gonic/gin"
	"github.com/gorilla/websocket"
	"io"
	"net/http"
	"sync"
	"sync/atomic"
	"time"
)

var upgrader = websocket.Upgrader{
	CheckOrigin: func(r *http.Request) bool {
		return true
	},
} // use default options
var mu sync.Mutex

var counter atomic.Int64

func (app Controller) WebsocketHandler(c *gin.Context) {
	conn, err := upgrader.Upgrade(c.Writer, c.Request, nil)
	if err != nil {
		app.log.Debugf("upgrade: %s", err)
		return
	}
	defer conn.Close()

	// Generate a unique ID for the client
	clientID := conn.RemoteAddr().String()
	app.log.Debugf("Client connected: %s", clientID)

	// Checking authoziation
	authorization := c.Query("token")
	if authorization == "" {
		conn.WriteMessage(websocket.TextMessage, []byte("no token provided"))
		c.AbortWithError(403, fmt.Errorf("no token provided"))
		return
	}

	user, err := app.jwt.Decode(authorization)
	if err != nil {
		conn.WriteMessage(websocket.TextMessage, []byte("invalid token"))
		c.AbortWithError(403, fmt.Errorf("invalid token"))
		return
	}

	// Add client to client list
	mu.Lock()
	app.clients[clientID] = conn
	mu.Unlock()

	for {
		_, message, err := conn.ReadMessage()
		if err != nil {
			if !errors.Is(err, io.EOF) { // it's not a casual connection close
				app.log.Errorf("read: %s", err)
			}
			break
		}
		app.log.Debugf("Client %s sent message: %s", clientID, string(message))

		var payload Message
		if err = json.Unmarshal(message, &payload); err != nil {
			conn.WriteMessage(websocket.TextMessage, []byte("invalid message"))
			continue
		}

		counter.Add(1)

		reply, err := json.Marshal(Reply{
			ID: counter.Load(),
			Message: Message{
				X:   payload.X,
				Y:   payload.Y,
				Hit: helpers.IsInArea(payload.X, payload.Y),
			},
			ShortUserInfo: ShortUserInfo{
				FirstName: user.FirstName,
				LastName:  user.LastName,
				Username:  user.Username,
				PhotoUrl:  user.PhotoUrl,
			},
			Timestamp: time.Now().Unix(),
		})
		if err != nil {
			conn.WriteMessage(websocket.TextMessage, []byte("error when marshalling reply :("))
			continue
		}
		for _, peer := range app.clients {
			peer.WriteMessage(websocket.TextMessage, reply) // send message to all other clients
		}
	}

	mu.Lock()
	delete(app.clients, clientID)
	mu.Unlock()
	app.log.Debugf("Client disconnected: %s", clientID)
}
