import eu.timepit.refined.api.Refined
import eu.timepit.refined.collection.{MaxSize, MinSize}
import eu.timepit.refined._
import eu.timepit.refined.boolean.AllOf
import shapeless.{::, HNil}
import shapeless.nat._

package object app {
  private type minStringSize = MinSize[_4]
  private type maxStringSize = MaxSize[W.`255`.T]
  type CustomStringV = AllOf[minStringSize :: maxStringSize :: HNil]
  type CustomString = Refined[String, CustomStringV]
}
