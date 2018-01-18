package ru.vereshchakov.booking.entity.person;

/**
 * Created by dvere on 07.01.2018.
 */
public abstract class Person implements Comparable<Person> {

    private final String id;
    final String name;

    public Person(String id) {
        this(id, "Person");
    }

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    //TODO: write unit-tests for check different situation
    @Override
    public int compareTo(Person person) {

        int nameComparison = this.name.compareTo(person.getName());
        if (nameComparison == 0) {
            return this.id.compareTo(person.getId());
        }
        return nameComparison;
    }
}
