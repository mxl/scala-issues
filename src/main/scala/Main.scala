// http://stackoverflow.com/q/43749680/746347

object Main extends App {

  import io.getquill._

  val db = new MirrorContext[MirrorSqlDialect,SnakeCase]
  import db._

  case class Contact(phone: String) extends Embedded
  case class Person(id: Int, contact: Contact)

  implicit val personSchemaMeta = schemaMeta[Person]("Person", _.id -> "personId", _.contact.phone -> "homePhone")

  def update(phone: String) = quote {
    query[Person].filter(_.id == 1).update(_.contact.phone -> lift(phone))
  }

  println(db.run(update("")).string)
}
