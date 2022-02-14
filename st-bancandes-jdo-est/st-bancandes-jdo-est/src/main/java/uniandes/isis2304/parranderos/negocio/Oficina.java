package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.util.LinkedList;

public class Oficina implements VOOficina{
    private BigDecimal id;
    private String nombre;
    private String direccion;
    private Integer numPAtencion;
    private BigDecimal idGerente;
    private String tipoIdGerente;
    private LinkedList<Object[]> puestosAtencion;

    public Oficina(BigDecimal id, String nombre, String direccion, Integer numPAtencion, BigDecimal idGerente,
            String tipoIdGerente, LinkedList<Object[]> puestosAtencion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numPAtencion = numPAtencion;
        this.idGerente = idGerente;
        this.tipoIdGerente = tipoIdGerente;
        this.puestosAtencion = puestosAtencion;
    }

    public String getTipoIdGerente() {
        return tipoIdGerente;
    }

    public void setTipoIdGerente(String tipoIdGerente) {
        this.tipoIdGerente = tipoIdGerente;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getNumPAtencion() {
        return numPAtencion;
    }

    public void setNumPAtencion(Integer numPAtencion) {
        this.numPAtencion = numPAtencion;
    }

    public BigDecimal getidGerente() {
        return idGerente;
    }

    public void setidGerente(BigDecimal idGerente) {
        this.idGerente = idGerente;
    }

    public LinkedList<Object[]> getPuestosAtencion() {
        return puestosAtencion;
    }

    public void setPuestosAtencion(LinkedList<Object[]> puestosAtencion) {
        this.puestosAtencion = puestosAtencion;
    }

}
