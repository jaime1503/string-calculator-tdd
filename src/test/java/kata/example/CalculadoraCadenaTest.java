package kata.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
