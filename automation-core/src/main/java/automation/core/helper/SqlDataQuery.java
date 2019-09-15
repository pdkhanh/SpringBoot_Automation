package automation.core.helper;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SqlDataQuery {
  private final JdbcTemplate jdbcTemplate;

  public SqlDataQuery(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public <T> List<T> loadData(Class<T> dataType, String query, Object... params) {
    return jdbcTemplate.query(query, new BeanPropertyRowMapper(dataType));
  }
}
