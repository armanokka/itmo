package main

import (
	"github.com/armanokka/web_lab4/config"
	"github.com/armanokka/web_lab4/internal/app"
)

func main() {
	cfg, err := config.NewConfig()
	if err != nil {
		panic(err)
	}

	if err = app.Run(cfg); err != nil {
		panic(err)
	}
}
