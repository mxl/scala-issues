// run sbt with -Ddatabase=jdbc-h2 or -Ddatabase=jdbc-mysql
object Main extends App {
  val ctx = new MyContext("ctx")
  import ctx._

  case class Entity(id: Int)

  ctx.run(query[Entity])
}
