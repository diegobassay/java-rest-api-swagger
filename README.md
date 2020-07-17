# java-rest-api-swagger
Projeto REST usando a entidade shopping

Aplicativo com as funcionalidades CRUD implementadas com Java 7/8, usando JUnit 5, Mockito, JAX-RS, JPA, H2 Database, Jacoco Coverage e Swagger.

## Pré-requisitos

* Docker version 19.03.8
* Docker-Compose version 1.25.0

## Para executar o projeto
Para construir e executar o projeto rodar os seguintes comandos na raiz do projeto:
```
docker build -t java-rest-api-swagger -f Dockerfile .

docker run --rm -p 9990:9990 -p 8080:8080 --name java-rest-api-swagger-containner java-rest-api-swagger:latest
```

## Sobre o banco de dados...
Não é necessário criar banco de dados, o JPA foi configurado para fazer o drop e o create cada vez que houver deploy do aplicativo, é obrigatório ter um datasource com o nome "ExampleDS" com drive H2 que vem por default no Wildfly no arquivo "standalone.xml" dentro da pasta "standalone/configuration":
```
                <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
                    <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                        <password>sa</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                </drivers>
```
## Para testar a API
Foi incorporado swagger para testar request a API, é necessário iniciar primeiro os serviços nomeados como "/populate", para acessar o Swagger digite o seguinte endereço:
```
http://localhost:8080/shoppingapi/swagger
```
## Para executar executar o relatório de cobertura e os tests
No Dockerfile trocar a linha:
```
RUN  mvn -f /home/app/pom.xml clean package -DskipTests
```
Para:
```
RUN  mvn -f /home/app/pom.xml clean package
```
*Isso irá criar um relatório com JaCoCo e executar os testes do JUnit5.
*Para abrir o relatório de cobertura na raiz do projeto entrar em "target/site/jacoco/index.html"