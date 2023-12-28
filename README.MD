# SISTEMA JOGO DE XADREZ

## Sobre

Este é um sistema de jogo de xadrez produzido em JAVA, atavés da IDE Eclipse.

O projeto foi idealizado por [Nelio Alves.](https://github.com/acenelios)

O software é executado em terminal, e possui as seguintes funcionalidades:

- Movimentos padrão das peças
- Jogadas especiais: En Passant, Roque e Promoção
- Contagem de turno
- Peças capturadas
- Lógica de xeque e xeque-mate
- Tratamento de possíveis erros de entrada e desvios da regra

## Conteúdos Aplicados:

### Java

- Lógica de programação (estrutura sequencial, condicional e repetitiva, vetores, matrizes, funções)
- Entrada e saída de dados
- Tipos e tratamento de dados
- Tratamento de exceções
- Programação orientada a objetos

### Orientação a objetos

- Classe, objeto, atributos e métodos
- Construtores
- Sobrecarga
- Encapsulamento
- Membros estáticos
- Sobrescrita
- Enumerações
- Composição de classes
- Associação/dependência de classes
- Classes abstratas
- Herança
- Polimorfismo
- Upcasting
- Downcasting

### UML

Representações UML abordadas:

- Classes: atributos e métodos
- Encapsulamento: public, protected, private
- Relacionamentos: herança, composição, associação

## Diagrama de Classes

O diagrama de classes apresenta de forma simplificada as classes, suas propriedades (atributos e métodos) e seus relacionamentos.

Feito por [Nelio Alves.](https://github.com/acenelios)

![DiagramaDeClasses](img/chess-system-design.png)

## Demonstração

### Tabuleiro inicial

Distribuição das peças no tabuleiro

![TabuleiroInicial](img/inicio.png)

### Jogando

Digita-se a posição da peça que será movida e em seguida a posição de destino da peça.
O prórpio programa destaca os movimentos possíveis para aquela peça.

![Jogando](img/jogadas-possiveis.png)

### Cheque

O jogo emite um alerta de cheque, e exige que a proxima jogada seja defensiva.

![Cheque](img/cheque.png)
![Exception](img/exception%20cheque.png)

### Cheque-mate

Quando ocorre um cheque-mate o programa é encerrado.
O vencedor e o tabuleiro final ficam impressos na tela.

![ChequeMate](img/cheque%20mate.png)

## Como jogar

### Requisitos

- Ter uma versão recente do Java instalada (recomenda-se a LTS)
- Ter o JDK instalado
- Ter um terminal com cores instalado (sugestão: GIT Bash)

### Executando

- Clone o repositório
- Identifique a pasta com os arquivos executáveis do projeto
- Abra essa pasta no terminal
- Execute a classe Program do pacote application (java application/Program)
