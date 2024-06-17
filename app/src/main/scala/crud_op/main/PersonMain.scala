package crud_op.main

import crud_op.Entity.Person
import crud_op.Service.PersonServiceImpl

object PersonMain extends App {
 private val service = new PersonServiceImpl
/* for(i <- 21838 to 100000){
//  service.insert(Person(i,"Nanda",22))
 }*/
 val del = (1 to 23423).map(i => service.delete(Some(i)))
//service.personTable()
}
