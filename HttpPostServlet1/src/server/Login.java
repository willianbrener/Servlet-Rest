package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		//ObjectOutputStream out = new ObjectOutputStream(
		//		response.getOutputStream());
		PrintWriter out = response.getWriter();

		String n = request.getParameter("usuario");
		String p = request.getParameter("senha");
		System.out.println(n);
		System.out.println(p);

		if (validate(n, p)) {
			//out.writeObject("success");
			out.println("success");

		} else {
			//out.writeObject("Usuario ou Senha esta incorreto!");
			out.println("Usuario ou Senha esta incorreto!");

		}

		out.close();
	}

	public static boolean validate(String nome, String senha) {
		boolean status = false;
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/lan_manager", "postgres",
					"postgres");

			PreparedStatement ps = con
					.prepareStatement("select nome, password from usuario where nome=?");
			ps.setString(1, nome);

			ResultSet rs = ps.executeQuery();
			status = rs.next();
			if (status) {
				String _senha = rs.getString(2);
				if (_senha.equalsIgnoreCase(senha)) {
					status = true;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}
}
