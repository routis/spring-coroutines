package routis.sample.springcoroutines

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Routes(private val taskHandler: TaskHandler) {

    @Bean
    fun route() = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/tasks/{id}",taskHandler::findOne)
        }
    }
}