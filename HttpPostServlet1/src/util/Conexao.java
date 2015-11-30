package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection fabricar() {

		try {
			Class.forName("org.postgresql.Driver").newInstance();
			System.out.println("Conexão aberta!");
			return DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/universidade",
					"postgres", "postgres");
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}