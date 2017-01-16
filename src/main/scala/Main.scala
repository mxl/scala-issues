// https://gitter.im/getquill/quill?at=587c64b8dec171b811f41454

import io.getquill._
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
  val ctx = new PostgresAsyncContext[SnakeCase]("ctx")

  import ctx._

  case class Entity(name: String)

  def filter(): Unit = {
    val _ = ctx.run(query[Entity].withFilter(_.name == lift("")))
  }

  def filter(name: String): Unit = {
    val _ = ctx.run(query[Entity].withFilter(_.name == lift(name)))
  }

//  def filter(predicate: Entity => Boolean): Unit = {
//    val _ = ctx.run(query[Entity].withFilter(predicate(_)))
//  }

  filter()
  filter("")
//  filter(_.name == lift(""))
}
