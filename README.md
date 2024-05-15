# JWT Validation
Esse projeto consiste em um validador de JWTs que, através de regras pré-definidas e uma signing key de entrada, realizada a verificação e validação do token.

## Validação
**Input:** Um JWT (string)

**Output:** verdadeiro (caso o token jwt atenda às regras); ou falso (caso contrário)

As regras implementadas para essa validação foram:

- Deve ser um JWT válido
- Deve conter apenas 3 claims (Name, Role e Seed)
- A claim Name não pode ter carácter de números
- A claim Role deve conter apenas 1 dos três valores (Admin, Member e External)
- A claim Seed deve ser um número primo.
- O tamanho máximo da claim Name é de 256 caracteres.

## Instruções de Execução

Para executar este projeto, siga as instruções abaixo:

### Pré-requisitos

- Java Development Kit (JDK) 17 ou superior instalado e configurado no seu sistema.
- Maven instalado e configurado.

### Compilação e Execução

1. Clone o repositório:

~~~
https://github.com/filipearn/itau-authentication.git
~~~

2. Navegue até o diretório do projeto:

~~~
cd itau-authentication
~~~

3. Compile o projeto:

~~~
mvn clean install
~~~

4. Execução do projeto: verificar o arquivo gerado na pasta target e executá-lo através do comando

- para execução local:
  - parâmetro de profile: local
  - caminho para arquivo que contém signing-key jwt: /caminho/para/arquivo/jwt-signing-key

~~~
java -jar -Dspring.profiles.active=local -DJWT_SIGNING_KEY_NAME=/caminho/para/arquivo/jwt-signing-key -DPORT:9090 target/nome-do-arquivo-jar.jar
~~~

# Requisições e retornos esperados

Para auxiliar nos testes, navegue até 'src/main/resources/insomnia' e acesse as coleções do insomnia.

No insomnia, crie a variável de ambiente 'base_url'
~~~json
{
  "base_url": "localhost:9090"
}
~~~

### Considerações
- a aplicação está configurada na porta 9090 como padrão. Caso deseje mudar, enviar a variável de ambiente 'PORT' com o valor.

**Caso 1: Token válido**
~~~json
{
  "Role": "Admin",
  "Seed": "7841",
  "Name": "Toninho Araujo"
}
~~~
~~~
curl -X POST http://localhost:9090/itau-challenge/v1/authentications?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.4Zo20qmmagvUvo_Ft0Wjz8crrrtm8NFOeSHpknYN-eQ
~~~
~~~
retorno: verdadeiro
~~~

**Caso 2: Token inválido**
~~~
curl -X POST http://localhost:9090/itau-challenge/v1/authentications?jwt=eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg
~~~
~~~
retorno: falso
~~~

**Caso 3: Claim name em lowercase**
~~~json
{
  "Role": "Admin",
  "Seed": "7841",
  "Name": "Toninho Araujo"
}
~~~
~~~
curl -X POST http://localhost:9090/itau-challenge/v1/authentications?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIm5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.EIgVDCuV0o4gnyYBVO_xw7DbAvhF_SebhjiNr99RH28
~~~
~~~
retorno: falso
~~~

**Caso 4: Claim name possui número**
~~~json
{
  "Role": "External",
  "Seed": "72341",
  "Name": "M4ria Olivia"
}
~~~
~~~
curl -X POST http://localhost:9090/itau-challenge/v1/authentications?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiNzIzNDEiLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.sS-beTM4Jz2teToF-ru0GkxuSzUIzceiUNSbeZ-bek4
~~~
~~~
retorno: falso
~~~

**Caso 5: Claim name maior que 256 caracteres**
~~~
curl -X POST http://localhost:9090/itau-challenge/v1/authentications?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJ2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zdmFsb3IgZGUgY2xhaW0gcG9zc3VpIGNhcmFjdGVyIGRlIG7Dum1lcm9zciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3N2YWxvciBkZSBjbGFpbSBwb3NzdWkgY2FyYWN0ZXIgZGUgbsO6bWVyb3MifQ.YVFGhiPDVaKIHIZBSk8qYOipDoXD0b7S9Ruld_Le0_o
~~~
~~~
retorno: falso
~~~
**Caso 6: mais que 3 claims**
~~~json
{
  "Role": "Member",
  "Org": "BR",
  "Seed": "14627",
  "Name": "Valdir Aranha"
}
~~~
~~~
curl -X POST http://localhost:9090/itau-challenge/v1/authentications?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.nShXVl4fbCGd-hTANDje6Ixr_cT4Atw1-GGuprrzZTc
~~~
~~~
retorno: falso
~~~
# Premissas

Durante o desenvolvimento deste projeto, as seguintes premissas e decisões foram assumidas:

- optei por deixar o envio do jwt como não obrigatório, retornando falso quando o valor for nulo;
- para valores 'em branco' para o jwt, também foi considerado como falso;
- foi considerado a capitalização de cada Claim, ou seja, Name != name.

# Estrutura da aplicação

A aplicação está estruturada utilizando o padrão de design Clean Architecture e contém três principais pacotes:
- app/api: contém toda a definição dos contratos/interface para acesso à aplicação;
- app/service: que contém o serviço de validação do Jwt;
- app/usecases: que contém a lógica de negócios da aplicação, mantendo-a isolada das camadas externas e garantindo a centralização das regras de validação.

# Principais estratégias

As principais estratégias utilizadas para a implementação da validação de um token JWT foram fundamentalmente baseadas nos princípios SOLID. 

As regras de negócio foram implementadas de modo que possam ser facilmente estendidas, uma vez que foi utilizado o padrão de projeto strategy, permitindo que as regras de validação e estratégias possam ser definidas em tempo de execução. Através dessa estratégia, é possível criar novas regras de validação sem que seja necessário realizar alterações nas definições da interface, atendento o Princípio Aberto/Fechado e corroborando com a utilização, também, do Princípio da Responsabilidade Única.

No decorrer do desenvolvimento foi detectado um comportamento comum entre todas as implementações de ValidationStrategy, o que permitiu desenvolver uma classes abstrata que implementava esse método, visando a evitar a repetição de código.

# Descrições dos principais métodos e classes

### `public CompletableFuture<ResponseEntity<Object>> authenticationPost(final String jwt)`

Método responsável por expor o endpoint da aplicação para recebimento de requisições de validação de tokens JWT.

### `classe JWTValidator`

Principal classe de validação do JWT, tendo a responsabilidade de validar a estrutura do token e orquestrar a validação das Claims utilizando o atributo strategyMap, que é um mapa de validadores. 

### `classes NameClaimValidationStrategy, RoleClaimValidationStrategy e SeedClaimValidationStrategy`

Classes responsáveis pela lógica de negócio e regras necessárias para validação de cada Claim presente no JWT. 

### `public String checkJwt(final String jwt)`

Método responsável pelo serviço de validação de um JWT. Responsável por instanciar um JWTValidator.