package crud_op.main

import crud_op.Entity.Person
import crud_op.Service.PersonServiceImpl

import java.io.File
import scala.io.Source

object PersonMain extends App {

 val filepath = "C:\\Users\\Brahmananda Rao\\Desktop\\TASK\\CRUD_OP\\app\\src\\main\\scala\\crud_op\\main\\data.csv"
/* val bufferedSource = Source.fromFile(new File(filepath))
 val lines = bufferedSource.getLines().drop(1)
 val person = lines.map { line =>
  val fields = line.split(",").map(_.trim)
  Person(fields(0).toInt, fields(1), fields(2).toInt)
 }.toList*/
 private val service = new PersonServiceImpl

 service.insert(filepath)
// println(person)

}

