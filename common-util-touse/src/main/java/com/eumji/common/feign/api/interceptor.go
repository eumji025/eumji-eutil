package main

import (
	"encoding/base64"
	"fmt"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

func main() {
	app := mux.NewRouter().StrictSlash(true)
	app.HandleFunc("/hello/{name}", func(writer http.ResponseWriter, request *http.Request) {
		headers := request.Header
		auth := headers.Get("Authorization")
		fmt.Printf("server receive the Authorization is: %s\n", auth)
		length := len("Basic ")
		substr := auth[length:]
		fmt.Printf("after substring Authorization is : %s\n", substr)
		value, e := base64.StdEncoding.DecodeString(substr)
		if e != nil {
			fmt.Println("base64 ecode this value error")
		}
		fmt.Printf("the header of Authorization is :%s\n", value)
		vars := mux.Vars(request)
		value2 := vars["name"]
		fmt.Printf("server receive :%s\n", value2)
		writer.Write([]byte(`Hello,I'm Server'`))
	})
	err := http.ListenAndServe(":8081", app)
	if err != nil {
		log.Fatal("ListenAndServe", err)
	}
}
