# NEGOCIO DE CERÁMICA PSP
```
git clone https://github.com/DavidMonteroCuesta/2024-11-11-psp-negocio-de-ceramica
```
## DISEÑO Y ANÁLISIS
![DISENYO Y ANALISIS](./imagenes/analisis-disenyo.jpeg)

Este proyecto simula un negocio con dos trabajadores, un alfarero y un vendedor, pensé que como ambos son trabajadores de este negocio esta podría ser su clase padre, que podría implementar de por sí la interfaz *Runnable* y así heredaría las funciones de dicha interfaz a sus hijos, permitiéndoles adquirir la función de hilos. Esta clase también contendría un método abstracto, trabajar, un método vacío que ganaría una función dependiendo del hijo, en el caso de la clase *Alfarero* este llamará al método *reponer()* en la clase *Exposicion*, y en la clase *Vendedor* a *vender()*. Puesto que este proyecto busca que el alfarero cree 10 obras y el vendedor las venda, limitando el número simultáneo de obras en exposición a 1, introduje un for en el método *trabar()* de *Alfarero* y *Vendedor*, de tal forma que cada uno llame a su respectivo método de la clase *Exposición* 10 veces, además estos métodos pondrán el hilo que haga falta en espera para que el atributo *contador* no suba de 1 o baje de 0.

**LA CLASE TRABAJADOR DEBERÍA SER ABSRACTA, AL PARECER NO LO ESPECIFIQUÉ EN EL DISEÑO, ME DISCULPO, SERÍA SIMPLEMENTE CAMBIAR LA *C* POR UNA *A***

## CÓDIGO

### CLASE NEGOCIO
```
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
```

### CLASE EXPOSICIÓN
```
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
```

### ENUM TIPO TRABAJADOR
```
package es.etg.psp.dmc.ngc.crmc.trabajadores;

public enum TipoTrabajador {
    ALFARERO,
    VENDEDOR
}
```

### CLASE TRABAJADOR
```
package es.etg.psp.dmc.ngc.crmc.trabajadores.factory;

public abstract class Trabajador implements Runnable{
    public abstract void trabajar();

    @Override
    public void run() {
        trabajar();
    }
}
```

### CLASE ALFARERO
```
package es.etg.psp.dmc.ngc.crmc.trabajadores.factory;

import es.etg.psp.dmc.ngc.crmc.galerias.Exposicion;

public class Alfarero extends Trabajador{

    @Override
    public void trabajar() {
        for (int i = 0; i < 10; i++) {
            try {
                Exposicion.reponer();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
```

### CLASE VENDEDOR
```
ppackage es.etg.psp.dmc.ngc.crmc.trabajadores.factory;

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
```

### INTERFAZ TRABAJADOR FACTORY
```
package es.etg.psp.dmc.ngc.crmc.trabajadores.factory;

import es.etg.psp.dmc.ngc.crmc.trabajadores.TipoTrabajador;

public interface TrabajadorFactory {
    public static Trabajador definir(TipoTrabajador tipo){
        if (tipo == TipoTrabajador.ALFARERO) return new Alfarero();
        return new Vendedor();
    }
}
```