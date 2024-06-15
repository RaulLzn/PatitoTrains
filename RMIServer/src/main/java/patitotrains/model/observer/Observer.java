package patitotrains.model.observer;

import java.io.Serializable;

public abstract class Observer<E extends Serializable> implements Serializable {

    //Este atributo es el sujeto que el observer observa que es de tipo generico
    protected E subject;

    //Esto sive para que el observer tenga un sujeto al cual observar
    protected Observer(E subject) {
        this.subject = subject;
    }

    //Este metodo se encarga de actualizar la informacion del observer
    public abstract void update();
}
