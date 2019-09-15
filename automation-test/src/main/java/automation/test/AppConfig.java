package automation.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import automation.core.annotation.EnablePageObjects;
import automation.core.helper.SqlDataQuery;

@SpringBootApplication
@EnablePageObjects
public class AppConfig {
  @Bean
  public SqlDataQuery sqlDataQuery(@Autowired JdbcTemplate jdbcTemplate) {
    return new SqlDataQuery(jdbcTemplate);
  }
}
