### API Rest - Delivery

***Desafio mensual de la Todo Code Academy: El objetivo del proyecto consitia en crear un sistema para la venta de comida, donde 
el cliente pudiera seleccionar el producto a adquirir y así realizar la compra.***

### <sub> - Tecnologías aplicadas - </sub>

- Java 17
- Maven
- Spring Boot 2
- JPA / Hibernate
- Hibernate Validation 
- MySql
- Spring Security (basic auth)

### <sub> - Herramientas utilizadas - </sub>

- IntelliJ Idea / Spring Tool Suite 4 - IDE
- Postman
- MySql Workbench

## Decisiones tomadas en el proyecto

 ## <sub> - @RestControllerAdvice y @ExceptionHandler - </sub>
 
 Para la captura de Exceptions decidí utilizar captura de exepciones globales con 'ExceptionHandler' para un codigo más limpio y
 claro sin tener que apelar al abuso de bloques try/catch.
  Aunque no era requerido, tambien decidí implementar Spring Security (basic auth) para el control de roles de usuario y autenticación
  a los diferentes endpoints de la aplicación.
  
## <sub> - Hibernate Validation - </sub>

Para la validaciones de datos decidí utilizar 'Hibernate Validation' el cual porporciona ciertas annotations como '@NotNull'
'@Email' entre otros, para el control de ingreso de datos desde el lado del cliente eh impedir que se guarden datos nulos 
o un formato incorrecto de email.
