package andersen.dev.tickets.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.Getter;

@Component
public class ConnectionSupplier {
	@Getter
	private final Connection connection;

	public ConnectionSupplier(@Value("${datasource.url}") String URL, @Value("${datasource.username}") String username,
			@Value("${datasource.password}") String password, @Value("${datasource.driver-class-name}") String driver)
			throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		connection = DriverManager.getConnection(URL, username, password);
	}
}
