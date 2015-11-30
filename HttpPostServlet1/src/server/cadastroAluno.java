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

import util.Conexao;

@SuppressWarnings("serial")
public class cadastroAluno extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String result = "erro";

		PrintWriter out = response.getWriter();

		String s1 = request.getParameter("cpf");
		String s2 = request.getParameter("nome");
		String s3 = request.getParameter("data_nascimento");
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/universidade", "postgres",
					"postgres");
			PreparedStatement ps = Conexao.fabricar()
					.prepareStatement("insert into aluno(cpf,nome,data_nascimento) values(?,?,?)");
			ps.setString(1, s1);
			ps.setString(2, s2);
			ps.setString(3, s3);
			ps.executeUpdate();
			con.close();
			result = "Inclusao realizada";
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("UK_ALUNO_NOME")) {
				result = "Aluno já existe";
			}
		}

		out.println(result);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
