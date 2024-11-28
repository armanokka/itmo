package http

import (
	"github.com/armanokka/web_lab4/internal/usecase"
	"github.com/gin-gonic/gin"
	"github.com/gorilla/websocket"
)

type Controller struct {
	botToken string // required for telegram auth payload check
	jwt      usecase.JWTTokenService
	router   *gin.RouterGroup
	repo     usecase.Repo
	log      usecase.Logger
	//users    map[int64]entity.User      // map[user id]user
	clients map[string]*websocket.Conn // map[ip]conn
	dots    []Message
}

func MapHandlers(router *gin.RouterGroup, repo usecase.Repo, jwt usecase.JWTTokenService, log usecase.Logger, botToken string) {
	c := Controller{router: router, repo: repo, botToken: botToken, jwt: jwt, log: log, clients: make(map[string]*websocket.Conn, 2)}
	router.Use(c.Middleware)
	router.POST("/telegram-auth", c.LoginHandler)
	router.GET("/ws", c.WebsocketHandler)
}
