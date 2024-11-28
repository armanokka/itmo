package http

type ResponseToken struct {
	Token string `json:"token"`
}

type ResponseError struct {
	Error string `json:"error"`
}

type TelegramLoginPayload struct {
	ID        int64  `json:"id"`
	FirstName string `json:"first_name"`
	LastName  string `json:"last_name"`
	Username  string `json:"username"`
	PhotoURL  string `json:"photo_url"`
	AuthDate  int64  `json:"auth_date"` // timestamp
	Hash      string `json:"hash"`
}

type Message struct {
	X   float64 `json:"x"`
	Y   float64 `json:"y"`
	Hit bool    `json:"hit"`
}

type ShortUserInfo struct {
	FirstName string `json:"first_name"`
	LastName  string `json:"last_name"`
	Username  string `json:"username"`
	PhotoUrl  string `json:"photo_url"`
}

type Reply struct {
	ID        int64 `json:"id"`
	Timestamp int64 `json:"timestamp"`
	Message
	ShortUserInfo
}
