package com.wakeparkby.Observer;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    private static List<Observer> observers = new ArrayList<>();
    private static int id;
    String name;
    int status = 10;

    /**
     * this constructor with one par
     *
     * @param name name of observer
     */

    public Observer(String name) {
        this.name = name;
        observers.add(this);
    }

    /**
     * this setter for status
     *
     * @param status of observer
     */

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * his setter for id
     *
     * @param id is id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * this is getter for id
     *
     * @return id of observer
     */

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }


    /**
     * this func notify observers by id
     *
     * @param id id of notified observer
     */

    public void notifyAllObservers(int id) {
        this.setId(id);
        for (Observer observer : observers) {
            observer.update();
        }
    }


    public void removeFromList(Observer observer) {
        observers.remove(observer);
    }

    /**
     * is abstract method for update in observer
     */

    public void update() {
    }
}
