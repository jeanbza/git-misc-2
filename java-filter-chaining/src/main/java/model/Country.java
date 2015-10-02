package model;

import java.util.List;

public class Country {
    private String id;
    private List<City> cities;

    public Country(String id, List<City> cities) {
        this.id = id;
        this.cities = cities;
    }

    public String getId() {
        return id;
    }

    public List<City> getCities() {
        return cities;
    }

    @Override
    public String toString() {
        return "Country{" +
            "id='" + id + '\'' +
            ", cities=" + cities +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Country country = (Country) o;

        if (id != null ? !id.equals(country.id) : country.id != null)
            return false;
        return !(cities != null ? !cities.equals(country.cities) : country.cities != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cities != null ? cities.hashCode() : 0);
        return result;
    }
}
