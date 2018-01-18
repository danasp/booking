package ru.vereshchakov.booking.entity.person;

/**
 * Created by dvere on 07.01.2018.
 */
public final class Consumer extends Person {

    public Consumer(String id) {
        this(id, "Consumer");
    }

    public Consumer(String id, String name) {
        super(id, name);
    }

    public String getName() {
        return name;
    }
}
