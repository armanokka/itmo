package postgres_repo

import (
	"context"
	"database/sql"
	"github.com/armanokka/web_lab4/internal/entity"
)

func (c DB) GetUserByID(ctx context.Context, userID int64) (entity.User, error) {
	var user Users
	query := c.db.WithContext(ctx).Model(&Users{}).Where("id = ?", userID).Find(&user)
	if query.Error != nil {
		return entity.User{}, query.Error
	}
	if query.RowsAffected == 0 {
		return entity.User{}, sql.ErrNoRows
	}
	return entity.User{
		ID:        user.ID,
		FirstName: user.FirstName,
		LastName:  user.LastName,
		Username:  user.Username,
		PhotoUrl:  user.PhotoUrl,
	}, nil
}

func (c DB) CreateUser(ctx context.Context, user entity.User) error {
	return c.db.WithContext(ctx).Create(&Users{
		ID:        user.ID,
		FirstName: user.FirstName,
		LastName:  user.LastName,
		Username:  user.Username,
		PhotoUrl:  user.PhotoUrl,
	}).Error
}
