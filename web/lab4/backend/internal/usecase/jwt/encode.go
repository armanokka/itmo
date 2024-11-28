package jwt

import (
	"github.com/armanokka/web_lab4/internal/entity"
	"github.com/golang-jwt/jwt"
)

func (c Service) Encode(user entity.User) (string, error) {
	token := jwt.New(jwt.SigningMethodHS512)
	claims := token.Claims.(jwt.MapClaims)
	claims["id"] = user.ID
	claims["first_name"] = user.FirstName
	claims["last_name"] = user.LastName
	claims["username"] = user.Username
	claims["photo_url"] = user.PhotoUrl

	tokenString, err := token.SignedString([]byte(c.jwtSecretKey))
	if err != nil {
		return "", err
	}
	return tokenString, nil
}
