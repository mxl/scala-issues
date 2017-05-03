// http://stackoverflow.com/q/43749680/746347

import io.getquill._

object Main extends App {
  val db = new PostgresJdbcContext[SnakeCase]("db")

  import db._

  case class Entity(name: String)

  db.run(
    for {
      e <- query[Entity]
    } yield e.name
  ).map(Some(_))
}
