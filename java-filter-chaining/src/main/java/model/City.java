package model;

import java.util.List;

public class City {
    private String id;
    private List<Person> people;

    public City(String id, List<Person> people) {
        this.id = id;
        this.people = people;
    }

    public String getId() {
        return id;
    }

    public List<Person> getPeople() {
        return people;
    }

    @Override
    public String toString() {
        return "City{" +
            "id='" + id + '\'' +
            ", people=" + people +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        City city = (City) o;

        if (id != null ? !id.equals(city.id) : city.id != null)
            return false;
        return !(people != null ? !people.equals(city.people) : city.people != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (people != null ? people.hashCode() : 0);
        return result;
    }
}
