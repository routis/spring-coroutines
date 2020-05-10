package routis.sample.springcoroutines

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@SpringBootApplication
@EnableWebFlux
class SpringCoroutinesApplication

fun main(args: Array<String>) {
	runApplication<SpringCoroutinesApplication>(*args)
}

