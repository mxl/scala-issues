import com.github.mauricio.async.db.pool.PartitionedConnectionPool
import com.github.mauricio.async.db.postgresql.PostgreSQLConnection
import com.typesafe.config.Config
import io.getquill._
import io.getquill.util.LoadConfig

class MyContext(pool: PartitionedConnectionPool[PostgreSQLConnection]) extends PostgresAsyncContext[SnakeCase](pool) with AsyncAsyncContext {
  def this(config: PostgresAsyncContextConfig) = this(config.pool)

  def this(config: Config) = this(PostgresAsyncContextConfig(config))

  def this(configPrefix: String) = this(LoadConfig(configPrefix))
}

object MyContext {
  val instance = new MyContext("ctx")
}