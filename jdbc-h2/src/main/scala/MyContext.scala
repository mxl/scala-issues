import java.io.Closeable
import javax.sql.DataSource

import com.typesafe.config.Config
import io.getquill._
import io.getquill.util.LoadConfig

class MyContext(dataSource: DataSource with Closeable) extends H2JdbcContext[SnakeCase](dataSource) with JdbcAsyncContext {
  def this(config: JdbcContextConfig) = this(config.dataSource)
  def this(config: Config) = this(JdbcContextConfig(config))
  def this(configPrefix: String) = this(LoadConfig(configPrefix))
}

object MyContext {
  val instance = new MyContext("ctx")
}
