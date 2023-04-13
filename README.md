## Resumo

Esse repositório foi criado para realizar o desafio técnico de Backend do processo seletivo Jovens Profissionais da empresa Minsait. Consiste na criação de uma API REST em Java, utilizando conceitos de Orientação a Objetos, protocolos HTTP, validações entre outros. 

## Conceitos utilizados 

* Java
* SOLID
* Programação Orientada a Objetos
* Lógica de Programação

## Versões

* JDK 8 +
* Apache Maven 2.9 +

## Como executar

1. Clone o repositório.
2. Utilize o Maven para instalar as dependências.
3. Rode o arquivo LoanApiApplication.java no seu terminal ou na sua IDE de preferência.

## Rotas 

** CLIENTES

1. POST - /api/v1/clientes - cadastra um cliente no banco de dados.
2. GET  - /api/v1/clientes - lista todos os clientes cadastrados.
3. GET  - /api/v1/clientes/:cpf - retorna o cliente cadastrado com aquele CPF válido.
4. DELETE  - /api/v1/clientes/:cpf - deleta o cliente possuidor daquele CPF no banco de dados.
5. PUT - /api/v1/clientes/:cpf - atualiza o cliente possuidor daquele CPF no banco de dados.


** EMPRÉSTIMOS

1. POST - /api/v1/clientes/:cpf/emprestimos - adiciona um empréstimo para o cliente possuidor daquele CPF na base de dados.
2. GET  - /api/v1/clientes/:cpf/emprestimos - lista todos os empréstimos daquele cliente cadastrado.
3. GET  - /api/v1/clientes/:cpf/emprestimos/:id - retorna o empréstimo daquele cliente que tenha o id desejado.
4. DELETE  - /api/v1/clientes/:cpf/emprestimos/:id - deleta o empréstimo daquele cliente que tenha o id desejado no banco de dados.


## Validações

- A chave cpf deve ter exatamente 11 dígitos.
- A chave nome de possuir no mínimo 3 dígitos.
- A chave rua deve ter 4 ou mais dígitos.
- A chave numero é obrigatória
- A chave cep deve ter exatamente 8 dígitos.
- A chave rendimentoMensal é obrigatória
- A chave telefone deve ter 10 ou 11 dígitos.
