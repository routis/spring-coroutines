package routis.sample.springcoroutines

import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

data class Task(val id: Int, val name: String)

interface TaskRepo {

    fun findById(id: Int): Mono<Task>


}

@Component
class DummyTaskRepo : TaskRepo {
    override fun findById(id: Int): Mono<Task> = when (id) {
        100 -> Mono.just(Task(id, "One hundred"))
        else -> Mono.empty()
    }
}

@Component
class TaskHandler(private val taskRepo: TaskRepo) {

    suspend fun findOne(req: ServerRequest): ServerResponse =
            Mono.just(req.pathVariable("id").toInt())
                    .flatMap { id -> taskRepo.findById(id) }
                    .flatMap { task -> ServerResponse.ok().json().bodyValue(task) }
                    .switchIfEmpty { ServerResponse.notFound().build() }
                    .awaitSingle()


}