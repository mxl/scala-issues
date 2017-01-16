// https://gitter.im/getquill/quill?at=587bfec4300f220a66f020e3

import io.getquill._

object Main extends App {
  val ctx = new PostgresAsyncContext[SnakeCase]("ctx")

  import ctx._
}
