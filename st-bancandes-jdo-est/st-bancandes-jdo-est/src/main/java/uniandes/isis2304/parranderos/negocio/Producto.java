package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;

public class Producto implements VOProducto {
    protected BigDecimal id;
    protected Long valorActual;
    protected Timestamp fechaCreacion;
    protected Timestamp fechaUltimoMov;
    protected BigDecimal idOficina;
    protected BigDecimal idUsuario;
    protected String tipoIdUsuario;
    protected LinkedList<Object[]> operaciones;
    protected String estado;

    public BigDecimal getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoIdUsuario() {
        return tipoIdUsuario;
    }

    public void setTipoIdUsuario(String tipoIdUsuario) {
        this.tipoIdUsuario = tipoIdUsuario;
    }

    public BigDecimal getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(BigDecimal idOficina) {
        this.idOficina = idOficina;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Long getValorActual() {
        return valorActual;
    }

    public void setValorActual(BigDecimal valorActual) {
        this.valorActual = valorActual.longValue();
    }

    public void setValorActual(Long valorActual) {
        this.valorActual = valorActual;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(oracle.sql.TIMESTAMP fechaCrecion) {
        try {
            this.fechaCreacion = fechaCrecion.timestampValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public Timestamp getFechaUltimoMov() {
        return fechaUltimoMov;
    }

    public void setFechaUltimoMov(Timestamp fechaUltimoMov) {
        this.fechaUltimoMov = fechaUltimoMov;
    }

    public BigDecimal getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigDecimal usuario) {
        this.idUsuario = usuario;
    }

    public LinkedList<Object[]> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(LinkedList<Object[]> operaciones) {
        this.operaciones = operaciones;
    }

    public Producto() {
        this.id = BigDecimal.valueOf(1);
        this.valorActual = Long.valueOf(1);
        this.fechaCreacion = new Timestamp(System.currentTimeMillis());
        this.fechaUltimoMov = null;
        this.idOficina = BigDecimal.valueOf(1);
        this.idUsuario = BigDecimal.valueOf(1);
        this.tipoIdUsuario = "";
        this.operaciones = new LinkedList<>();
        this.estado = "";
    }

    public Producto(BigDecimal id, Long valorActual, Timestamp fechaCrecion, Timestamp fechaUltimoMov,
            BigDecimal idOficina, BigDecimal usuario, String tipoIdUsuario, LinkedList<Object[]> operaciones,
            String estado) {
        this.id = id;
        this.valorActual = valorActual;
        this.fechaCreacion = fechaCrecion;
        this.fechaUltimoMov = fechaUltimoMov;
        this.idOficina = idOficina;
        this.idUsuario = usuario;
        this.tipoIdUsuario = tipoIdUsuario;
        this.operaciones = operaciones;
        this.estado = estado;
    }

}
