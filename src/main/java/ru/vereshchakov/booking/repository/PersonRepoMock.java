package ru.vereshchakov.booking.repository;

import org.springframework.stereotype.Repository;
import ru.vereshchakov.booking.entity.person.Consumer;
import ru.vereshchakov.booking.entity.person.Person;
import ru.vereshchakov.booking.entity.person.Producer;

import java.util.Random;

/**
 * Created by dvere on 14.01.2018.
 */
@Repository(value = "personRepoMock")
public class PersonRepoMock implements PersonRepo {

    @Override
    public Person getPersonById(String personId) {
        switch (personId) {
            case "1":
                return createProducer(personId);
            case "2":
                return createConsumer(personId);
            default:
                return new Random().nextBoolean()
                        ? createProducer(personId)
                        : createConsumer(personId);
        }
    }

    private Person createProducer(String personId) {
        return new Producer(personId, "Danila Vereshchakov");
    }

    private Person createConsumer(String personId) {
        return new Random().nextBoolean()
                ? new Consumer(personId, "Anna Vereshchakova")
                : new Consumer(personId, "Ivan Vereshchakov");
    }
}
