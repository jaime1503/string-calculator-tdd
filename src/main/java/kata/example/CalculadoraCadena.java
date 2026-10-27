package kata.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class CalculadoraCadena {

    public String add(String numeros) {
        return presentarResultado(sumarConResultado(numeros));
    }

    public String multiplicar(String numeros) {
        return presentarResultado(
                calcularConResultado(numeros, true)
        );
    }

    BigDecimal sumar(String numeros) {
        return calcular(numeros, false);
    }

    ResultadoCalculo sumarConResultado(String numeros) {
        return calcularConResultado(numeros, false);
    }

    private ResultadoCalculo calcularConResultado(
            String numeros,
            boolean multiplicacion) {

        try {
            BigDecimal valor = calcular(numeros, multiplicacion);

            return new ResultadoCalculo(
                    valor,
                    Collections.emptyList()
            );
        } catch (IllegalArgumentException error) {
            List<String> errores = Arrays.asList(
                    error.getMessage().split("\n")
            );

            return new ResultadoCalculo(null, errores);
        }
    }

    private String presentarResultado(ResultadoCalculo resultado) {
        if (!resultado.esCorrecto()) {
            return String.join("\n", resultado.getErrores());
        }

        return resultado.getValor().toPlainString();
    }

    private BigDecimal calcular(
            String numeros,
            boolean multiplicacion) {

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

        BigDecimal resultadoNumerico = multiplicacion
                ? BigDecimal.ONE
                : BigDecimal.ZERO;

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
                    } else if (multiplicacion) {
                        resultadoNumerico =
                                resultadoNumerico.multiply(numero);
                    } else {
                        resultadoNumerico =
                                resultadoNumerico.add(numero);
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

        return resultadoNumerico;
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
}