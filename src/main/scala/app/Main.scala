package app
import io.getquill._

import scala.concurrent.{ ExecutionContext, Future }

object Main extends App {
  case class Person(name: String)

  implicit val ctx = new PostgresAsyncContext(SnakeCase, "ctx")

  import ctx._

  def test(implicit ec: ExecutionContext): Future[Boolean] = {
    val q = quote {
      query[Person].nonEmpty
    }
    ctx.run(q)
  }

  import scala.concurrent.ExecutionContext.Implicits.global

  test
}
