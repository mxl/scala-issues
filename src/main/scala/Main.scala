import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

// run sbt with -Ddatabase=jdbc-h2 or -Ddatabase=jdbc-mysql
object Main extends App {
  val ctx = MyContext.instance
  import ctx._

  case class Entity(id: Int)

  Await.result(ctx.runAsync(query[Entity]), Duration.Inf)

}
