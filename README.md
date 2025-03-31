
# ITeam: Desafio Técnico - API de Produtos

Este projeto foi desenvolvido para atender aos requisitos do teste da IT-EAM (www.it-eam.com). O objetivo é criar uma API RESTful para o gerenciamento de produtos, permitindo que usuários autenticados realizem operações de CRUD (Criar, Ler, Atualizar e Excluir). A API conta com validações robustas e controle de acesso baseado em funções.

## Conceitos Aplicados

#### "O trabalho de uma boa arquitetura de software é adiar decisões, não tomá-las."

Uma boa arquitetura adia decisões, evitando escolhas prematuras e facilitando mudanças. A Clean Architecture tem ganhado popularidade por sua capacidade de criar sistemas flexíveis, escaláveis e de fácil manutenção. Seu foco está na separação de responsabilidades e na independência de frameworks, priorizando a lógica de domínio e as regras de negócio. Esse modelo permite evoluir o sistema sem impactar outras partes, garantindo adaptabilidade e qualidade a longo prazo.

O tema central da Clean Architecture é dividir sistemas em camadas para torná-los mais flexíveis, manuteníveis e testáveis.

IMAGEM

#### 1. Entidade (Core)

 - Propósito: Representa as principais entidades ou objetos de dominio da aplicação, como objetos de negócios. Eles contêm apenas lógica relacionada ao dominio e não têm conhecimento de detalhes de implementação, como banco de dados, frameworks ou interfaces do usuário. 

 - Depedências: Não deve depender de nenhuma outra camada, especialmente de detalhes técnicos. 


#### 2. Use Case

- Propósito: Define os casos de uso da aplicação. Cada caso de uso é uma funcionalidade ou tarefa especifica que a aplicação podee realizar. Ele contém a logica de negocios e coordena a interação entre as entidades.

- Depedências: Pode depender de entidades, mas não deve depender diretamente de detalhes técnicos, como interfaces de usuário ou banco de dados. 


#### 3. Inteface and Adapter (Application)

- Propósito: Essa camada lida com a interação com o mundo exterior à aplicação, como interfaces de usuários, APIs externas ou banco de dados. As interfaces definem os contratos que a aplicação precisa implementar, enquanto os adaptadores fornecem a implementação real para cumprir esses contratos. 

Dependências: Pode depender de casos de uso e entidades, mas não deve depender de detalhes técnicos específicos, como frameworks.

#### 4. Framework ()

- Propósito: Esta camada contém detalhes técnicos e implementações concretas de frameoworks, bibliotecas e componentes externos. Ela se comunica com o sistema operacional, banco de dados, serviços web, etc. 

- Dependências: Pode depender das outras camadas da aplicação para fornecer funcionalidade específica, mas as camadas internas não devem depender diretamente dela.

A Clean Architecture enfatiza a sepração de responsabilidades entre essas camdas, isolando o coração do software, e se preocupando a longo prazo com a manutenabilidade e escalonamento de tecnologias. 

## Técnicas 

- Spring Boot:: 3.4.4
- Java:: 17
- Flyway 
- Data Base → H2
- Lombok 
- Fluent Validator
- Swagger 
- Gradle

## Modules

- Core 
- UseCase 
- Application
- Infrastructure
