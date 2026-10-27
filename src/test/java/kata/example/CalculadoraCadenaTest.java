package kata.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

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
}
