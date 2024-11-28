package entity

type User struct {
	ID        int64  // filled from Telegram
	FirstName string // filled from Telegram
	LastName  string // filled from Telegram
	Username  string // filled from Telegram
	AuthDate  int64  // filled from Telegram
	PhotoUrl  string // filled from Telegram
}

type Dots struct {
	FkUserID int64
	X        float64
	Y        float64
	R        int
}
