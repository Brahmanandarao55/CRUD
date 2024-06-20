package crud_op

import crud_op.Entity.Person
import crud_op.Repository.PersonRepoImpl
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{times, verify, when}
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.mockito.MockitoSugar

import java.sql.{Connection, PreparedStatement, ResultSet, SQLException, Statement}


class UnitTest2  extends FlatSpec with Matchers with MockitoSugar {
  "PersonRepoImpl" should "create person table successfully" in {
    val connection = mock[Connection]
    val statement = mock[Statement]
    when(connection.createStatement()).thenReturn(statement)

    val repo = new PersonRepoImpl {
      override def jdbcConnection(): Connection = connection
    }

    when(statement.executeUpdate(any[String])).thenReturn(1)
    noException should be thrownBy repo.createPerson()
//    val x = noException should be thrownBy repo.createPerson()
//    ScalaReflectionException should be thrownBy repo.createPerson()
    verify(statement).executeUpdate(any[String])
    verify(connection).close()

  }

  it should "insert person successfully" in {
    val connection = mock[Connection]
    val preparedStatement = mock[PreparedStatement]
    when(connection.prepareStatement(any[String])).thenReturn(preparedStatement)

    val repo = new PersonRepoImpl {
      override def jdbcConnection(): Connection = connection
    }

    val person = Person(1, "John Doe", 25)
    when(preparedStatement.executeUpdate()).thenReturn(1)
    noException should be thrownBy repo.insertPerson(person)
    verify(preparedStatement).setInt(1, person.Id)
    verify(preparedStatement).setString(2, person.Name)
    verify(preparedStatement).setInt(3, person.Age)
    verify(preparedStatement).executeUpdate()
    verify(connection).close()
  }

  it should "get person successfully" in {
    val connection = mock[Connection]
    val preparedStatement = mock[PreparedStatement]
    val resultSet = mock[ResultSet]
    when(connection.prepareStatement(any[String])).thenReturn(preparedStatement)
    when(preparedStatement.executeQuery()).thenReturn(resultSet)
    when(resultSet.next()).thenReturn(true, false)
    when(resultSet.getInt("ID")).thenReturn(1)
    when(resultSet.getString("NAME")).thenReturn("John Doe")
    when(resultSet.getInt("AGE")).thenReturn(25)


    val repo = new PersonRepoImpl {
      override def jdbcConnection(): Connection = connection
    }

    noException should be thrownBy repo.getPerson(Some(1))
    verify(preparedStatement).setInt(1, 1)
    verify(preparedStatement).executeQuery()
    verify(resultSet, times(2)).next()
    verify(connection).close()
  }

  it should "update person successfully" in {
    val connection = mock[Connection]
    val preparedStatement = mock[PreparedStatement]
    when(connection.prepareStatement(any[String])).thenReturn(preparedStatement)

    val repo = new PersonRepoImpl {
      override def jdbcConnection(): Connection = connection
    }

    val person = Person(1, "John Doe", 30)
    when(preparedStatement.executeUpdate()).thenReturn(1)
    noException should be thrownBy repo.updatePerson(person)
    verify(preparedStatement).setString(1, person.Name)
    verify(preparedStatement).setInt(2, person.Age)
    verify(preparedStatement).setInt(3, person.Id)
    verify(preparedStatement).executeUpdate()
    verify(connection).close()
  }

  it should "delete person successfully" in {
    val connection = mock[Connection]
    val preparedStatement = mock[PreparedStatement]
    when(connection.prepareStatement(any[String])).thenReturn(preparedStatement)

    val repo = new PersonRepoImpl {
      override def jdbcConnection(): Connection = connection
    }

    when(preparedStatement.executeUpdate()).thenReturn(1)
    noException should be thrownBy repo.deletePerson(Some(1))
    verify(preparedStatement).setInt(1, 1)
    verify(preparedStatement).executeUpdate()
    verify(connection).close()
  }

  it should "fetch all persons successfully" in {
    val connection = mock[Connection]
    val statement = mock[Statement]
    val resultSet = mock[ResultSet]
    when(connection.createStatement()).thenReturn(statement)
    when(statement.executeQuery(any[String])).thenReturn(resultSet)
    when(resultSet.next()).thenReturn(true, false)
    when(resultSet.getInt("ID")).thenReturn(1)
    when(resultSet.getString("NAME")).thenReturn("John Doe")
    when(resultSet.getInt("AGE")).thenReturn(25)

    val repo = new PersonRepoImpl {
      override def jdbcConnection(): Connection = connection
    }

    noException should be thrownBy repo.personTable()
    verify(statement).executeQuery(any[String])
    verify(resultSet, times(2)).next()
    verify(connection).close()
  }

}
