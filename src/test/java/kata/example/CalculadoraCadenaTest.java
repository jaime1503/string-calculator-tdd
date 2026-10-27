package kata.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class CalculadoraCadenaTest {

    @Test
    void CalculadoraCadena() {

        CalculadoraCadena calculdora = new CalculadoraCadena();
        String res = calculdora.add("");
        assertEquals("0", res);
    }

    @Test
    void devuelveElNumeroCuandoLaCadenaContieneUnSoloNumero() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String res = calculadora.add("5");
        assertEquals("5", res);
    }

    @Test
    void devuelveLaSumaDeDosNumerosSeparadosPorComa() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String res = calculadora.add("1,2");
        assertEquals("3", res);
    }

    @Test
    void devuelveLaSumaDeDosNumerosDecimales() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String res = calculadora.add("1.1,2.2");
        assertEquals("3.3", res);
    }

    @Test
    void devuelveLaSumaDeTresNumerosSeparadosPorComa() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String res = calculadora.add("1,2,3");
        assertEquals("6", res);
    }

    @Test
    void devuelveLaSumaCuandoHayComasYSaltosDeLinea() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String res = calculadora.add("1\n2,3");
        assertEquals("6", res);
    }

    @Test
    void devuelveErrorCuandoFaltaUnNumeroEntreComaYSaltoDeLinea() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String res = calculadora.add("175.2,\n35");
        String mensajeEsperado = "Number expected but '\\n' found at position 6.";
        assertEquals(mensajeEsperado, res);
    }

    @Test
    void devuelveErrorCuandoLaCadenaTerminaEnSeparador() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String res = calculadora.add("1,3,");
        assertEquals("Number expected but EOF found.", res);
    }
    @Test
    void devuelveLaSumaConUnSeparadorPersonalizado() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String res = calculadora.add("//;\n1;2");
        assertEquals("3", res);
    }
    @Test
    void devuelveLaSumaConUnaBarraVerticalComoSeparador() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String resultado = calculadora.add("//|\n1|2|3");
        assertEquals("6", resultado);
    }

    @Test
    void devuelveLaSumaConUnSeparadorDeVariosCaracteres() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String resultado = calculadora.add("//sep\n2sep3");
        assertEquals("5", resultado);
    }
    @Test
    void devuelveErrorCuandoSeUsaUnSeparadorDistintoDelDeclarado() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String resultado = calculadora.add("//|\n1|2,3");
        assertEquals("'|' expected but ',' found at position 3.", resultado);
    }

    @Test
    void devuelveErrorCuandoHayUnNumeroNegativo() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String resultado = calculadora.add("-1,2");
        assertEquals("Negative not allowed : -1", resultado);
    }

    @Test
    void devuelveErrorConTodosLosNumerosNegativos() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String resultado = calculadora.add("2,-4,-5");
        assertEquals("Negative not allowed : -4, -5", resultado);
    }

    @Test
    void devuelveLosErroresDeUnNegativoYUnNumeroAusente() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String resultado = calculadora.add("-1,,2");
        String mensajeEsperado = "Negative not allowed : -1\n" + "Number expected but ',' found at position 3.";
        assertEquals(mensajeEsperado, resultado);
    }

    @Test
    void devuelveLosErroresEnElOrdenEnQueAparecen() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String resultado = calculadora.add("-1,,-2");
        String mensajeEsperado = "Negative not allowed : -1\n" + "Number expected but ',' found at position 3.\n" + "Negative not allowed : -2";
        assertEquals(mensajeEsperado, resultado);
    }

    @Test
    void devuelveUnResultadoNumericoAlSumar() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        BigDecimal resultado = calculadora.sumar("1,2,3");
        assertEquals(new BigDecimal("6"), resultado);
    }

    @Test
    void lanzaUnaExcepcionConLosErroresDeLaEntrada() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> calculadora.sumar("-1,,2"));
        String mensajeEsperado = "Negative not allowed : -1\n" + "Number expected but ',' found at position 3.";
        assertEquals(mensajeEsperado, error.getMessage());
    }

    @Test
    void devuelveUnResultadoCorrectoConElValorDeLaSuma() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        ResultadoCalculo resultado = calculadora.sumarConResultado("1,2,3");
        assertTrue(resultado.esCorrecto());
        assertEquals(new BigDecimal("6"), resultado.getValor());
        assertTrue(resultado.getErrores().isEmpty());
    }

    @Test
    void devuelveUnResultadoConErroresCuandoLaEntradaEsInvalida() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        ResultadoCalculo resultado = calculadora.sumarConResultado("-1,,2");
        assertFalse(resultado.esCorrecto());
        assertNull(resultado.getValor());
        assertEquals(Arrays.asList("Negative not allowed : -1", "Number expected but ',' found at position 3."), resultado.getErrores());
    }

    @Test
    void devuelveElProductoDeDosNumeros() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        String resultado = calculadora.multiplicar("2,3");
        assertEquals("6", resultado);
    }

    @ParameterizedTest(name = "Caso de multiplicación {index}")
    @MethodSource("casosDeMultiplicacion")
    void multiplicaRespetandoLasReglas(
            String entrada,
            String esperado) {

        CalculadoraCadena calculadora = new CalculadoraCadena();

        String resultado = calculadora.multiplicar(entrada);

        assertEquals(esperado, resultado);
    }

    static Stream<Arguments> casosDeMultiplicacion() {
        return Stream.of(
                Arguments.of("", "0"), Arguments.of("7", "7"), Arguments.of("2,3,4", "24"), Arguments.of("1.1,2.2", "2.42"), Arguments.of("2\n3,4", "24"),
                Arguments.of("//;\n2;3", "6"), Arguments.of("//|\n2|3|4", "24"), Arguments.of("//sep\n2sep3", "6"),

                Arguments.of("175.2,\n35", "Number expected but '\\n' found at position 6."),
                Arguments.of("1,3,", "Number expected but EOF found."),
                Arguments.of("//|\n1|2,3", "'|' expected but ',' found at position 3."),
                Arguments.of("-1,2", "Negative not allowed : -1"),
                Arguments.of("2,-4,-5", "Negative not allowed : -4, -5"),
                Arguments.of("-1,,2", "Negative not allowed : -1\n" + "Number expected but ',' found at position 3."),
                Arguments.of("-1,,-2", "Negative not allowed : -1\n" + "Number expected but ',' found at position 3.\n" + "Negative not allowed : -2"),
                Arguments.of("2,0,3", "0"),
                Arguments.of("//sep\n2sep", "Number expected but EOF found."));
    }
}
