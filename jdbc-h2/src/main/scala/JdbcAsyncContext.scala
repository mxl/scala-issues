import io.getquill.context.jdbc._

import scala.concurrent.Future
import scala.language.experimental.macros

trait JdbcAsyncContext {
  this: JdbcContext[_, _] =>

  def runAsync[T](quoted: Quoted[T]): Future[RunQuerySingleResult[T]] = macro JsonAsyncContextMacro.runQuerySingleAsync[T]

  def runAsync[T](quoted: Quoted[Query[T]]): Future[RunQueryResult[T]] = macro JsonAsyncContextMacro.runQueryAsync[T]

}
