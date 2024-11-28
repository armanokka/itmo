package jwt

type Service struct {
	jwtSecretKey string
}

func NewService(jwtSecretKey string) Service {
	return Service{jwtSecretKey: jwtSecretKey}
}
