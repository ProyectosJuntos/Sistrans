package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;

public class Prestamo extends Producto implements VOPrestamo {
    private Long cuotaMinima;

    public Long getCuotaMinima() {
        return cuotaMinima;
    }

    public void setCuotaMinima(Long cuotaMinima) {
        this.cuotaMinima = cuotaMinima;
    }

    public void setCuotaMinima(BigDecimal cuotaMinima) {
        this.cuotaMinima = cuotaMinima.longValue();
    }

    public Prestamo() {
        super();
        this.cuotaMinima = null;
    }

    public Prestamo(BigDecimal id, Long valorActual, Timestamp fechaCrecion, Timestamp fechaUltimoMov,
            BigDecimal oficina, BigDecimal usuario, String tipoIdUsuario, LinkedList<Object[]> operaciones,
            String estado, Long cuotaMinima) {
        super(id, valorActual, fechaCrecion, fechaUltimoMov, oficina, usuario, tipoIdUsuario, operaciones, estado);
        this.cuotaMinima = cuotaMinima;
    }

    @Override
    public String toString() {
        String resul = "";
        resul += "Id: ";
        resul += id;
        resul += "\t Valor Actual: ";
        resul += valorActual;
        resul += "\t fechaCreacion: ";
        resul += fechaCreacion;
        resul += "\t fechaUltimoMov: ";
        resul += fechaUltimoMov;
        resul += "\t idOficina: ";
        resul += idOficina;
        resul += "\t idUsuario: ";
        resul += idUsuario;
        resul += "\t tipoIdUsuario: ";
        resul += tipoIdUsuario;
        resul += "\t estado: ";
        resul += estado;
        resul += "\t cuotaMinima: ";
        resul += cuotaMinima;

        return resul;
    }
}
