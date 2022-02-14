package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;

public interface VOCuentaJuridica {
    public Long getValorAPagar();

    public String getFrecuencia();

    public LinkedList<Object> getCuentasRecibir();
}
