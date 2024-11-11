package es.etg.psp.dmc.ngc.crmc;

import es.etg.psp.dmc.ngc.crmc.trabajadores.TipoTrabajador;
import es.etg.psp.dmc.ngc.crmc.trabajadores.factory.TrabajadorFactory;

public class Negocio implements TrabajadorFactory{
    public static void main(String[] args){
        Thread alfarero = new Thread(TrabajadorFactory.definir(TipoTrabajador.ALFARERO));
        alfarero.start();

        Thread vendedor = new Thread(TrabajadorFactory.definir(TipoTrabajador.VENDEDOR));
        vendedor.start();
    }
}