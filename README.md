# Teste Técnico challenge backend Itaú

Foi construido uma aplicação que expõe uma api web que recebe por parametros um JWT (string) e verifica se é valida conforme regras abaixo:

Deve ser um JWT válido
Deve conter apenas 3 claims (Name, Role e Seed)
A claim Name não pode ter carácter de números
A claim Role deve conter apenas 1 dos três valores (Admin, Member e External)
A claim Seed deve ser um número primo.
O tamanho máximo da claim Name é de 256 caracteres.

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
