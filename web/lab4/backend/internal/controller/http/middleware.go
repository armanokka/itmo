package http

import (
	"github.com/gin-gonic/gin"
)

func (app Controller) Middleware(c *gin.Context) {
	c.Next()

	err := c.Errors.Last()
	if err == nil {
		return
	}
	c.Header("Content-type", "application/json")
	c.JSON(-1, ResponseError{Error: err.Error()})
}
