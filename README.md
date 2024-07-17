# Teste Técnico challenge backend Itaú

Foi construido uma aplicação que expõe uma api web que recebe por parametros um JWT (string) e verifica se é valida conforme regras abaixo:

* Deve ser um JWT válido
* Deve conter apenas 3 claims (Name, Role e Seed)
* A claim Name não pode ter carácter de números
* A claim Role deve conter apenas 1 dos três valores (Admin, Member e External)
* A claim Seed deve ser um número primo.
* O tamanho máximo da claim Name é de 256 caracteres.

# Detalhes da API
A api foi construida utilizando a linguagem de programação java com framework spring boot, na src/main/java temos os pacotes dividos da seguinte forma:

![image](https://github.com/user-attachments/assets/339e6595-e776-40c7-8f3a-0e8b7dfc0d05)

* com.app= Contem a classe TokenJWTApplication.java que inicia a aplicação(spring boot)
* com.app.controler= Contem a classe TokenJWTController que realiza o mapeamento dos endpoints
* com.app.model= Contem a classe TokenJWT que modela os objetos com getters e setters
* com.app.service= Contem a classe ValidarTokenJWTService que acopla as regras de negócio
* com.utils= Contem classes com métodos uteis para todo o projeto.

Na src/test/java temos:

![image](https://github.com/user-attachments/assets/31266269-8df1-4258-ab6c-d0199f2ac696)

* com.app.dados= Contem a classe Dados que utilizamos para capturar os dados de teste.
* com.app.testes.integracao= Comtem a classe IntegracaoTest que foram criados todoss os testes de integração automatizados
* com.app.testes.unitarios= Contem a classe ValidarTokenJWTServiceTest que foram criados todoss os testes unitários automatizados

Resources main e test, estão armazenados os arquivos de properties conforme abaixo:

![image](https://github.com/user-attachments/assets/7ce25114-c6de-4162-a05c-dfcf5eae8af2)
Na resources/main o arquivo de properties da aplicação, apenas utilizamos a propriedade server.port para definir a porta que a api ira subir, no caso optamos pela 8000, porém, pode ser alterado para outras portas.

![image](https://github.com/user-attachments/assets/6e344ce4-c222-4adc-96ae-f1a187e68650)
Na resources/test o arquivo de propeties dos testes, armazenamos as propriedades uri e tokens que são utilizados de acordo com cada teste.

# Detalhando a classe e  métodos que validam a regra de negócio das claims
O classe que valida as regras de negócio das claims, esta localizado no pacote com.app.service classe ValidarTokenJWTService, nessa classe temos o método principal validateJwt que recebe uma string parametro nomeado como "jwt" e retorna um valor boleano, true se a jwt for válida e false se a jwt for inválida.

![image](https://github.com/user-attachments/assets/cea08b54-f5b3-47b3-a179-3336605222f8)

Foram criados os seguintes métodos na classe ValidarTokenJWTService:

* verificaClaimRole(String claimRole): Recebe como parametro uma string claimRole e retorna boleano de acordo com a regra de negócio claim Role deve conter apenas 1 dos três valores (Admin, Member e External)
* verificaClaimSeed(int claimSeed): Recebe como parametro um int claimSeed e retorna boleano de acordo com a regra de negócio claim Seed deve ser um número primo.
* verificaClaimName(String claimName): Recebe como parametro uma string claimName e retorna boleano de acordo com a regra de negócio claim Name não pode ter carácter de números e O tamanho máximo da claim Name é de 256 caracteres.
* isPrimo(long n): Recebe como parametro um long numero e retorna boleano de acordo com a regra numero não pode ser < 1 e numero não pode ter como resultado o resto da divisão do numero ser igual a zero(0).

##  Requisitos
* Java 11+ JDK deve estar instalado
* Maven deve estar instalado e configurado no path da aplicação

## Como iniciar a api
É possivel iniciar a aplicação pela propria IDE, executando na classe TokenJWTApplication Run As >> Java Application 

## Como executar os testes
É Possivel executar pela propria IDE, selecionado qual Teste deseja executar e simplismente dar play no método <br>
do teste ou podera executar pela linha de comando atraves do comando:<br>

Rodar todos os testes<br>
```bash
mvn test 
```
Rodar teste especifico utilizando o nome do método<br>
```bash
mvn test -Dtest="IntegracaoTest#deveVerificarJwtValido"
```

## O que foi entregue?
* Api que valida Claims
* Testes de Integração (Positivos e negativos)
* Testes unitários (Positivos e negativos)

## Melhorias
* Expor api em container ou cloud
* Relatório de testes customizados (Allure Report)
* Validar Header e Assinatura de um token JWT
* Gerar token JWT
