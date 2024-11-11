package es.etg.psp.dmc.ngc.crmc.trabajadores.factory;

import es.etg.psp.dmc.ngc.crmc.galerias.Exposicion;

public class Alfarero extends Trabajador{

    private static final int CANT_REPOSICIONES = 10;

    @Override
    public void trabajar() {
        for (int i = 0; i < CANT_REPOSICIONES; i++) {
            try {
                Exposicion.reponer();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
