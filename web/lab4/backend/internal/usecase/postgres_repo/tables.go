package postgres_repo

type Users struct {
	ID        int64  `gorm:"primaryKey;autoIncrement"`
	FirstName string `gorm:"not null"`
	LastName  string `gorm:"not null"`
	Username  string `gorm:"not null"`
	PhotoUrl  string `gorm:"not null"`
}
