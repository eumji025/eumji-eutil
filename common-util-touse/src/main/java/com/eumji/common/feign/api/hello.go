package main

import (
	"fmt"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

func main() {
	app := mux.NewRouter().StrictSlash(true)
	app.HandleFunc("/hello/{name}", func(writer http.ResponseWriter, request *http.Request) {
		vars := mux.Vars(request)
		value := vars["name"]
		fmt.Printf("server receive :%s", value)
		writer.Write([]byte(`Hello,I'm Server'`))
	})
	err := http.ListenAndServe(":8081", app)
	if err != nil {
		log.Fatal("ListenAndServe", err)
	}
}
