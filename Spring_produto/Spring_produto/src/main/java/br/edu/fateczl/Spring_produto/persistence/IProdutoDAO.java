package br.edu.fateczl.Spring_produto.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.Spring_produto.model.Produto;

public interface IProdutoDAO {
	
    public String insert(Produto produto) throws SQLException, ClassNotFoundException;
	public String update(Produto produto) throws SQLException, ClassNotFoundException;
	public String delete(Produto produto) throws SQLException, ClassNotFoundException;
	public Produto selectOne(Produto produto) throws SQLException, ClassNotFoundException;
    public List<Produto> selectAll() throws SQLException, ClassNotFoundException;
    
    public int consultaQtdForaEstoque(int qtdMinima) throws SQLException, ClassNotFoundException ;
    public List<Produto> consultaListaProdutosForaEstoque (int qtdMinima) throws SQLException, ClassNotFoundException ;
	
}
