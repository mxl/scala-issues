import scala.reflect.macros.whitebox.{Context => MacroContext}

class AsyncAsyncContextMacro(val c: MacroContext) {

  import c.universe._

  def runQuerySingleAsync[T: WeakTypeTag](quoted: Tree): Tree =
    q"${c.prefix}.run($quoted)"

  def runQueryAsync[T: WeakTypeTag](quoted: Tree): Tree =
    q"${c.prefix}.run($quoted)"
}
