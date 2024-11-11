package es.etg.psp.dmc.ngc.crmc.trabajadores.factory;

import es.etg.psp.dmc.ngc.crmc.trabajadores.TipoTrabajador;

public interface TrabajadorFactory {
    public static Trabajador definir(TipoTrabajador tipo){
        if (tipo == TipoTrabajador.ALFARERO) return new Alfarero();
        return new Vendedor();
    }
}
