# String Calculator — Práctica de TDD

Calculadora de cadenas desarrollada en Java con Maven y JUnit 5, siguiendo el ciclo RED → GREEN → REFACTOR.

## Funcionalidades

- Suma y multiplicación de una cantidad variable de números.
- Soporte para números decimales mediante `BigDecimal`.
- Separadores por coma, salto de línea y delimitadores personalizados.
- Detección de números ausentes y entradas terminadas en separador.
- Rechazo de números negativos, indicando los encontrados.
- Devolución de múltiples errores en su orden de aparición.

Las posiciones de los errores se cuentan desde cero dentro de la cadena de números, excluyendo la cabecera del separador personalizado.

## Organización

- `CalculadoraCadena`: operaciones, lectura de entradas y validaciones.
- `ResultadoCalculo`: contiene el valor calculado o una lista de errores.
- `CalculadoraCadenaTest`: pruebas unitarias y parametrizadas.

La suma interna devuelve un `BigDecimal`. Se exploran dos formas de comunicar errores: mediante excepciones y mediante un objeto de resultado. Los métodos `add` y `multiplicar` devuelven texto.

## Ejecutar las pruebas

1. Abrir el proyecto en IntelliJ IDEA.
2. Configurar un JDK compatible; se recomienda Java 17 o superior.
3. Cargar las dependencias de Maven.
4. Hacer clic derecho sobre `src/test/java` y ejecutar todas las pruebas.

La batería actual contiene **38 casos de prueba**.

## Proceso TDD

El historial contiene commits separados para las pruebas en RED, las implementaciones en GREEN y las refactorizaciones. También incluye pruebas adicionales de comportamientos que la implementación ya admitía.