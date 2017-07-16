import eu.timepit.refined.api.Refined
import io.getquill.{CassandraAsyncContext, Literal, MappedEncoding}
import scala.concurrent.{ExecutionContext, Future}

object Main extends App {
  trait CustomStringCodec {
    import app._

    implicit val decoder: MappedEncoding[CustomString, String] = MappedEncoding[CustomString, String](bname => bname.value)
    implicit val encoder: MappedEncoding[String, CustomString] = MappedEncoding[String, CustomString](bname => Refined.unsafeApply(bname))
  }

  class TestDAL(val cassandra: CassandraAsyncContext[Literal]) extends CustomStringCodec {
    import app._
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
