import io.getquill._
import macros.PredicateMacro
import scala.language.experimental.macros

object Main extends App {
  case class Foo(id: Int)

  val ctx = new MysqlJdbcContext[SnakeCase]("")
  import ctx._

  def getFoo(f: Foo => Boolean): Seq[Foo] = macro PredicateMacro.filter


  getFoo(_.id == 1)
}
