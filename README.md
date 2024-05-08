## 📚 Projeto Biblioteca Alexandria 📚

O objetivo deste projeto é fornecer uma API REST que simula uma biblioteca digital. Esta API permite que os usuários registrem e gerenciem três entidades principais: livros, autores e editoras.

A  API oferece a capacidade de relacionar, modificar e verificar todas as entidades (livros, autores e editoras). Isso oferece aos usuários a flexibilidade de trabalhar com essas informações da maneira que melhor atenda às suas necessidades.

Em resumo, este projet</details>o  é uma ferramenta poderosa para gerenciamento e organização de livros digitais, proporcionando uma experiência de usuário rica e personalizável.

<details>
   <summary><strong>📝 Habilidades trabalhadas</strong></summary>
  
  - Manipulação de banco de dados com Hibernate e Spring Data JPA
  - Injeção de dependências utilizando Spring
  - Exceções customizadas
  - Arquitetura baseada em OOP
  - API Rest
  - Organização através de models, services e controllers 
</details>

<details>
   <summary><strong>🤔 Como executar o projeto? </strong></summary>
   <br>

   1. Faça o clone do projeto
 - Use o comando: `git clone git@github.com:car0l15/alexandria.git`
 - Entre na pasta do repositório que você acabou de clonar:
  2. Instale as dependencias
     - `mvn spring-boot:run`
  3. Se conectando ao banco de dados <br>
  
     Usando docker  🐳🐳<br>
      1 - Certifique-se de que não existe nenhuma instalação do mysql para que não haja conflito<br>
      2 - rode o comando `docker compose up -d`<br>
      3 - Starte a aplicação e o hibernate fará a conexão com o DB<br>
     
  
     Caso queira se conectar ao seu DB local: <br>
      1 - Vá até o arquivo `application.properties`<br>
      2 - Modifique a propriedade `spring.datasource.username` para o username do seu DB<br>
      3 - Modifique a propriedade `spring.datasource.password` para a senha do seu DB<br>
      4 - Starte a aplicação e o hibernate fará a conexão com o DB<br>
   
  
</details>

