package kata.example;

import java.math.BigDecimal;

public class CalculadoraCadena {

    public String add(String numeros) {
        if (numeros.isEmpty()) {
            return "0";
        }

        String[] partes = numeros.split("[,\n]");

        if (partes.length == 1) {
            return numeros;
        }

        BigDecimal suma = BigDecimal.ZERO;
        int posicion = 0;

        for (String parte : partes) {
            if (parte.isEmpty()) {
                char separador = numeros.charAt(posicion);

                String encontrado = separador == '\n' ? "\\n" : String.valueOf(separador);

                return "Number expected but '" + encontrado + "' found at position " + posicion + ".";
            }

            BigDecimal numero = new BigDecimal(parte);
            suma = suma.add(numero);

            posicion = posicion + parte.length() + 1;
        }

        return suma.toPlainString();
    }
}
