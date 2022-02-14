package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Operacion implements VOOperacion {
    private BigDecimal id;
    private String tipoOperacion;
    private Long valor;
    private Timestamp fecha;
    private BigDecimal idPuestoAtencion;
    private BigDecimal idEmpleado;
    private String tipoIdEmpleado;
    private BigDecimal idUsuario;
    private String tipoIdUsuario;
    private BigDecimal cuenta_origen;
    private BigDecimal cuenta_destino;

    public Operacion() {
        this.id = BigDecimal.valueOf(1);
        this.tipoOperacion = "";
        this.valor = Long.valueOf(1);
        this.fecha= new Timestamp(System.currentTimeMillis());
        this.idPuestoAtencion = null;
        this.tipoIdUsuario = "";
        this.idUsuario = null;
        this.cuenta_origen = null;
        this.cuenta_destino = null;
        this.tipoIdEmpleado = "";
        this.idEmpleado = null;
    }



    public Operacion(BigDecimal id, String tipoOperacion, Long valor,
    Timestamp fecha, BigDecimal idPuestoAtencion, BigDecimal idEmpleado, String tipoIdEmpleado, BigDecimal idUsuario,
    String tipoIdUsuario, BigDecimal cuenta_origen, BigDecimal cuenta_destino) {
        this.id = id;
        this.tipoOperacion = tipoOperacion;
        this.valor = valor;
        this.fecha = fecha;
        this.idPuestoAtencion = idPuestoAtencion;
        this.idEmpleado = idEmpleado;
        this.tipoIdEmpleado = tipoIdEmpleado;
        this.idUsuario = idUsuario;
        this.tipoIdUsuario = tipoIdUsuario;
        this.cuenta_origen = cuenta_origen;
        this.cuenta_destino = cuenta_destino;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor.longValue();
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(oracle.sql.TIMESTAMP fecha) {
        try {
            this.fecha= fecha.timestampValue();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public BigDecimal getIdPuestoAtencion() {
        return idPuestoAtencion;
    }

    public void setIdPuestoAtencion(BigDecimal idPuestoAtencion) {
        this.idPuestoAtencion = idPuestoAtencion;
    }

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getTipoIdEmpleado() {
        return tipoIdEmpleado;
    }

    public void setTipoIdEmpleado(String tipoIdEmpleado) {
        this.tipoIdEmpleado = tipoIdEmpleado;
    }

    public BigDecimal getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoIdUsuario() {
        return tipoIdUsuario;
    }

    public void setTipoIdUsuario(String tipoIdUsuario) {
        this.tipoIdUsuario = tipoIdUsuario;
    }

    public BigDecimal getCuenta_origen() {
        return cuenta_origen;
    }

    public void setCuenta_origen(BigDecimal cuenta_origen) {
        this.cuenta_origen = cuenta_origen;
    }

    public BigDecimal getCuenta_destino() {
        return cuenta_destino;
    }

    public void setCuenta_destino(BigDecimal cuenta_destino) {
        this.cuenta_destino = cuenta_destino;
    }

    @Override
    public String toString() {
        String resul = "";
        resul += "Id: ";
        resul += id;
        resul += "\t Tipo Operacion: ";
        resul += tipoOperacion;
        resul += "\t Valor: ";
        resul += valor;
        resul += "\t fecha: ";
        resul += fecha;
        resul += "\t idPuestoatencion: ";
        resul += idPuestoAtencion;
        resul += "\t tipoIdUsuario: ";
        resul += tipoIdUsuario;
        resul += "\t idUsuario: ";
        resul += idUsuario;
        if (!(cuenta_origen== null)){
            resul += "\t cuenta origen: ";
            resul += cuenta_origen;
        }
        if (!(cuenta_destino== null)){
            resul += "\t cuenta destino: ";
            resul += cuenta_destino;
        }
        if (!(tipoIdEmpleado== null)){
            resul += "\t tipoIdEmpleado: ";
            resul += tipoIdEmpleado;
            resul += "\t idEmpleado: ";
            resul += idEmpleado;
        }

        return resul;
    }

}
