CREATE DATABASE spring_produto
GO

USE spring_produto
GO

CREATE TABLE Produto (
codigo           INT            NOT NULL,
nome             VARCHAR(30)    NOT NULL,
valor_unitario   DECIMAL(7, 2)  NOT NULL,
qtd_estoque      INT            NOT NULL
PRIMARY KEY(codigo)
)
GO


INSERT INTO Produto VALUES
(1, 'CHAVE', 7.5, 6),
(2, 'ESPADA', 10.5, 10)

INSERT INTO Produto VALUES
(3, 'pokemon', 100.9, 5),
(4, 'cerrote', 50.70, 4)

/*
Criar no SQL Server uma database com a seguinte tabela e fazer uma procedure que permita 
fazer o insert, update e delete dos dados na tabela, a partir de um código de operação (I, U ou D)
 */
CREATE PROCEDURE sp_produto (@opcao CHAR(1), 
                             @codigo INT, 
	                         @nome VARCHAR(100), 
							 @qtd_estoque INT,
							 @valor_unitario   DECIMAL(7, 2),
	                         @saida VARCHAR(200) OUTPUT)
AS
IF(UPPER(@opcao) = 'D' AND @codigo IS NOT NULL)
    BEGIN
         DELETE Produto
         WHERE codigo = @codigo
         SET @saida = 'Produto codigo '+CAST(@codigo AS VARCHAR(10))+' excluída'
    END

	ELSE
	BEGIN
	     IF(UPPER(@opcao) = 'D' AND @codigo IS NULL)
		 BEGIN
		      RAISERROR('O codigo do produto nulo', 16, 1)
		 END
		 ELSE
		 BEGIN
		      IF(UPPER(@opcao) = 'I')
			  BEGIN

			       INSERT INTO Produto VALUES
			       (@codigo, @nome, @valor_unitario, @qtd_estoque) 
                   SET @saida = 'Produto cadastro'

			  END
			  ELSE
			  BEGIN
              IF(UPPER(@opcao) = 'U')
			  BEGIN
			      UPDATE Produto
			      SET nome = @nome,
			          valor_unitario = @valor_unitario,
				      qtd_estoque = @qtd_estoque
			          WHERE codigo = @codigo
			          SET @saida = 'Produto' +CAST(@codigo AS VARCHAR(10))+ 'atualizado'
			  END
			  ELSE
			  BEGIN
			       RAISERROR('Operação Invalida', 16, 1)    
			  END
			  END
		 END
	END
	                DECLARE @opcao CHAR(1), 
                            @codigo INT, 
	                        @nome VARCHAR(100), 
							@qtd_estoque INT,
							@valor_unitario   DECIMAL(7, 2),
	                        @saida VARCHAR(200) 

					SET @opcao  = 'U'
                    SET @codigo = 1
	                SET @nome  = 'Chaves'
					SET @qtd_estoque = 17 
					SET	@valor_unitario = 4.50  
	                SET @saida = ''

	EXEC sp_produto @opcao, @codigo, @nome, @qtd_estoque, @valor_unitario, @saida

	SELECT * FROM Produto
/*
Fazer uma Scalar Function que verifique, na tabela produtos (codigo, nome, 
valor unitário e qtd estoque) quantos produtos estão com estoque abaixo de 
um valor de entrada (O valor mínimo deve ser parâmetro de entrada)
 */

CREATE FUNCTION fn_valor_minimo(@valor_minimo INT)
RETURNS INT
AS
BEGIN
DECLARE @quantidade_fora INT
SET @quantidade_fora = (SELECT COUNT(codigo) AS qtd_abaixo FROM Produto WHERE qtd_estoque < @valor_minimo)
RETURN @quantidade_fora
END

SELECT dbo.fn_valor_minimo(7) AS qtd_abaixo_do_estoque


/*
Fazer uma Multi Statement Table Function que liste o código, o nome e a 
quantidade dos produtos que estão com o estoque abaixo de um valor de 
entrada (O valor mínimo deve ser parâmetro de entrada)
 */
CREATE FUNCTION fn_produtos_quantidade(@valor_minimo INT)
RETURNS @tabela TABLE (
codigo              INT,
nome                VARCHAR(100),
quantidade_estoque  INT
)
AS
BEGIN
INSERT INTO @tabela (codigo, nome, quantidade_estoque)
   SELECT codigo, nome, qtd_estoque FROM Produto WHERE qtd_estoque < @valor_minimo
RETURN 
END

SELECT codigo, nome, quantidade_estoque FROM dbo.fn_produtos_quantidade(11)

SELECT codigo, nome, valor_unitario, qtd_estoque FROM Produto WHERE codigo = 1



