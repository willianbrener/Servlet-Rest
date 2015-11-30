package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class deletarAluno extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String result = "erro";

		PrintWriter out = response.getWriter();

		String s1 = request.getParameter("cpf");
		System.out.println(s1);

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/universidade", "postgres",
					"postgres");
			PreparedStatement ps = con
					.prepareStatement("delete from aluno where cpf = ?");
			ps.setString(1, s1);
			ps.executeUpdate();
			con.close();
			result = "Delecao realizada";
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("UK_ALUNO_NOME")) {
				result = "Aluno não existe";
			}
		}

		out.println(result);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
