<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/styles.css"/>'>
<title>Estoque de Produtos</title>
</head>
<body>
     <div>
          <jsp:include page="menu.jsp"/>
     </div>
     teste...
     <div align="center">
          <form action="produtoestoque" method="post">
               <table>
				<tr>
					<td colspan="3">
						<input type="number" min="0" step="1" id="valor_minimo" name="valor_minimo" placeholder="Valor Minimo"
						value='<c:out value="${produto.codigo }"></c:out>'>
					</td>
					<td><input type="submit" id="botao" name="botao" value="Buscar"></td>  
					<td><input type="submit" id="botao" name="botao" value="Listar"></td> 
                </tr>               
               </table>
          </form> 
     </div>
	<div align="center">
		<c:if test="${not empty erro }">
			<H2><c:out value="${erro }" /></H2>
		</c:if>
		<c:if test="${not empty saida }">
			<H2><c:out value="${saida }" /></H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty produtoestoque }">
			<table border="1">
				<thead>
					<tr>
						<th>Codigo</th>
						<th>Nome</>
						<th>Quantidade em Estoque</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${produtoestoque }" var="produtoestoque">
						<tr>
							<td><c:out value="${produtoestoque.codigo }"></c:out></td>
							<td><c:out value="${produtoestoque.nome }"></c:out></td>
							<td><c:out value="${produtoestoque.qtd_estoque }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	</div>
</body>
	<div align="center">
		<c:if test="${not empty quantidade }">
			<table border="1">
				<thead>
					<tr>
						<th>Quantidade Abaixo do Recomendado</th>
					</tr>
				</thead>
				<tbody>
					
						<tr>

							<td><c:out value="${quantidade }"></c:out></td>
						</tr>
				
				</tbody>
			</table>
		</c:if>	
	</div>        
</body>
</html>