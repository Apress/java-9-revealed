// Person.java
package com.jdojo.person;

import com.jdojo.address.Address;

public class Person {
    private long personId;
    private String firstName;
    private String lastName;
    private Address address = new Address();

    public Person(long personId, String firstName, 
                  String lastName) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "[Person Id:" + personId + 
               ", First Name:" + firstName + 
               ", Last Name:" + lastName + 
               ", Address:" + address + "]";
    }
}
