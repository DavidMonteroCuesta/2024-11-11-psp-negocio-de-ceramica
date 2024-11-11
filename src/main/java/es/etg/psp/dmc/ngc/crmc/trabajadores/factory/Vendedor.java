package es.etg.psp.dmc.ngc.crmc.trabajadores.factory;

import es.etg.psp.dmc.ngc.crmc.galerias.Exposicion;

public class Vendedor extends Trabajador{

    @Override
    public void trabajar() {
        for (int i = 0; i < 10; i++) {
            try {
                Exposicion.vender();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
