package es.etg.psp.dmc.ngc.crmc.trabajadores.factory;

public abstract class Trabajador implements Runnable{
    public abstract void trabajar();

    @Override
    public void run() {
        trabajar();
    }
}
