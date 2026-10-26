package kata.example;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CalculadoraCadena {

    public String add(String numeros) {
        if (numeros.isEmpty()) {
            return "0";
        }

        String patronSeparadores = "[,\n]";
        int longitudSeparador = 1;

        if (numeros.startsWith("//")) {
            int finCabecera = numeros.indexOf('\n');

            String separadorPersonalizado =
                    numeros.substring(2, finCabecera);

            numeros = numeros.substring(finCabecera + 1);

            patronSeparadores = Pattern.quote(separadorPersonalizado);
            longitudSeparador = separadorPersonalizado.length();
        }

        String[] partes = numeros.split(patronSeparadores, -1);

        if (partes.length == 1) {
            return numeros;
        }

        BigDecimal suma = BigDecimal.ZERO;
        int posicion = 0;

        for (String parte : partes) {
            if (parte.isEmpty()) {
                if (posicion == numeros.length()) {
                    return "Number expected but EOF found.";
                }

                char separador = numeros.charAt(posicion);

                String encontrado = separador == '\n'
                        ? "\\n"
                        : String.valueOf(separador);

                return "Number expected but '" + encontrado
                        + "' found at position " + posicion + ".";
            }

            BigDecimal numero = new BigDecimal(parte);
            suma = suma.add(numero);

            posicion = posicion + parte.length() + longitudSeparador;
        }

        return suma.toPlainString();
    }
}