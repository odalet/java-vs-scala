package com.chariotsolutions.jshepard;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public interface ResultSets {
  default LocalDateTime convertTimestampToLocalDateTime(Timestamp timestamp) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), ZoneId.systemDefault());
  }
}
