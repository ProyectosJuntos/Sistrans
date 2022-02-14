package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;

public class Cuenta extends Producto implements VOCuenta {
    private String tipoCuenta;

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Cuenta() {
        super();
        this.tipoCuenta = "";
    }

    public Cuenta(Producto p, String tipoCuenta) {
        this.id = p.id;
        this.valorActual = p.valorActual;
        this.fechaCreacion = p.fechaCreacion;
        this.fechaUltimoMov = p.fechaUltimoMov;
        this.idOficina = p.idOficina;
        this.idUsuario = p.idUsuario;
        this.tipoIdUsuario = p.tipoIdUsuario;
        this.operaciones = p.operaciones;
        this.estado = p.estado;
        this.tipoCuenta = tipoCuenta;
    }

    public Cuenta(BigDecimal id, Long valorActual, Timestamp fechaCrecion, Timestamp fechaUltimoMov, BigDecimal oficina,
            BigDecimal usuario, String tipoIdUsuario, LinkedList<Object[]> operaciones, String estado,
            String tipoCuenta) {
        super(id, valorActual, fechaCrecion, fechaUltimoMov, oficina, usuario, tipoIdUsuario, operaciones, estado);
        this.tipoCuenta = tipoCuenta;
    }

}
