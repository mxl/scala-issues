// https://gitter.im/getquill/quill?at=587ba728300f220a66ee8159

import io.getquill._

object Main extends App {
  val ctx = new JdbcContext[H2Dialect, Literal]("ctx")
  import ctx._

  case class Person(name: String, age: Int)

  val q = quote {
    query[Person].map(c => c.name == "Rich")
  }

  ctx.run(q)
}
