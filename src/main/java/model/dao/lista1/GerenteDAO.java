package model.dao.lista1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.Banco;
import model.dao.BaseDAO;
import model.entity.lista01.Gerente;
import model.entity.lista01.Operacional;

public class GerenteDAO implements BaseDAO<Gerente> {

	public Gerente salvar(Gerente gerencia) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO GERENCIA (NOME, CPF, SEXO, "
				+ "IDADE, SALBRUTO, DESCINSS, DESCIR, SALBASE, SALLIQUIDO, NMGERENCIA, COMISSAO, OPERACIONAIS, SIGDIRETORIA) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			stmt.setString(1, gerencia.getNome());
			stmt.setString(2, gerencia.getCpf());
			stmt.setString(3, gerencia.getSexo());
			stmt.setInt(4, gerencia.getIdade());
			stmt.setDouble(5, gerencia.getSalBruto());
			stmt.setDouble(6, gerencia.getDescInss());
			stmt.setDouble(7, gerencia.getDescIR());
			stmt.setDouble(8, gerencia.getSalBase());
			stmt.setDouble(9, gerencia.getSalLiquido());
			stmt.setString(10, gerencia.getNmGerencia());
			stmt.setDouble(11, gerencia.getComissao());
			stmt.setObject(12, gerencia.getOperacionais());
			stmt.setString(13, gerencia.getSigDiretoria());
			
			stmt.execute();
			
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				int idGerado = generatedKeys.getInt(1);
				gerencia.setCdGerencia(idGerado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir nova Gerencia.");
			System.out.println("Erro: " + e.getMessage());
		}

		return gerencia;
	}

	public boolean excluir(int cdGerencia) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM GERENCIA WHERE ID=" + cdGerencia;
		Statement stmt = Banco.getStatement(conn);
		
		int quantidadeLinhasAfetadas = 0;
		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir Gerencia.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return quantidadeLinhasAfetadas > 0;
	}

	public boolean alterar(Gerente gerencia) {
		Connection conn = Banco.getConnection();
		String sql = " UPDATE GERENCIA "
				+ " SET NOME=?, CPF=?, SEXO=?, IDADE=?, SALBRUTO=?, DESCINSS=?,"
				+ " DESCIR=?, SALBASE=?, SALLIQUIDO=?, NMGERENCIA=?, COMISSAO=?, OPERACIONAIS=?, SIGDIRETORIA=? "
				+ " WHERE CDGERENCIA=? ";

		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		int quantidadeLinhasAfetadas = 0;
		
		try {
			stmt.setString(1, gerencia.getNome());
			stmt.setString(2, gerencia.getCpf());
			stmt.setString(3, gerencia.getSexo());
			stmt.setInt(4, gerencia.getIdade());
			stmt.setDouble(5, gerencia.getSalBruto());
			stmt.setDouble(6, gerencia.getDescInss());
			stmt.setDouble(7, gerencia.getDescIR());
			stmt.setDouble(8, gerencia.getSalBase());
			stmt.setDouble(9, gerencia.getSalLiquido());
			stmt.setString(10, gerencia.getNmGerencia());
			stmt.setDouble(11, gerencia.getComissao());
			stmt.setObject(12, gerencia.getOperacionais());
			stmt.setString(13, gerencia.getSigDiretoria());
			stmt.setInt(14, gerencia.getCdGerencia());
			
			quantidadeLinhasAfetadas = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Operacional.");
			System.out.println("Erro: " + e.getMessage());
		}

		return quantidadeLinhasAfetadas > 0 ;
	}

	public Gerente consultarPorId(int cdGerencia) {
		Connection conn = Banco.getConnection();
		String sql = " SELECT CDGERENCIA, NOME, CPF, SEXO, IDADE, SALBRUTO, DESCINSS,"
				+ " DESCIR, SALBASE, SALLIQUIDO, NMGERENCIA, COMISSAO, OPERACIONAIS, SIGDIRETORIA"
				+ " FROM GERENCIA "
				+ " WHERE CDGERENCIA=" + cdGerencia;
		
		Statement stmt = Banco.getStatement(conn); 
		
		Gerente gerencia = null;
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);
			
			if(resultadoDaConsulta.next()) {
				gerencia = construirGerenciaDoResultSet(resultadoDaConsulta);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Gerencia por cdGerencia = " + cdGerencia);
			System.out.println("Erro: " + e.getMessage());
		}
		
		return gerencia;
	}
		

	public ArrayList<Gerente> consultarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT CDGERENCIA, NOME, CPF, SEXO, IDADE, SALBRUTO, DESCINSS,"
				+ " DESCIR, SALBASE, SALLIQUIDO, NMGERENCIA, COMISSAO, OPERACIONAIS, SIGDIRETORIA"
				+ " FROM GERENCIA ";

		Statement stmt = Banco.getStatement(conn); 
		ArrayList<Gerente> gerencias = new ArrayList<Gerente>();
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);
			
			while(resultadoDaConsulta.next()) {
				Gerente gerencia = construirGerenciaDoResultSet(resultadoDaConsulta);
				gerencias.add(gerencia);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Gerencias");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return gerencias;

}
	private Gerente construirGerenciaDoResultSet(ResultSet resultadoDaConsulta) {
		Gerente gerencia;
		gerencia = new Gerente();
		try {
			gerencia.setCdGerencia(resultadoDaConsulta.getInt("CDGERENCIA"));
			
			gerencia.setNome(resultadoDaConsulta.getString("NOME"));
			gerencia.setCpf(resultadoDaConsulta.getString("CPF"));
			gerencia.setSexo(resultadoDaConsulta.getString("SEXO"));
			gerencia.setIdade(resultadoDaConsulta.getInt("IDADE"));
			gerencia.setSalBruto(resultadoDaConsulta.getDouble("SALBRUTO"));
			gerencia.setDescInss(resultadoDaConsulta.getDouble("DESCINSS"));
			gerencia.setDescIR(resultadoDaConsulta.getDouble("DESCIR"));
			gerencia.setSalBase(resultadoDaConsulta.getDouble("SALBASE"));
			gerencia.setSalLiquido(resultadoDaConsulta.getDouble("SALLIQUIDO"));
			gerencia.setNmGerencia(resultadoDaConsulta.getString("NMGERENCIA"));
			gerencia.setComissao(resultadoDaConsulta.getDouble("COMISSAO"));
			gerencia.setOperacionais((List<Operacional>) resultadoDaConsulta.getObject("OPERACIONAIS"));
			gerencia.setSigDiretoria(resultadoDaConsulta.getString("SIGDIRETORIA"));
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao construir Operacional a partir do ResultSet");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return gerencia;
	}
	
}