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
        String separadorPersonalizado = null;

        if (numeros.startsWith("//")) {
            int finCabecera = numeros.indexOf('\n');

            separadorPersonalizado =
                    numeros.substring(2, finCabecera);

            numeros = numeros.substring(finCabecera + 1);

            patronSeparadores = Pattern.quote(separadorPersonalizado);
            longitudSeparador = separadorPersonalizado.length();
        }

        String[] partes = numeros.split(patronSeparadores, -1);

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

            if (separadorPersonalizado != null) {
                int posicionComa = parte.indexOf(',');

                if (posicionComa >= 0) {
                    int posicionError = posicion + posicionComa;

                    return "'" + separadorPersonalizado
                            + "' expected but ',' found at position "
                            + posicionError + ".";
                }
            }

            BigDecimal numero = new BigDecimal(parte);
            suma = suma.add(numero);

            posicion = posicion + parte.length() + longitudSeparador;
        }

        return suma.toPlainString();
    }
}