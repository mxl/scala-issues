// https://gitter.im/getquill/quill?at=589774ae6b2d8dd55204c380

import java.time._
import io.getquill._

object Main extends App {
  def liftNow: Option[LocalDateTime] = Option(LocalDateTime.now(ZoneOffset.UTC))

  trait QuillCoding {
    this: MysqlAsyncContext[_] =>
    implicit class LocalDateTimeCompare(left: Option[LocalDateTime]) {
      def >(right: Option[LocalDateTime]) = quote(infix"$left IS NULL OR $left > $right".as[Boolean])
    }
    implicit class Deletable[T <: { def deletedAt: Option[LocalDateTime] }](q: EntityQuery[T]) {
      def valid = quote(q.filter(x => x.deletedAt > lift(liftNow)))
    }
  }

  val db = new MysqlAsyncContext[SnakeCase]("") with QuillCoding
  import db._

  case class A(value: Long) extends AnyVal {
    def test = quote(query[Account].valid)
  }
  case class Account(id: A, deletedAt: Option[LocalDateTime])
}
