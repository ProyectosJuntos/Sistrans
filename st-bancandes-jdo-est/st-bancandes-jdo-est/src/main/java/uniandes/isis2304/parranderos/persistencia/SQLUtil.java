
package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLUtil {
        private final static String SQL = PersistenciaBancandes.SQL;

        private PersistenciaBancandes pp;

        public SQLUtil(PersistenciaBancandes pp) {
                this.pp = pp;
        }

        public long nextval(PersistenceManager pm) {
                Query q = pm.newQuery(SQL, "SELECT " + pp.darSeqBancandes() + ".nextval FROM DUAL");
                q.setResultClass(Long.class);
                long resp = (long) q.executeUnique();
                return resp;
        }

        public long[] limpiarBancandes(PersistenceManager pm) {
                Query qAccion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAccion());
                Query qAFC = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAFC());
                Query qCDT = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCDT());
                Query qCuenta = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCuenta());
                Query qEmpleado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpleado());
                Query qOficina = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOficina());
                Query qOperacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperacion());
                Query qPrestamo = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPrestamo());
                Query qProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto());
                Query qPuestoAtencion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPuestoAtencion());
                Query qUsuario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario());

                long accionEliminados = (long) qAccion.executeUnique();
                long AFCEliminados = (long) qAFC.executeUnique();
                long CDTEliminados = (long) qCDT.executeUnique();
                long cuentaAhorrosEliminados = (long) qCuenta.executeUnique();
                long empleadoEliminados = (long) qEmpleado.executeUnique();
                long oficinaEliminados = (long) qOficina.executeUnique();
                long operacionEliminados = (long) qOperacion.executeUnique();
                long prestamoEliminados = (long) qPrestamo.executeUnique();
                long productoEliminados = (long) qProducto.executeUnique();
                long puestoAtencionEliminados = (long) qPuestoAtencion.executeUnique();
                long usuarioEliminados = (long) qUsuario.executeUnique();
                return new long[] { accionEliminados, AFCEliminados, CDTEliminados, cuentaAhorrosEliminados,
                                empleadoEliminados, oficinaEliminados, operacionEliminados, prestamoEliminados,
                                productoEliminados, puestoAtencionEliminados, usuarioEliminados };
        }

}
