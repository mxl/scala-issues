object Main extends App {
  import org.json4s._
  import org.json4s.native.JsonMethods._

  val json = """{ "key": {"a": true, "b": 123}, "value": [1,2,3,4] }"""

  case class Key(a: Boolean, b: Int)
  case class Event(key: Key, value: JValue)

  case class EventKey(key: Key)
  case class EventValue(value: Seq[Int])

  implicit val formats = DefaultFormats

  val parsedJson = parse(json)

  val event = parsedJson.extract[Event]
  val value = event.value.extract[Seq[Int]]

  val eventKey = parsedJson.extract[EventKey]
  val eventValue = parsedJson.extract[EventValue]

}
