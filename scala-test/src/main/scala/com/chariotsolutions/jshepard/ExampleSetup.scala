package com.chariotsolutions.jshepard

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository("exampleSetup")
class ExampleSetup {
  private var jdbcTemplate : JdbcTemplate = _

  def initializeData() {
    jdbcTemplate.execute("create table name (id int primary key, last_name varchar(100) not null, first_name varchar(100) not null, middle_name varchar(100), last_update timestamp not null);")
    jdbcTemplate.execute("insert into name  values (1, 'aLastname1', 'firstname1', null, localtimestamp);")
    jdbcTemplate.execute("insert into name  values (2, 'aLastname2', 'firstname2', 'middlename1', localtimestamp);")
    jdbcTemplate.execute("insert into name  values (3, 'bLastname1', 'firstname3', null, localtimestamp);")
    jdbcTemplate.execute("insert into name  values (4, 'bLastname1', 'firstname4', 'middlename2', localtimestamp);")
  }

  @Autowired
  def setJdbcTemplate(jdbcTemplate: JdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate
  }
}

