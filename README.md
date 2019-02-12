# java-rest-api-swagger
Projeto REST usando a entidade shopping

Aplicativo com as funcionalidades CRUD implementadas com Java 7/8, usando JUnit 5, Mockito, JAX-RS, JPA, H2 Database, Jacoco Coverage e Swagger.

## Pré-requisitos

* Java >= 7
* WildFly >= 10

## Preparando o projeto

Para compilar o projeto executar o seguinte comando:
```
mvn clean install
```
* Copiar arquivo "shoppingapi.war" na pasta "target" do projeto.
* Colar o arquivo na pasta "standalone/deployments" dentro do Wildfly.


## Para criar o banco de dados
Não é necessário criar banco de dados, o JPA foi configurado para fazer o drop e o create cada vez que houve deploy do aplicativo, é obrigatório ter um datasource com o nome "ExampleDS" com drive H2 que vem por default no Wildfly no arquivo "standalone.xml" dentro da pasta "standalone/configuration":
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

## Para executar o projeto
Depois de executar os passos acima dentro do Wildfly na pasta "bin" executar o seguinte comando:
```
./standalone.sh
```
## Para testar a API
Foi incorporado swagger para testar request a API, é necessário iniciar primeiro os serviços nomeados como "/populate", para acessar o Swagger digite o seguinte endereço:
```
http://localhost:8080/shoppingapi/swagger/#/
```
Obs: Se sua porta for diferente da 8080 é necessário editar um arquivo index.html dentro da pasta "swagger" essa pasta encontra-se dentro da pasta "webapp" do projeto:
```
    <script>
    window.onload = function() {
      // Begin Swagger UI call region
      const ui = SwaggerUIBundle({
        url: "http://localhost:8080/shoppingapi/resources/swagger.json",
        dom_id: '#swagger-ui',
        deepLinking: true,
        presets: [
          SwaggerUIBundle.presets.apis,
          SwaggerUIStandalonePreset
        ],
        plugins: [
          SwaggerUIBundle.plugins.DownloadUrl
        ],
        layout: "StandaloneLayout"
      })
      // End Swagger UI call region

      window.ui = ui
    }
  </script>
```
## Para executar executar o relatório de cobertura e os tests
Na raiz do projeto executar o seguinte comando:
```
mvn package test
```
*Isso irá criar um relatório com JaCoCo e executar os testes do JUnit5.
*Para abrir o relatório de cobertura na raiz do projeto entrar em "target/site/jacoco/index.html"
Obs: Não está com cobertura na camada do repository pois não implementei esses testes ainda.
