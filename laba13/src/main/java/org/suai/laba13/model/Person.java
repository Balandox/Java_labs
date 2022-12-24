package org.suai.laba13.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private List<String> phoneList;

    public Person(int id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneList = new ArrayList<>();
    }

    public Person(){
        this.phoneList = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(obj instanceof Person){
            Person person = (Person) obj;
            if(Objects.equals(person.getFirstName(), this.getFirstName())
            && Objects.equals(person.getLastName(), this.getLastName()))
                return true;
        }
        return false;
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

    public List<String> getPhoneList() {
        return phoneList;
    }

    public String toString(){
        return this.firstName + " " + this.lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
