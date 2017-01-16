// https://gitter.im/getquill/quill?at=587bfec4300f220a66f020e3

import io.getquill._
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
  val ctx = new PostgresAsyncContext[SnakeCase]("ctx")

  import ctx._

  case class Entity1(id: Int, name: Option[String])

  case class Entity2(entity1Id: Int, name: String)

  ctx.run(
    query[Entity1]
      .leftJoin(query[Entity2])
      .on(_.id == _.entity1Id)
      .filter(t => t._1.name == t._2.map(_.name))
  )
}
