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

## Padrões de Projeto

O projeto segue os princípios SOLID e utiliza os padrões de projeto Adapter, Singleton e Factory.

- **Adapter**: Este padrão é usado para converter a interface de uma classe em outra interface que o cliente espera. No contexto deste projeto, é usado para converter DTOs em entidades e vice-versa.
- **Singleton**: Este padrão garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global a ela. É usado no projeto para serviços que precisam ser instanciados apenas uma vez.
- **Factory**: Este padrão é usado para criar objetos sem expor a lógica de criação ao cliente. No projeto, é usado para criar diferentes tipos de objetos de resposta com base nas necessidades do cliente.

## Princípios SOLID

- **Single Responsibility Principle (SRP)**: Cada classe no projeto tem uma única responsabilidade.
- **Open-Closed Principle (OCP)**: As entidades do projeto estão abertas para extensão, mas fechadas para modificação.
- **Liskov Substitution Principle (LSP)**: As classes derivadas podem ser substituídas por suas classes-base.
- **Interface Segregation Principle (ISP)**: O projeto usa muitas interfaces específicas do cliente em vez de uma interface geral.
- **Dependency Inversion Principle (DIP)**: O projeto depende de abstrações, não de concretizações.

## Clean Code

O projeto segue as práticas de Clean Code. O código é escrito de forma clara e compreensível, com nomes de variáveis, métodos e classes descritivos. O código é modularizado e cada método realiza uma única tarefa.

## Documentação da API

A documentação da API é gerada usando o Swagger. O Swagger fornece uma interface de usuário interativa para a documentação da API, permitindo que os usuários experimentem a API diretamente do navegador.

## Repositório

O projeto utiliza o Spring Data JPA para a camada de repositório. Além disso, o projeto utiliza o padrão Criteria para consultas complexas ao banco de dados, permitindo a construção de consultas de forma programática e segura.

## Cobertura de Testes

O projeto tem uma cobertura de teste de linha de código de pelo menos 80%. Além disso, todas as condições de código são cobertas por testes.

## Como Executar

Para executar o projeto localmente, você precisa ter o Java e o Maven instalados em sua máquina. Além disso, você precisa de uma instância do PostgreSQL em execução.

1. Clone o repositório
2. Navegue até o diretório do projeto
3. Execute o comando `mvn spring-boot:run`

A API estará disponível em `http://localhost:8080`.


Por favor, substitua os links de código-fonte e API Live pelos links reais do seu projeto.