package com.chariotsolutions.jshepard

import java.time.LocalDateTime

import com.chariotsolutions.jshepard.JdbcTemplateUtils._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

case class Name(id : Int, lastName : String, firstName : String, middleName : Option[String], lastUpdate : LocalDateTime)

@Repository("exampleRepository")
class ExampleRepository() extends JdbcTemplateUtils {
  val queryAllNames = query("select * from name")

  def getAllNames: List[Name] = {
    query("select * from name") { (rs, rowNum) =>
      Name(
        rs.getInt(1),
        rs.getString(2),
        rs.getString(3),
        Option(rs.getString(4)),
        rs.getLocalDateTime(5))
    }
  }

  def getAllFirstNames: List[String] = {
    queryAllNames { (rs, rowNum) => rs.getString(3) }
  }

  @Autowired
  def setJdbcTemplate(jdbcTemplate: JdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate
  }
}
