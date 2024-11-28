package app

import (
	"errors"
	"github.com/armanokka/web_lab4/config"
	http2 "github.com/armanokka/web_lab4/internal/controller/http"
	"github.com/armanokka/web_lab4/internal/usecase"
	"github.com/armanokka/web_lab4/internal/usecase/jwt"
	"github.com/armanokka/web_lab4/internal/usecase/postgres_repo"
	"github.com/armanokka/web_lab4/pkg/logger"
	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"net/http"
	"strconv"
)

const (
	ctxTimeout = 5
)

func Run(cfg *config.Config) error {
	// Creating context that stops on os.Interrupt, SIGINT, SIGQUIT
	//ctx, cancel := context.WithCancel(context.Background())
	//c := make(chan os.Signal, 1)
	//signal.Notify(c, os.Interrupt, syscall.SIGINT, syscall.SIGQUIT)
	//defer signal.Stop(c)
	//go func() {
	//	<-c
	//	cancel()
	//}()

	// Initializing logger
	log := logger.NewApiLogger(&cfg.Logger)
	log.InitLogger()

	db, err := postgres_repo.New(postgres_repo.Config{
		User:     cfg.Postgres.User,
		DB:       cfg.Postgres.DB,
		Password: cfg.Postgres.Password,
		Driver:   cfg.Postgres.Driver,
		Host:     cfg.Postgres.Host,
		Port:     cfg.Postgres.Port,
	})
	if err != nil {
		panic(err)
	}

	router := gin.Default()
	if cfg.Logger.Level == "debug" {
		gin.SetMode(gin.DebugMode)
	}
	router.Use(cors.New(cors.Config{
		AllowAllOrigins:        true,
		AllowMethods:           []string{"*"},
		AllowPrivateNetwork:    true,
		AllowHeaders:           []string{"*"},
		AllowCredentials:       true,
		ExposeHeaders:          []string{"*"},
		AllowWildcard:          true,
		AllowBrowserExtensions: true,
		AllowWebSockets:        true,
	}))

	jwtService := jwt.NewService(cfg.Server.JWTSecretKey)

	http2.MapHandlers(router.Group("/api"), usecase.Repo(db), jwtService, usecase.Logger(log), cfg.Bot.Token)

	if err = router.Run(":" + strconv.Itoa(cfg.Server.Port)); err != nil && !errors.Is(err, http.ErrServerClosed) {
		log.Fatalf("Error starting Server: ", err)
	}

	log.Info("Server exited successfully")
	return nil
}
