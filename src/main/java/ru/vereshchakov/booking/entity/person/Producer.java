package ru.vereshchakov.booking.entity.person;

/**
 * Created by dvere on 07.01.2018.
 */
public final class Producer extends Person {

    public Producer(String id) {
        this(id, "Producer");
    }

    public Producer(String id, String name) {
        super(id, name);
    }

    public String getName() {
        return name;
    }

}
