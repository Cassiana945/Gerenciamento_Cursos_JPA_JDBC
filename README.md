# Gerenciamento_Cursos_JPA_JDBC
Este projeto demonstra a implementação de um sistema de gerenciamento de cursos e instrutores 
usando tanto JPA quanto JDBC, destacando as diferenças entre as duas abordagens.

→ Para iniciar o banco de dados, insira os seguintes comandos no terminal do projeto:

cd AtividadeJPA
ls docker-compose.yml
docker-compose up -d

Se as tabelas não forem criadas automaticamente copie e cole as inserções do arquivo schema.sql
que está dentro de resources no terminal de projeto.

→ Para testar os endpoints JDBC:

Clique run em AtividadeJpaApplication
Acesse: http://localhost:8080/swagger-ui/index.html#/

→ Para realizar os testes JPA:
Vá nas classes de teste e clique em run
