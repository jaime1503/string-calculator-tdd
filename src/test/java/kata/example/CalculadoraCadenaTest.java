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
}
