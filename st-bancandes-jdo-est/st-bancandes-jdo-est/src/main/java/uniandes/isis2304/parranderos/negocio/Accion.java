package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public class Accion implements VOAccion {
    private BigDecimal id;
    private String compania;
    private BigDecimal valorAccion;
    private BigDecimal idCliente;
    private String tipoIdCliente;
    private BigDecimal idCuentaRegistrada;

    public BigDecimal getId() {
        return id;
    }

    public String getTipoIdCliente() {
        return tipoIdCliente;
    }

    public void setTipoIdCliente(String tipoIdCliente) {
        this.tipoIdCliente = tipoIdCliente;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public BigDecimal getValorAccion() {
        return valorAccion;
    }

    public void setValorAccion(BigDecimal valorAccion) {
        this.valorAccion = valorAccion;
    }

    public BigDecimal getCliente() {
        return idCliente;
    }

    public void setCliente(BigDecimal idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getCuentaRegistrada() {
        return idCuentaRegistrada;
    }

    public void setCuentaRegistrada(BigDecimal idCuentaRegistrada) {
        this.idCuentaRegistrada = idCuentaRegistrada;
    }

    public Accion(BigDecimal id, String compania, BigDecimal valorAccion, BigDecimal idCliente, String tipoIdCliente,
            BigDecimal idCuentaRegistrada) {
        this.id = id;
        this.compania = compania;
        this.valorAccion = valorAccion;
        this.idCliente = idCliente;
        this.tipoIdCliente = tipoIdCliente;
        this.idCuentaRegistrada = idCuentaRegistrada;
    }

}
