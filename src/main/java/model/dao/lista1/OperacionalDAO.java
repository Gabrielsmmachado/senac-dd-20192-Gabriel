package model.dao.lista1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.dao.BaseDAO;
import model.entity.lista01.Operacional;

public class OperacionalDAO implements BaseDAO<Operacional> {

	public Operacional salvar(Operacional operacional) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO OPERACIONAL (NOME, CPF, SEXO, "
				+ "IDADE, SALBRUTO, DESCINSS, DESCIR, SALBASE, SALLIQUIDO, NMGERENCIA) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			stmt.setString(1, operacional.getNome());
			stmt.setString(2, operacional.getCpf());
			stmt.setString(3, operacional.getSexo());
			stmt.setInt(4, operacional.getIdade());
			stmt.setDouble(5, operacional.getSalBruto());
			stmt.setDouble(6, operacional.getDescInss());
			stmt.setDouble(7, operacional.getDescIR());
			stmt.setDouble(8, operacional.getSalBase());
			stmt.setDouble(9, operacional.getSalLiquido());
			stmt.setString(10, operacional.getNmGerencia());
			
			stmt.execute();
			
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				int idGerado = generatedKeys.getInt(1);
				operacional.setCdOperacional(idGerado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo Operacional.");
			System.out.println("Erro: " + e.getMessage());
		}

		return operacional;
	}

	public boolean excluir(int cdOperacional) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM OPERACIONAL WHERE ID=" + cdOperacional;
		Statement stmt = Banco.getStatement(conn);
		
		int quantidadeLinhasAfetadas = 0;
		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir Operacional.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return quantidadeLinhasAfetadas > 0;
	}

	public boolean alterar(Operacional operacional) {
		Connection conn = Banco.getConnection();
		String sql = " UPDATE OPERACIONAL "
				+ " SET NOME=?, CPF=?, SEXO=?, IDADE=?, SALBRUTO=?, DESCINSS=?,"
				+ " DESCIR=?, SALBASE=?, SALLIQUIDO=?, NMGERENCIA=? "
				+ " WHERE CDOPERACIONAL=? ";

		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		int quantidadeLinhasAfetadas = 0;
		
		try {
			stmt.setString(1, operacional.getNome());
			stmt.setString(2, operacional.getCpf());
			stmt.setString(3, operacional.getSexo());
			stmt.setInt(4, operacional.getIdade());
			stmt.setDouble(5, operacional.getSalBruto());
			stmt.setDouble(6, operacional.getDescInss());
			stmt.setDouble(7, operacional.getDescIR());
			stmt.setDouble(8, operacional.getSalBase());
			stmt.setDouble(9, operacional.getSalLiquido());
			stmt.setString(10, operacional.getNmGerencia());
			stmt.setInt(11, operacional.getCdOperacional());
			quantidadeLinhasAfetadas = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Operacional.");
			System.out.println("Erro: " + e.getMessage());
		}

		return quantidadeLinhasAfetadas > 0 ;
	}

	public Operacional consultarPorId(int cdOperacional) {
		Connection conn = Banco.getConnection();
		String sql = " SELECT CDOPERACIONAL, NOME, CPF, SEXO, IDADE, SALBRUTO, DESCINSS,"
				+ " DESCIR, SALBASE, SALLIQUIDO, NMGERENCIA"
				+ " FROM OPERACIONAL "
				+ " WHERE CDOPERACIONAL=" + cdOperacional;
		
		Statement stmt = Banco.getStatement(conn); 
		
		Operacional operacional = null;
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);
			
			if(resultadoDaConsulta.next()) {
				operacional = construirOperacionalDoResultSet(resultadoDaConsulta);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Operacional por cdOperacional = " + cdOperacional);
			System.out.println("Erro: " + e.getMessage());
		}
		
		return operacional;
	}

	public ArrayList<Operacional> consultarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT CDOPERACIONAL, NOME, CPF, SEXO, IDADE, SALBRUTO, DESCINSS,"
				+ " DESCIR, SALBASE, SALLIQUIDO, NMGERENCIA"
				+ " FROM OPERACIONAL ";

		Statement stmt = Banco.getStatement(conn); 
		ArrayList<Operacional> operacionais = new ArrayList<Operacional>();
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);
			
			while(resultadoDaConsulta.next()) {
				Operacional operacional = construirOperacionalDoResultSet(resultadoDaConsulta);
				operacionais.add(operacional);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Operacionais");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return operacionais;
	}
	
	private Operacional construirOperacionalDoResultSet(ResultSet resultadoDaConsulta) {
		Operacional operacional;
		operacional = new Operacional();
		try {
			operacional.setCdOperacional(resultadoDaConsulta.getInt("CDOPERACIONAL"));
			
			operacional.setNome(resultadoDaConsulta.getString("NOME"));
			operacional.setCpf(resultadoDaConsulta.getString("CPF"));
			operacional.setSexo(resultadoDaConsulta.getString("SEXO"));
			operacional.setIdade(resultadoDaConsulta.getInt("IDADE"));
			operacional.setSalBruto(resultadoDaConsulta.getDouble("SALBRUTO"));
			operacional.setDescInss(resultadoDaConsulta.getDouble("DESCINSS"));
			operacional.setDescIR(resultadoDaConsulta.getDouble("DESCIR"));
			operacional.setSalBase(resultadoDaConsulta.getDouble("SALBASE"));
			operacional.setSalLiquido(resultadoDaConsulta.getDouble("SALLIQUIDO"));
			operacional.setNmGerencia(resultadoDaConsulta.getString("NMGERENCIA"));
		} catch (SQLException e) {
			System.out.println("Erro ao construir Operacional a partir do ResultSet");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return operacional;
	}

}
