package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.util.LinkedList;

public class PuestoAtencion implements VOPuestoAtencion {
    private BigDecimal id;
    private String tipoPuesto;
    private String localizacion;
    private Oficina oficina;
    private BigDecimal idCajero;
    private String tipoIdCajero;
    private LinkedList<Object[]> operaciones;

    public BigDecimal getId() {
        return id;
    }

    public String getTipoIdCajero() {
        return tipoIdCajero;
    }

    public void setTipoIdCajero(String tipoIdCajero) {
        this.tipoIdCajero = tipoIdCajero;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTipoPuesto() {
        return tipoPuesto;
    }

    public void setTipoPuesto(String tipoPuesto) {
        this.tipoPuesto = tipoPuesto;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public BigDecimal getIdCajero() {
        return idCajero;
    }

    public void setCajero(BigDecimal cajero) {
        this.idCajero = cajero;
    }

    public LinkedList<Object[]> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(LinkedList<Object[]> operaciones) {
        this.operaciones = operaciones;
    }

    public PuestoAtencion(BigDecimal id, String tipoPuesto, String localizacion, Oficina oficina, BigDecimal cajero,
            String tipoIdCajero, LinkedList<Object[]> operaciones) {
        this.id = id;
        this.tipoPuesto = tipoPuesto;
        this.localizacion = localizacion;
        this.oficina = oficina;
        this.idCajero = cajero;
        this.tipoIdCajero = tipoIdCajero;
        this.operaciones = operaciones;
    }

}
