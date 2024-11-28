package utils

import (
	"crypto/hmac"
	"crypto/sha256"
	"encoding/hex"
)

func EncodeSHA256(s string) []byte {
	h := sha256.New()
	h.Write([]byte(s))
	return h.Sum(nil)
}

func EncodeHMAC_SHA256(message, key []byte) string {
	h := hmac.New(sha256.New, key)
	h.Write(message)
	return hex.EncodeToString(h.Sum(nil))
}
