package http

import (
	"database/sql"
	"encoding/json"
	"errors"
	"fmt"
	"github.com/armanokka/web_lab4/internal/entity"
	"github.com/armanokka/web_lab4/internal/helpers"
	"github.com/gin-gonic/gin"
)

func (app Controller) LoginHandler(c *gin.Context) {
	var payload TelegramLoginPayload
	if err := json.NewDecoder(c.Request.Body).Decode(&payload); err != nil {
		c.AbortWithError(400, fmt.Errorf("invalid body"))
		return
	}

	user := entity.User{
		ID:        payload.ID,
		FirstName: payload.FirstName,
		LastName:  payload.LastName,
		Username:  payload.Username,
		AuthDate:  payload.AuthDate,
		PhotoUrl:  payload.PhotoURL,
	}

	if !helpers.VerifyTelegramAuth(user, payload.Hash, app.botToken) { // hash did not match
		c.AbortWithError(403, fmt.Errorf("invalid authorization"))
		return
	}

	// Get user by id to ensure he exists. If not found, create it
	if _, err := app.repo.GetUserByID(c, payload.ID); err != nil {
		if !errors.Is(err, sql.ErrNoRows) {
			c.AbortWithError(500, err)
			return
		}
		if err = app.repo.CreateUser(c, user); err != nil {
			c.AbortWithError(500, err)
			return
		}
	}

	token, err := app.jwt.Encode(user)
	if err != nil {
		c.AbortWithError(500, err)
		return
	}

	//c.SetCookie("Authorization", token, 60*60*24*7, "/", c.Request.Host, false, false) // because domain is localhost this

	c.JSON(200, ResponseToken{Token: token})
}
