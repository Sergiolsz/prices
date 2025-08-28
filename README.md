# Prices Microservice

## Descripción
Servicio Spring Boot que expone un endpoint REST para consultar la tarifa y precio aplicable de un producto en una cadena entre fechas determinadas.  
Se utiliza H2 en memoria y arquitectura Hexagonal + DDD + CQRS.

**Notas:**
- La base de datos en memoria se inicializa automáticamente con 4 registros de ejemplo al arrancar.
- Los logs muestran las peticiones y respuestas para trazabilidad.

## Requisitos previos
- Java 21
- Maven 3.6 o superior
- Git
- IDE (Eclipse, IntelliJ, etc.)
- Postman o curl para probar el endpoint
- Navegador para acceder a la consola H2

---
## Endpoint

- **URL:** `/api/v1/prices`
- **Método:** GET
- **Parámetros:**
    - `brandId` (Long)
    - `productId` (Long)
    - `applicationDate` (ISO date-time)
- **Response:** JSON con precio y datos de la tarifa

**Ejemplo Request:**
```bash
    curl -X GET "http://localhost:8080/api/v1/prices?brandId=1&productId=35455&applicationDate=2020-06-14T16:00:00"
```
- Navegador o Postman:
http://localhost:8080/api/v1/prices?brandId=1&productId=35455&applicationDate=2020-06-14T16:00:00

**Ejemplo Response:**
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45,
  "currency": "EUR"
}
```

## Cómo ejecutar el proyecto

1. Clonar el repositorio:
```bash
    git clone <repository-url>
    cd prices-microservice
```
2. Construir el proyecto con Maven:
```bash
  mvn clean install
```
3. Ejecutar la aplicación:
```bash
  mvn spring-boot:run
```
4. La aplicación estará disponible en `http://localhost:8080`.
5. Acceder a la consola H2 en `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`, User: `sa`, Password: vacío).
6. Probar el endpoint con Postman o curl.

## Tests automáticos incluidos

Validación de los 5 escenarios solicitados:

- 14/06/2020 10:00 → Precio 35.50, Tarifa 1
- 14/06/2020 16:00 → Precio 25.45, Tarifa 2
- 14/06/2020 21:00 → Precio 35.50, Tarifa 1
- 15/06/2020 10:00 → Precio 30.50, Tarifa 3
- 16/06/2020 21:00 → Precio 38.95, Tarifa 4
- Test adicional verifica que los 4 registros se cargan correctamente en H2.
  Para ejecutar los tests:
```bash
    mvn test
```
