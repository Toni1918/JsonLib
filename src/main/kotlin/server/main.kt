package server
import server.start
import controller.Controller


fun main() {
    // Inicia o servidor na porta 8080 com o Controller
    start(8080, listOf(Controller()))
}
