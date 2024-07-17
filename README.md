# Teste Técnico Backend Itaú

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
* com.app.testes.integracao= Contem a classe IntegracaoTest que foram criados todos os testes de integração automatizados
* com.app.testes.unitarios= Contem a classe ValidarTokenJWTServiceTest que foram criados todos os testes unitários automatizados

Resources main e test, estão armazenados os arquivos de properties conforme abaixo:

![image](https://github.com/user-attachments/assets/7ce25114-c6de-4162-a05c-dfcf5eae8af2)

Na resources/main o arquivo de properties da aplicação, apenas utilizamos a propriedade server.port para definir a porta que a api ira subir, no caso optamos pela 8000, porém, pode ser alterado para outras portas.

![image](https://github.com/user-attachments/assets/6e344ce4-c222-4adc-96ae-f1a187e68650)
Na resources/test o arquivo de propeties dos testes, armazenamos as propriedades uri e tokens que são utilizados de acordo com cada teste.

# Detalhando a classe e  métodos que validam a regra de negócio das claims
O classe que valida as regras de negócio das claims, esta localizado no pacote com.app.service classe ValidarTokenJWTService, nessa classe temos o método principal validateJwt que recebe uma string parametro nomeado como "jwt" e retorna um valor boleano, true se a jwt for válida e false se a jwt for inválida.

![image](https://github.com/user-attachments/assets/cea08b54-f5b3-47b3-a179-3336605222f8)

Foram criados os seguintes métodos na classe ValidarTokenJWTService:

* decodePayload(String jwt):Recebe como parametro uma string jwt verifica se o jwt possui mais de 02 partes, decodifica a base64 transformando em bites, depois converte os bytes em string e converte a string em json, por fim retorna um array de string baseado no tamanho do objetojson.
  
* verificaClaimRole(String claimRole): Recebe como parametro uma string claimRole e retorna boleano de acordo com a regra de negócio claim Role deve conter apenas 1 dos três valores (Admin, Member e External)
  
* verificaClaimSeed(int claimSeed): Recebe como parametro um int claimSeed e retorna boleano de acordo com a regra de negócio claim Seed deve ser um número primo.
  
* verificaClaimName(String claimName): Recebe como parametro uma string claimName e retorna boleano de acordo com a regra de negócio claim Name não pode ter carácter de números e O tamanho máximo da claim Name é de 256 caracteres.
  
* isPrimo(long n): Recebe como parametro um long numero e retorna boleano de acordo com a regra numero não pode ser < 1 e numero não pode ter como resultado o resto da divisão do numero ser igual a zero(0).

Tambem validamos no método validateJwt se o token é valido ou não e se o array strClaims obtido do método decodePayload é diferente ou igual a 3.

# Detalhando a classe TokenJWTController
A Classe TokenJWTController é responsável por receber as requisições, interpretar os parametros, interagir com o modelo e preparar a resposta da api.

![image](https://github.com/user-attachments/assets/b1fe14f7-fdd0-4b22-9891-97dc529b83a5)

O método validate recebe o parametro string JWT, no seu corpo, temos a chamada do método validarJWTService da classe ValidarTokenJWTService que realizada a validação do JWT retornando o valor boleano, se true método valida retorna json com campo message e valor JWT Válido, caso falso, retorna json com campo message e valor de acordo com o teste realizado.

# Detalhando a classe TokenJWT
A classe TokenJWT desempenha um papel central na aplicação, definindo a estrutura dos dados, implementando a lógica de negócios e interagindo com o armazenamento persistente dos dados do JWT.

![image](https://github.com/user-attachments/assets/768d9110-339c-41f2-a255-c13c19eabbe2)


##  Requisitos
* Java 11+ JDK deve estar instalado
* Maven deve estar instalado e configurado no path da aplicação

## Como iniciar a api
É possivel iniciar a aplicação pela propria IDE, executando na classe TokenJWTApplication Run As >> Java Application

![image](https://github.com/user-attachments/assets/caa43946-dc7b-4368-bfac-c4f542cfd962)

## Como executar os testes
É Possivel executar pela propria IDE, selecionado qual Teste deseja executar e simplismente dar play no método <br>
do teste ou podera executar pela linha de comando atraves do comando:<br>

## Testes unitários
Os testes unitários foram construidos visando validar as unidades(métodos e entradas) da classe ValidarTokenJWTServiceTest realizando testes positivos e negativos.

![image](https://github.com/user-attachments/assets/777396b3-e5ec-4619-b416-34c85c2285e5)

## Testes de integração
Os testes de integração foram construidos visando validar a integração de todas as partes da api construida realizando testes positivos, negativos, de status code, validando valor das mensagens do body response e tipo primitivo retornado.

![image](https://github.com/user-attachments/assets/1b16d737-0bff-4664-8345-1aef27986543)

## Execução dos testes
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
* Validar Header e Assinatura de um token JWT
* Gerar token JWT
* Relatório de testes customizados (Allure Report)
* Criação da classe BaseTest e da classe RunnerTest
