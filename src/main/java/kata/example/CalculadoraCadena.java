package kata.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Collections;

public class CalculadoraCadena {

    public String add(String numeros) {
        try {
            return sumar(numeros).toPlainString();
        } catch (IllegalArgumentException error) {
            return error.getMessage();
        }
    }

    BigDecimal sumar(String numeros) {
        if (numeros.isEmpty()) {
            return BigDecimal.ZERO;
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

        List<String> negativos = new ArrayList<>();
        List<String> errores = new ArrayList<>();

        for (String parte : partes) {
            if (parte.isEmpty()) {
                registrarNegativos(negativos, errores);

                errores.add(
                        crearMensajeNumeroAusente(numeros, posicion)
                );
            } else {
                int posicionComa = parte.indexOf(',');

                if (separadorPersonalizado != null
                        && posicionComa >= 0) {
                    registrarNegativos(negativos, errores);

                    int posicionError = posicion + posicionComa;

                    errores.add(
                            "'" + separadorPersonalizado
                                    + "' expected but ',' found at position "
                                    + posicionError + "."
                    );
                } else {
                    BigDecimal numero = new BigDecimal(parte);

                    if (numero.signum() < 0) {
                        negativos.add(parte);
                    } else {
                        suma = suma.add(numero);
                    }
                }
            }

            posicion = posicion + parte.length() + longitudSeparador;
        }

        registrarNegativos(negativos, errores);

        if (!errores.isEmpty()) {
            throw new IllegalArgumentException(
                    String.join("\n", errores)
            );
        }

        return suma;
    }

    private void registrarNegativos(
            List<String> negativos,
            List<String> errores) {

        if (!negativos.isEmpty()) {
            errores.add(
                    "Negative not allowed : "
                            + String.join(", ", negativos)
            );

            negativos.clear();
        }
    }

    private String crearMensajeNumeroAusente(
            String numeros,
            int posicion) {

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

    ResultadoCalculo sumarConResultado(String numeros) {
        BigDecimal valor = sumar(numeros);
        return new ResultadoCalculo(valor, Collections.emptyList());
    }
}