package giovannibotta.lambda;

import com.google.common.collect.ImmutableList;
import giovannibotta.streams.Person;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * @author giovanni
 * @since 4/25/14
 */
public class FunWithLambdas {
    private Collection<Person> people;

    @Before
    public void createPeople() {
        people = ImmutableList.of(
                new Person("Giovanni", LocalDate.of(1982, 1, 3), Person.Sex.MALE, "giovanni@tinypass.com"),
                new Person("Anna", LocalDate.of(1978, 5, 23), Person.Sex.FEMALE, "giovanni@tinypass.com"),
                new Person("Paolo", LocalDate.of(1999, 11, 11), Person.Sex.MALE, "giovanni@tinypass.com"),
                new Person("Marco", LocalDate.of(1984, 4, 6), Person.Sex.MALE, "giovanni@tinypass.com"),
                new Person("Matteo", LocalDate.of(1972, 8, 19), Person.Sex.MALE, "giovanni@tinypass.com"),
                new Person("Mario", LocalDate.of(2001, 6, 30), Person.Sex.MALE, "giovanni@tinypass.com"),
                new Person("Giorgio", LocalDate.of(1959, 7, 19), Person.Sex.MALE, "giovanni@tinypass.com"),
                new Person("Luca", LocalDate.of(1994, 9, 1), Person.Sex.MALE, "giovanni@tinypass.com"));
    }

    @Test
    public void test() {
        ArrayList<Person> peopleList = new ArrayList<>(people);
        Collection<Person> collection = peopleList;
        Optional<Person> optionalFirst = collection.parallelStream().filter(e -> e.getAge() > 18).findFirst();
        System.out.println(optionalFirst);

        peopleList.removeIf(p -> p.getAge() > 10);

        System.out.println(peopleList);


    }
}
