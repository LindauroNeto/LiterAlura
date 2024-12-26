#LiterAlura - Colecionando Livros! 📚👨‍💻
Sistema de catálogo de livros, utilizando a API [Gutendex](https://github.com/garethbjohnson/gutendex) e convertendo seus dados para dentro da aplicação e passando os dados para um banco de dados relacionais.

## 🔨 Funcionalidades
1. **Cadastramento de um novo livro**: Para realizar o cadastro, basta inserir o nome do livro,  caso o sistema encontre um nome parecido ou igual ao livro procurado ele irá buscar o primeiro resultado!
2. **Listagem dos livros cadastrados**: Após o cadastramento dos primeiros livros, será possível visualizar os livros dentro do sistema.
3. **Listagem dos autores**: Com o cadastramento dos livros, também serão cadastrados os autores, em que serão possível de serem visualizados por essa função.
4. **Verificação dos autores vivos em um determinado ano**: O sistema primeiramente vai solicitar o ano a ser imposto como delimitador, após, irá dar uma lista dos autores vivos do ano informado.
5. **Averiguação dos livros por idioma**: O sistema irá exibir um menu com todos os idiomas disponíveis para busca, após uma das opções do menu ser buscado, serão listados todos os livros daquele determinado idioma.

***OBS.1.: Sobre a `Funcionalidade 1`, tendo em vista que na API são mais de 70 mil livros cadastrados, haverá mais de um livro com o mesmo nome, ou nome parecido, por isso ele irá buscar o primeiro que encontrar. Caso tenha dificuldades em encontrar algum livro em específico, consulte-o primeiro no site do [Project Gutenburg](https://www.gutenberg.org/wiki/Main_Page).***

***OBS.2.: Ainda sobre a `Funcionalidade 1`, no momento, a aplicação só suporta 1 livro por autor, a pretensão futura é de permitir mais de livro por autor.***

## 🧠 Conhecimentos aplicado
- Estilo de empacotamento em **Package by Layer**;
- **Consumo de API**, com a ajuda de **Records** e do **Jackson** para a conversão dos dados;
- **Persistência dos Dados** obtidos;
- Uso de **Lamdas** para redução e aprimoração do código;
- Aplicação de **Streams** em listas de dados;
- Uso **JPA** e **JPQL** para o manejamento dos dados;
- Tratamento de **Exceptions**;
- Auxílio do **Spring Boot** para o desenvolvimento da aplicação, utilizando algumas de suas principais Beans, **Service** e **Repository**, para a realização de injeções de dependências.

## 💻 Tecnologias utilizada
- ``Java 17``
- ``Maven v4.0.0``
- ``Spring Boot``
- ``Spring Tool Suite 4 (IDE)``
- ``PostgreSQL``

