package es.etg.psp.dmc.ngc.crmc.galerias;

public class Exposicion {
    private static final int MIN_OBRAS = 0;
    private static final int MAX_OBRAS = 1;
    private static int contador = 0;

    public static synchronized void reponer() throws InterruptedException{
        if (contador >= MAX_OBRAS)
            Exposicion.class.wait();

        contador++;
        Exposicion.class.notify();
        System.out.println("REPONER");
    }

    public static synchronized void vender() throws InterruptedException{
        if (contador <= MIN_OBRAS)
            Exposicion.class.wait();
            
        contador--;
        Exposicion.class.notify();
        System.out.println("VENDER");
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Exposicion.contador = contador;
    }
}
