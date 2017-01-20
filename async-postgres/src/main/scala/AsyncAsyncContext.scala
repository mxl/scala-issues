import io.getquill.context.async.AsyncContext

import scala.language.experimental.macros

trait AsyncAsyncContext {
  this: AsyncContext[_, _, _] =>

  def runAsync[T](quoted: Quoted[T]): RunQuerySingleResult[T] = macro AsyncAsyncContextMacro.runQuerySingleAsync[T]

  def runAsync[T](quoted: Quoted[Query[T]]): RunQueryResult[T] = macro AsyncAsyncContextMacro.runQueryAsync[T]
}
