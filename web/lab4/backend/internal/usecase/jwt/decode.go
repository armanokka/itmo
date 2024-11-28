package jwt

import (
	"fmt"
	"github.com/armanokka/web_lab4/internal/entity"
	"github.com/golang-jwt/jwt"
)

func (c Service) Decode(token string) (entity.User, error) {
	t, err := jwt.Parse(token, func(token *jwt.Token) (interface{}, error) {
		if _, ok := token.Method.(*jwt.SigningMethodHMAC); !ok {
			return nil, fmt.Errorf("there's an error with the signing method")
		}
		return []byte(c.jwtSecretKey), nil
	})
	if err != nil {
		return entity.User{}, err
	}

	claims, ok := t.Claims.(jwt.MapClaims)
	if !ok {
		return entity.User{}, fmt.Errorf("unable to extract claims")
	}
	return entity.User{
		ID:        int64(claims["id"].(float64)),
		FirstName: claims["first_name"].(string),
		LastName:  claims["last_name"].(string),
		Username:  claims["username"].(string),
		PhotoUrl:  claims["photo_url"].(string),
	}, nil
}
