class CustomClass[T] private (val value: T) extends AnyVal

object CustomClass {
  def apply[T](value: T) = new CustomClass[T](value)
}
