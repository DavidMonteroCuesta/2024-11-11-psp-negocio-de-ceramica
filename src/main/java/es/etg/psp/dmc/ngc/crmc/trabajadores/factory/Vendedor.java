package es.etg.psp.dmc.ngc.crmc.trabajadores.factory;

import es.etg.psp.dmc.ngc.crmc.galerias.Exposicion;

public class Vendedor extends Trabajador{

    private static final int CANT_VENTAS = 10;

    @Override
    public void trabajar() {
        for (int i = 0; i < CANT_VENTAS; i++) {
            try {
                Exposicion.vender();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
