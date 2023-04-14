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
public class ProdutoEstoqueController {

	@Autowired
	ProdutoDAO pDao;
	private int quantidade;
	
	@RequestMapping(name = "produtoestoque", value = "/produtoestoque", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		return new ModelAndView("produtoestoque");
	}
	
	@RequestMapping(name = "produtoestoque", value = "/produtoestoque", method = RequestMethod.POST)
	public ModelAndView produtos(ModelMap model, @RequestParam Map<String, String> allParams) {
		String valor_minimo = allParams.get("valor_minimo");
		String botao = allParams.get("botao");
		
		
		
		List<Produto> produtoestoque = new ArrayList<Produto>();
		String erro = "";
		String saida = "";
		
		try {
			int qtdMinima = Integer.parseInt(valor_minimo);
			if(botao.equalsIgnoreCase("buscar")) {
				quantidade = pDao.consultaQtdForaEstoque(qtdMinima);
			} else {
				if(botao.equalsIgnoreCase("listar")) {
				produtoestoque = pDao.consultaListaProdutosForaEstoque(qtdMinima);
				}
			  }
			
		} catch(ClassNotFoundException | SQLException e) {
			
			
		} finally {
			
			model.addAttribute("produtoestoque", produtoestoque);
			model.addAttribute("quantidade", quantidade);
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
		}
		
		return new ModelAndView("produtoestoque");
	}
}
