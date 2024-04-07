# README

## Descrição do Projeto

Este projeto é uma API REST para gerenciamento de pessoas e seus endereços. A API permite criar, editar e consultar uma ou mais pessoas, bem como criar, editar e consultar um ou mais endereços de uma pessoa. Além disso, é possível indicar qual endereço será considerado o principal de uma pessoa.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- JPA/Hibernate
- Spring Data JPA
- Spring Web
- PostgreSQL

## Padrões de Projeto e Princípios SOLID

O projeto segue os princípios SOLID e utiliza os padrões de projeto Adapter, Singleton e Factory. O padrão Adapter é usado para converter DTOs em entidades e vice-versa. O padrão Singleton é usado para serviços que precisam ser instanciados apenas uma vez. O padrão Factory é usado para criar diferentes tipos de objetos de resposta com base nas necessidades do cliente.

## Clean Code e TDD

O projeto segue as práticas de Clean Code e TDD. O código é escrito de forma clara e compreensível, com nomes de variáveis, métodos e classes descritivos. O código é modularizado e cada método realiza uma única tarefa. Além disso, todos os recursos e funcionalidades foram desenvolvidos seguindo a metodologia TDD (Test Driven Development), garantindo uma cobertura de teste de linha de código de pelo menos 80%.

## Documentação da API

A documentação da API é gerada usando o Swagger. O Swagger fornece uma interface de usuário interativa para a documentação da API, permitindo que os usuários experimentem a API diretamente do navegador.

## Repositório

O projeto utiliza o Spring Data JPA para a camada de repositório. Além disso, o projeto utiliza o padrão Criteria para consultas complexas ao banco de dados, permitindo a construção de consultas de forma programática e segura.

## Como Executar

Para executar o projeto localmente, você precisa ter o Java e o Maven instalados em sua máquina. Além disso, você precisa de uma instância do PostgreSQL em execução.

1. Clone o repositório
2. Navegue até o diretório do projeto
3. Abra o arquivo `application.yml` e configure as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` com as informações do seu banco de dados PostgreSQL
4. Execute o comando `mvn clean install`
5. Execute o comando `mvn spring-boot:run`

A API estará disponível em `http://localhost:8080`.
