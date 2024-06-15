package patitotrains.model.messenger;

import patitotrains.model.observer.Observer;
import raul.Model.array.Array;

import java.io.Serializable;

public class Messenger implements Serializable {
    private String message;
    private Array<Observer<Messenger>> observers;

    public Messenger(String message) {
        this.message = message;
        observers = new Array<>(10);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyAllObservers();
    }

    public void attach(Observer<Messenger> observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        observers.forEach(observer -> {
            if (observer != null) {
                observer.update();
            }
            return null;
        });
    }

}
