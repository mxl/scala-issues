// http://stackoverflow.com/questions/41684944/json4s-parsing-fails-for-simple-string-value

import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.JsonDSL._

object Main extends App {
  parse(compact(render("bla-b")))
  compact(render("bla-b"))
  parse(compact(render("joco" -> "bla-b")))
}
