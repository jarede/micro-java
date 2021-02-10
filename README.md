# micro-java
Microserviço em Java, com jax-rs, jetty, Rest Easy

## Comandos

### Iniciar servidor de desenvolvimento

    mvn -Pdev jetty:run

### Executar os testes

    mvn -Pdev test
    mvn -Pdev -Dtest=ServicoTest#iniciarOk test

### Compilar para publicação no Fluig

    mvn -Pprod clean package

### Testando com HTTPie

Anexar arquivo

    http -v --form POST http://localhost:8080/s/i arq=@~/arquivo.txt

### Testando com curl

    curl -F "arq=@~/arquivo.txt" http://localhost:8080/s/i -v -H "Content-Type: application/x-www-form-urlencoded"
