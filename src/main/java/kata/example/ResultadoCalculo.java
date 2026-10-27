package kata.example;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class ResultadoCalculo {

    private final BigDecimal valor;
    private final List<String> errores;

    public ResultadoCalculo(BigDecimal valor, List<String> errores) {
        this.valor = valor;
        this.errores = Collections.unmodifiableList(new ArrayList<>(errores));
    }
    public boolean esCorrecto() {
        return errores.isEmpty();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public List<String> getErrores() {
        return errores;
    }
}
