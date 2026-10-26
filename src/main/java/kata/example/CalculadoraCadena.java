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

        BigDecimal suma = BigDecimal.ZERO;

        for (String parte : partes) {
            BigDecimal num = new BigDecimal(parte);
            suma = suma.add(num);
        }

        return suma.toPlainString();
    }
}
