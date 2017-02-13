package macros

import scala.reflect.macros.whitebox.{Context => MacroContext}

class PredicateMacro(val c: MacroContext) {

  import c.universe._

  def filter(f: c.Tree): c.Tree =
    q"""run(query[Foo].filter($f))"""

}
