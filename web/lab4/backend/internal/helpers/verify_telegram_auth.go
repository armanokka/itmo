package helpers

import (
	"github.com/armanokka/web_lab4/internal/entity"
	"github.com/armanokka/web_lab4/pkg/utils"
	"sort"
	"strconv"
	"strings"
)

// VerifyTelegramAuth verifies the hash from the Telegram auth widget.
func VerifyTelegramAuth(user entity.User, hash, botToken string) bool {
	s := []string{"id=" + strconv.FormatInt(user.ID, 10),
		"first_name=" + user.FirstName,
		"last_name=" + user.LastName,
		"username=" + user.Username,
		"auth_date=" + strconv.FormatInt(user.AuthDate, 10),
		"photo_url=" + user.PhotoUrl}
	sort.Strings(s)
	dataCheckString := strings.Join(s, "\n")
	return utils.EncodeHMAC_SHA256([]byte(dataCheckString), utils.EncodeSHA256(botToken)) == hash
}
