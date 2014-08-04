package com.chariotsolutions.jshepard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExampleRepository implements ResultSets {
  private JdbcTemplate jdbcTemplate;

  private final RowMapper<Name> rowMapper = (rs, rowNum) -> new Name(
      rs.getInt(1),
      rs.getString(2),
      rs.getString(3),
      Optional.ofNullable(rs.getString(4)),
      convertTimestampToLocalDateTime(rs.getTimestamp(5))
  );

  public List<Name> getAllNames() {
    return jdbcTemplate.query("select * from name", rowMapper);
  }

  public List<String> getAllFirstNames() {
    return jdbcTemplate.query("select first_name from name", (rs, rowNum) -> rs.getString(1));
  }

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }
}
