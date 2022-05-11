package model;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private final List<Person> relatives;
    private final List<Person> friends;
    private String name;
    private int age;
    private Gender gender;

    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        relatives = new ArrayList<>(2);
        friends = new ArrayList<>(2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Person> getRelatives() {
        return relatives;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void addRelative(Person person) {
        relatives.add(person);
    }

    public void addFriend(Person person) {
        friends.add(person);
    }

    public static enum Gender {
        MALE, FEMALE, AGENDER, ANDROGYNOUS, BICURIOUS, CISGENDER, TRANSGENDER
    }

}