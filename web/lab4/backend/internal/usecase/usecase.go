package usecase

import (
	"context"
	"github.com/armanokka/web_lab4/internal/entity"
)

type Repo interface {
	GetUserByID(ctx context.Context, userID int64) (entity.User, error)
	CreateUser(ctx context.Context, user entity.User) error
}

type JWTTokenService interface {
	Encode(user entity.User) (string, error)
	Decode(jwt string) (entity.User, error)
}

type Logger interface {
	Debug(args ...interface{})
	Debugf(template string, args ...interface{})
	Info(args ...interface{})
	Infof(template string, args ...interface{})
	Warn(args ...interface{})
	Warnf(template string, args ...interface{})
	Error(args ...interface{})
	Errorf(template string, args ...interface{})
	DPanic(args ...interface{})
	DPanicf(template string, args ...interface{})
	Fatal(args ...interface{})
	Fatalf(template string, args ...interface{})
}
