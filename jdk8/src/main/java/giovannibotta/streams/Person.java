package giovannibotta.streams;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author giovanni
 * @since 3/18/14
 */
public class Person {
    public Person(String name, LocalDate birthday, Sex gender, String email) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
    }

    public enum Sex {
        MALE, FEMALE;

        @Override
        public String toString() {
            if (this == MALE) return "M";
            else return "F";
        }
    }

    private final String name;
    private final LocalDate birthday;
    private final Sex gender;
    private final String email;

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Sex getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                '}';
    }
}
