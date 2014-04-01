package giovannibotta.streams;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author giovanni
 * @since 3/18/14
 */
public class PersonTest {
    private List<Person> people;

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
                new Person("Luca", LocalDate.of(1994, 9, 1), Person.Sex.MALE, "giovanni@tinypass.com")
        );
    }

    @Test
    public void testPredicates() {
        Predicate<Person> female = p -> p.getGender().equals(Person.Sex.FEMALE);
        Predicate<Person> age = p -> p.getAge() > 30;
        Comparator<Person> ageComparator = (p1, p2) -> p1.getAge() - p2.getAge();

        Set<String> list = people.stream() // parallelStream()
                .filter(female)
                .filter(age)
                .map(Person::getName)
                .collect(Collectors.toSet());
        System.out.println(list);

        Person min = people.stream().min(ageComparator).get();
        System.out.println(min);

        System.out.println(people.stream().mapToInt(Person::getAge).average().getAsDouble());
    }
}
