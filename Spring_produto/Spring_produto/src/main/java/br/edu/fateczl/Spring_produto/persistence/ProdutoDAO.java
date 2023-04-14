package br.edu.fateczl.Spring_produto.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.Spring_produto.model.Produto;

@Repository
public class ProdutoDAO implements IProdutoDAO{
	
	@Autowired
	private GenericDAO gDao;
	

	private String callProcedureProduto(String opcao, Produto produto) throws ClassNotFoundException, SQLException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_produto(?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, opcao);
		cs.setInt(2, produto.getCodigo());
		cs.setString(3, produto.getNome());
		cs.setInt(4, produto.getQtd_estoque());
		cs.setFloat(5, produto.getValor_unitario());
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		
		cs.close();
		c.close();
		
		return saida;
	}
	@Override
	public String insert(Produto produto) throws SQLException, ClassNotFoundException {
		String saida = callProcedureProduto("I", produto);
		return saida;
	}

	@Override
	public String update(Produto produto) throws SQLException, ClassNotFoundException {
		String saida = callProcedureProduto("U", produto);
		return saida;
	}

	@Override
	public String delete(Produto produto) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_produto(?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "D");
		cs.setInt(2, produto.getCodigo());
		cs.setNull(3, Types.VARCHAR);
		cs.setNull(4, Types.VARCHAR);
		cs.setNull(5, Types.VARCHAR);
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		
		cs.close();
		c.close();
		
		return saida;
	}

	@Override
	public Produto selectOne(Produto produto) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome, valor_unitario, qtd_estoque FROM Produto WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, produto.getCodigo());
		System.out.println(produto.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			 
		     produto.setNome(rs.getString("nome"));
		     produto.setValor_unitario(rs.getFloat("valor_unitario"));
		     produto.setQtd_estoque(rs.getInt("qtd_estoque"));
		     System.out.println(produto.toString());
		}
		rs.close();
		ps.close();
		c.close();
		return produto;
	}

	@Override
	public List<Produto> selectAll() throws SQLException, ClassNotFoundException {
		List<Produto> produtos = new ArrayList<>();
		
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome, valor_unitario, qtd_estoque FROM Produto";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
		     Produto produto = new Produto();	
		     produto.setCodigo(rs.getInt("codigo"));
		     produto.setNome(rs.getString("nome"));
		     produto.setValor_unitario(rs.getFloat("valor_unitario"));
		     produto.setQtd_estoque(rs.getInt("qtd_estoque"));
		     produtos.add(produto);
		     
		}
		rs.close();
		ps.close();
		c.close();
		
		return produtos;
	}

	@Override
	public int consultaQtdForaEstoque(int qtdMinima) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT dbo.fn_valor_minimo(?) AS qtd_abaixo_do_estoque";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, qtdMinima);
		ResultSet rs = ps.executeQuery();
		int quantidade = 0;
		if(rs.next()) {
			 quantidade = rs.getInt("qtd_abaixo_do_estoque");
		}
		return quantidade;
	}

	@Override
	public List<Produto> consultaListaProdutosForaEstoque(int qtdMinima) throws SQLException, ClassNotFoundException  {
		List<Produto> produtos_abaixo_etoque = new ArrayList<>();
		
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome, quantidade_estoque FROM dbo.fn_produtos_quantidade(?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, qtdMinima);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
		     Produto produto = new Produto();	
		     produto.setCodigo(rs.getInt("codigo"));
		     produto.setNome(rs.getString("nome"));		     
		     produto.setQtd_estoque(rs.getInt("quantidade_estoque"));
		     produtos_abaixo_etoque.add(produto);
		}
		rs.close();
		ps.close();
		c.close();
		
		return produtos_abaixo_etoque;
	}
}

	

