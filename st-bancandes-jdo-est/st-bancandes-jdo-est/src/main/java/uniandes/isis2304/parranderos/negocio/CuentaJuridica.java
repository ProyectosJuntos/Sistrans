package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;

public class CuentaJuridica extends Cuenta implements VOCuentaJuridica {

    private Long valorAPagar;
    private String frecuencia;
    private LinkedList<Object> cuentasRecibir;

    public Long getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(Long valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public LinkedList<Object> getCuentasRecibir() {
        return cuentasRecibir;
    }

    public void setCuentasRecibir(LinkedList<Object> cuentasRecibir) {
        this.cuentasRecibir = cuentasRecibir;
    }

    public CuentaJuridica(BigDecimal id, Long valorActual, Timestamp fechaCrecion, Timestamp fechaUltimoMov,
            BigDecimal oficina, BigDecimal usuario, String tipoIdUsuario, LinkedList<Object[]> operaciones,
            String estado, String tipoCuenta, Long valorAPagar, String frecuencia, LinkedList<Object> cuentasRecibir) {
        super(id, valorActual, fechaCrecion, fechaUltimoMov, oficina, usuario, tipoIdUsuario, operaciones, estado,
                tipoCuenta);
        this.valorAPagar = valorAPagar;
        this.frecuencia = frecuencia;
        this.cuentasRecibir = cuentasRecibir;
    }

}
