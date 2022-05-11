package functional;

import model.Person;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalApp {
    public static void main(String[] args) {
        // populate people
        List<Person> personList = Arrays.asList(
                new Person("Alex", 30, Person.Gender.MALE),
                new Person("Alexandra", 29, Person.Gender.FEMALE),
                new Person("Bob", 20, Person.Gender.MALE),
                new Person("Bea", 26, Person.Gender.FEMALE),
                new Person("chen", 20, Person.Gender.AGENDER),
                new Person("Cathie", 47, Person.Gender.AGENDER),
                new Person("David", 28, Person.Gender.AGENDER),
                new Person("Deb", 45, Person.Gender.AGENDER),
                new Person("Edward", 35, Person.Gender.CISGENDER),
                new Person("Elie", 45, Person.Gender.CISGENDER),
                new Person("Fahad", 55, Person.Gender.CISGENDER),
                new Person("Fatima", 39, Person.Gender.CISGENDER),
                new Person("Galal", 39, Person.Gender.TRANSGENDER),
                new Person("Gorkem", 54, Person.Gender.TRANSGENDER),
                new Person("Hassan", 23, Person.Gender.TRANSGENDER),
                new Person("Harnek", 52, Person.Gender.TRANSGENDER),
                new Person("Horace", 42, Person.Gender.TRANSGENDER)
        );
        // establish relationship Imperatively
        for (int i = 0; i < personList.size(); i++) {
            for (int z = i + 1, count = 0; z < personList.size() && count < 4; z++, count++) {
                if (count == 0 | count == 1) {
                    personList.get(i)
                              .addRelative(personList.get(z));
                } else if (count == 2 | count == 3) {
                    personList.get(i)
                              .addFriend(personList.get(z));
                }
            }
        }

        // ---------------------------------------

        // transgender list
        List<Person> transgenderList = personList.stream()
                                                 .filter(p -> p.getGender() == Person.Gender.TRANSGENDER)
                                                 .collect(Collectors.toList());
        transgenderList.forEach(p -> System.out.printf("Name: %s | Gender: %s\n", p.getName(), p.getGender()));

        // identify users that have multi gendered connections
        List<Person> multiGenderedList = personList
                .stream()
                .filter(p -> {
                    // check count of distinct genders
                    long countOfDistinctGenders = Stream.of(p.getRelatives(), p.getFriends())
                                                        .flatMap(Collection::stream)
                                                        .map(Person::getGender)
                                                        .distinct()
                                                        .count();
                    return countOfDistinctGenders > 1;
                })
                .collect(Collectors.toList());

        multiGenderedList.forEach(person -> {
            System.out.println("Multi Gendered");
            System.out.println("---------------------");
            System.out.println(person.getName());
            System.out.println("*********************");
            for (Person relative : person.getRelatives())
                System.out.printf("Name: %s | Gender: %s\n", relative.getName(), relative.getGender());
            for (Person relative : person.getFriends())
                System.out.printf("Name: %s | Gender: %s\n", relative.getName(), relative.getGender());
            System.out.println("---------------------");
        });
    }
}
