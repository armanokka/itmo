package postgres_repo

import (
	"fmt"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

type Config struct {
	User     string
	DB       string
	Password string
	Driver   string
	Host     string
	Port     int
}

type DB struct {
	db           *gorm.DB
	jwtSecretKey string
}

func New(cfg Config) (DB, error) {
	dsn := fmt.Sprintf("host=%s user=%s password=%s dbname=%s port=%d sslmode=disable TimeZone=Europe/Moscow",
		cfg.Host, cfg.User, cfg.Password, cfg.DB, cfg.Port)
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{
		SkipDefaultTransaction: true,
	})
	if err != nil {
		return DB{}, err
	}

	raw, err := db.DB()
	if err != nil {
		return DB{}, err
	}
	if err = raw.Ping(); err != nil {
		return DB{}, err
	}
	raw.SetMaxIdleConns(10)
	raw.SetMaxOpenConns(10)

	if err = db.AutoMigrate(&Users{}); err != nil {
		return DB{}, err
	}

	return DB{db: db}, nil
}
