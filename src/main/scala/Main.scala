import io.getquill.{CassandraAsyncContext, Literal, MappedEncoding}

import scala.concurrent.{ExecutionContext, Future}

object Main extends App {
  type CustomString = CustomClass[String]

  trait CustomStringCodec {
    implicit val encoder: MappedEncoding[CustomString, String] = MappedEncoding[CustomString, String](_.value)
    implicit val decoder: MappedEncoding[String, CustomString] = MappedEncoding[String, CustomString](s => CustomClass.apply(s))
  }

  class TestDAL(val cassandra: CassandraAsyncContext[Literal]) extends CustomStringCodec {
    case class A(name: CustomString)
    import cassandra._

    def operations(rule: A, name: CustomString)(implicit ec: ExecutionContext): Future[Unit] = {
      run(query[A].insert(lift(rule))).map(_ => ())
      val deleteQ = quote(query[A].filter(c => c.name == lift(name)).delete)
      run(deleteQ)
    }

    def get(name: CustomString)(implicit ec: ExecutionContext): Future[List[A]] = {
      val q = quote(query[A].filter(o => o.name == lift(name)))
      run(q)
    }
  }
}
