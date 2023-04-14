<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/styles.css"/>'>
<title>Produtos</title>
</head>
<body>
     <div>
          <jsp:include page="menu.jsp"/>
     </div>
     <div align="center">
          <form action="produto" method="post">
               <table>
                 <tr>
                 <br />   
				<tr>
					<td colspan="3">
						<input type="number" min="0" step="1" id="codigo" name="codigo" placeholder="Codigo"
						value='<c:out value="${produto.codigo }"></c:out>'>
					</td>
					<td><input type="submit" id="botao" name="botao" value="Buscar"></td>  
                </tr>
                
				<tr>
					<td colspan="4">
						<input type="text" id="nome" name="nome" placeholder="Nome"
						value='<c:out value="${produto.nome }"></c:out>'>
				    </td>
				</tr>
				
				<tr>
					<td colspan="4">
						<input type="number" min="0" step="0.01" id="valor_unitario" name="valor_unitario" placeholder="Valor Unitario"
						value='<c:out value="${produto.valor_unitario }"></c:out>'>
				    </td>
				</tr>	
				
				<tr>
					<td colspan="4">
						<input type="number" id="qtd_estoque" name="qtd_estoque" placeholder="Quantidade em Estoque"
						value='<c:out value="${produto.qtd_estoque }"></c:out>'>
				    </td>
				</tr>
				
				<tr>
					<td><input type="submit" id="botao" name="botao" value="Inserir"></td>
					<td><input type="submit" id="botao" name="botao" value="Atualizar"></td>
					<td><input type="submit" id="botao" name="botao" value="Excluir"></td>
					<td><input type="submit" id="botao" name="botao" value="Listar"></td>
				</tr>												
                
               </table>
          </form>
     </div>      
     <br />
	<div align="center">
		<c:if test="${not empty erro }">
			<H2><c:out value="${erro }" /></H2>
		</c:if>
		<c:if test="${not empty saida }">
			<H2><c:out value="${saida }" /></H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty produtos }">
			<table border="1">
				<thead>
					<tr>
						<th>Codigo</th>
						<th>Nome</th>
						<th>Valor Unitario</th>
						<th>Quantidade em Estoque</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${produtos }" var="produtos">
						<tr>
							<td><c:out value="${produtos.codigo }"></c:out></td>
							<td><c:out value="${produtos.nome }"></c:out></td>
							<td><c:out value="${produtos.valor_unitario }"></c:out></td>
							<td><c:out value="${produtos.qtd_estoque }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	</div>
</body>
	<div align="center">
		<c:if test="${not empty produto }">
			<table border="1">
				<thead>
					<tr>
						<th>Codigo</th>
						<th>Nome</th>
						<th>Valor Unitario</th>
						<th>Quantidade em Estoque</th>
					</tr>
				</thead>
				<tbody>
					
						<tr>
							<td><c:out value="${produto.codigo }"></c:out></td>
							<td><c:out value="${produto.nome }"></c:out></td>
							<td><c:out value="${produto.valor_unitario }"></c:out></td>
							<td><c:out value="${produto.qtd_estoque }"></c:out></td>
						</tr>
				
				</tbody>
			</table>
		</c:if>	
	</div>
</html>