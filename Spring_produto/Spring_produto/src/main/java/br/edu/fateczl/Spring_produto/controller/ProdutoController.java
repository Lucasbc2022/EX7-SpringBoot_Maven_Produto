package br.edu.fateczl.Spring_produto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.Spring_produto.model.Produto;
import br.edu.fateczl.Spring_produto.persistence.ProdutoDAO;

@Controller
public class ProdutoController {
	
	@Autowired
	ProdutoDAO pDao;
	
	@RequestMapping(name = "produto", value = "/produto", method = RequestMethod.GET)
    public ModelAndView init (ModelMap model) {

		return new ModelAndView("produto");
	}
		

	
	@RequestMapping(name = "produto", value = "/produto",  method = RequestMethod.POST)
	public ModelAndView produtos(ModelMap model, @RequestParam Map<String, String> allParams) {
		String codigo = allParams.get("codigo");
		String nome = allParams.get("nome");
		String qtd_estoque = allParams.get("qtd_estoque");
		String valor_unitario = allParams.get("valor_unitario");
		
		String botao = allParams.get("botao");
		
		Produto produto = new Produto();
		List<Produto> produtos = new ArrayList<Produto>();
		String erro = "";
		String saida = "";
		System.out.println(codigo);
		
		try {
			
			if(botao.equalsIgnoreCase("listar")) {
				produtos = listaProdutos();
				produto = null;
			} else {
				produto.setCodigo(Integer.parseInt(codigo));
				if(botao.equalsIgnoreCase("buscar") || botao.equalsIgnoreCase("excluir")) {
		                if(botao.equalsIgnoreCase("buscar")) {
		                	
					        produto = pDao.selectOne(produto);  
					    } else {
				            saida = pDao.delete(produto);
				            produto = new Produto();
					    }
						
				} else {
					produto.setNome(nome);
					produto.setValor_unitario(Float.parseFloat(valor_unitario));
					produto.setQtd_estoque(Integer.parseInt(qtd_estoque));
					if(botao.equalsIgnoreCase("inserir")) {
						saida = pDao.insert(produto);
						produto = new Produto();
					} else {
						saida = pDao.update(produto);
						produto = new Produto();
					}
				}
					
					}
				
			
			
		} catch (ClassNotFoundException | SQLException e) {
		    erro = e.getMessage();
		} finally {
			
			model.addAttribute("produto", produto);
			model.addAttribute("produtos", produtos);
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
		}
			
		
		return new ModelAndView("produto");
	}
	
	private List<Produto> listaProdutos() throws ClassNotFoundException, SQLException {
		List<Produto> produtos = pDao.selectAll();
		
		return produtos;
	}

}
