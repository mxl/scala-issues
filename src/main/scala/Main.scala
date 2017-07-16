import io.getquill.{CassandraAsyncContext, Literal}

object Main extends App {
  type CustomString = CustomClass[String]

  class TestDAL(val cassandra: CassandraAsyncContext[Literal]) {

    import cassandra._

    implicit val decodeCustomString = MappedEncoding[String, CustomString](s => CustomClass.apply(s))

    // fails because `LowPriorityImplicits.materializeDecoder` can not provide decoder for `CustomClass` `AnyVal` with private property
    // and `EncodingDsl.mappedDecoder` with `decodeCustomString` is not used at all
    // defining decoder explicitly:
    // implicit val decoder = mappedDecoder(decodeCustomString, stringDecoder)
    // solves the issue
    implicitly[Decoder[CustomString]]
  }

}
