package bj.nazonhou.quickstart;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.java.Log;

@SpringBootApplication
@Log
public class QuickstartApplication implements CommandLineRunner {

	private final JdbcTemplate jdbcTemplate;
	private final DataSource dataSource;

	public QuickstartApplication(JdbcTemplate jdbcTemplate, DataSource dataSource) {
		this.jdbcTemplate = jdbcTemplate;
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(QuickstartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Datasource = " + dataSource.toString());
		jdbcTemplate.execute("SELECT 1");
	}

}
