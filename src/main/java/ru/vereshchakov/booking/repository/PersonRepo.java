package ru.vereshchakov.booking.repository;

import ru.vereshchakov.booking.entity.person.Person;

/**
 * Created by dvere on 14.01.2018.
 */
public interface PersonRepo {
    public Person getPersonById(String personId);
}
