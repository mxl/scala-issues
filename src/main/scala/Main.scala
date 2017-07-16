import io.getquill.{Literal, MirrorContext, MirrorIdiom}

object Main extends App {
  class CustomString private (val value: String) extends AnyVal

  object CustomString {
    def apply(value: String) = new CustomString(value)
  }

  val ctx = new MirrorContext[MirrorIdiom, Literal]

  import ctx._

  implicit val decodeCustomString = MappedEncoding[String, CustomString](s => CustomString.apply(s))

  // fails because `LowPriorityImplicits.materializeDecoder` can not provide decoder for `CustomClass` `AnyVal` with private property
  // and `EncodingDsl.mappedDecoder` with `decodeCustomString` is not used at all
  // defining decoder explicitly:
  // implicit val decoder = mappedDecoder(decodeCustomString, stringDecoder)
  // solves the issue
  implicitly[Decoder[CustomString]]

}
