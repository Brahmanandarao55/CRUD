package crud_op.Service
import crud_op.Entity.Person
trait PersonService {
  def insert(person:Person):Unit
  def get(id:Option[Int]):Unit
  def update(person: Person):Unit
  def delete(id:Option[Int]):Unit
  def personTable():Unit

}
