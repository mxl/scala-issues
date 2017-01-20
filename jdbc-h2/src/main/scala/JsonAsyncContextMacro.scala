import scala.reflect.macros.whitebox.{Context => MacroContext}

class JsonAsyncContextMacro(val c: MacroContext) {

  import c.universe._

  def future(t: Tree): Tree =
    q"scala.concurrent.Future { $t }"

  def runQuerySingleAsync[T: WeakTypeTag](quoted: Tree): Tree =
    future(q"${c.prefix}.run($quoted)")

  def runQueryAsync[T: WeakTypeTag](quoted: Tree): Tree =
    future(q"${c.prefix}.run($quoted)")
}
