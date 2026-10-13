# Cálculo de Comisiones

# Despliegue

https://javaspringboot-core.onrender.com

# Video
https://www.youtube.com/watch?v=3YWeTPzIXoQ


## Descripción

Aplicación web desarrollada en **Java Spring Boot** que calcula comisiones de vendedores según sus ventas en un rango de fechas. El usuario selecciona fecha de inicio y fin, y el sistema agrupa las ventas por vendedor, aplica la regla de comisión correspondiente y muestra el resultado en tabla.

## Tecnologías

- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- Thymeleaf (motor de plantillas)
- H2 (base de datos en memoria)
- Lombok
- Maven
- Docker

## Arquitectura MVC

- **Model** → `Vendedor`, `Venta`, `Regla`
- **Repository** → `VentaRepository`, `ReglaRepository`
- **Service** → `ComisionService` (interfaz) + `ComisionServiceImpl` (Clean Code)
- **DTO** → `ComisionDTO`
- **Controller** → `ComisionController`
- **View** → `index.html` (Thymeleaf, dark theme)

## Instalación y ejecución local

Requisitos: Java 17+, Maven 3.8+

```bash
git clone https://github.com/Miguezx21/JavaSpringBoot_CORE.git
cd JavaSpringBoot_CORE
mvn spring-boot:run
```

Abrir en el navegador: `http://localhost:8080`

## Despliegue en Render

El proyecto incluye `Dockerfile` listo para Render (free tier).

1. Conectar el repo en [render.com](https://render.com)
2. New → Web Service → Runtime: Docker → Branch: `master`
3. Render detecta el Dockerfile y despliega automáticamente

> ⚠️ El free tier duerme tras 15 min de inactividad. El primer request tarda ~30s en despertar.

## Datos de prueba

Hardcodeados en `DataLoader.java`, cargados al iniciar la app:

| Vendedor     | Fecha      | Monto      |
|--------------|------------|------------|
| Juan Perez   | 01/10/2026 | $800.00    |
| Juan Perez   | 05/10/2026 | $300.50    |
| Juan Perez   | 12/10/2026 | $5,000.00  |
| Maria Garcia | 03/10/2026 | $1,500.00  |
| Maria Garcia | 08/10/2026 | $600.00    |
| Pedro Lopez  | 06/10/2026 | $450.00    |

Rango de prueba recomendado: `2026-10-01` → `2026-10-12`

**Reglas de comisión:**
- ≥ $2,000 → 10%
- ≥ $1,000 → 5%
- ≥ $500 → 2.5%
- < $500 → sin comisión

## Estructura del proyecto

```
src/main/java/com/comisiones/calculocomisiones/
├── config/DataLoader.java
├── controller/ComisionController.java
├── dto/ComisionDTO.java
├── model/
│   ├── Vendedor.java
│   ├── Venta.java
│   └── Regla.java
├── repository/
│   ├── VentaRepository.java
│   └── ReglaRepository.java
└── service/
    ├── ComisionService.java
    └── impl/
        └── ComisionServiceImpl.java
```
