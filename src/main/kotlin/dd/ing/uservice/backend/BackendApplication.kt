package dd.ing.uservice.backend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
    // runApplication<BackendApplication>(*args)
    SpringApplication.run(BackendApplication::class.java, *args)
}
