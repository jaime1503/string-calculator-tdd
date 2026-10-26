package kata.example;

import java.math.BigDecimal;

public class CalculadoraCadena {

    public String add(String numeros) {
        if (numeros.isEmpty()) {
            return "0";
        }

        String[] partes = numeros.split(",");

        if (partes.length == 1) {
            return numeros;
        }

        BigDecimal primerNum = new BigDecimal(partes[0]);
        BigDecimal segundoNum = new BigDecimal(partes[1]);

        BigDecimal suma = primerNum.add(segundoNum);
        return suma.toPlainString();
    }
}
