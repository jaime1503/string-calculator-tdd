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

}
