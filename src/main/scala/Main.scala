import io.getquill._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main extends App {
  val db = new MysqlAsyncContext[SnakeCase]("")

  import db._

  case class Session(sid: Int, uid: Int) extends Embedded

  case class TokenStorage(session: Session, selector: String)

  implicit val tokenStorageSchemaMeta =
    schemaMeta[TokenStorage]("token", _.session.sid -> "sessionId", _.session.uid -> "userId")

  def findBySelector(selector: String): Future[Option[TokenStorage]] =
    db.run {
      query[TokenStorage].filter(_.selector == lift(selector))
    }
      .map(_.headOption)
}
