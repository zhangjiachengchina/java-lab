package me.zhang.combining;

import me.zhang.combining.observer.Observable;
import me.zhang.combining.observer.Observer;

/**
 * Created by Li on 1/24/2016 1:44 PM.
 */
public class DuckCall implements Quackable {

    Observable observable;

    public DuckCall() {
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("Kwak");
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }

    @Override
    public String toString() {
        return "Duck Call";
    }
}
