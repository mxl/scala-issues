

object Main extends App {

  class Concrete2Scala extends ConcreteJava {
    //    override protected def test(c: Class[_]) = super.test(c)
  }


  class ConcreteScala extends RenameJava {
    override protected def renameTest(c: Class[_]) = {
      println("ConcreteScala")
      concreteTest(c)
    }
  }

  def test[T](o: AbstractJava[T], c: Class[T]) =
    o.test(c)

  test(new ConcreteScala(), classOf[Object])

}
