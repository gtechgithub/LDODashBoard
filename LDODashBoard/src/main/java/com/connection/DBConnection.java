package com.connection;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public interface DBConnection {
	public void setdataSource(DataSource dataSource);
}
