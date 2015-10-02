package model;

public class City {
    private String id;
    private boolean hasOutdoorActivities;

    public City(String id, boolean hasOutdoorActivities) {
        this.id = id;
        this.hasOutdoorActivities = hasOutdoorActivities;
    }

    public String getId() {
        return id;
    }

    public boolean hasOutdoorActivities() {
        return hasOutdoorActivities;
    }

    @Override
    public String toString() {
        return "City{" +
            "id='" + id + '\'' +
            ", hasOutdoorActivities=" + hasOutdoorActivities +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        City city = (City) o;

        if (hasOutdoorActivities != city.hasOutdoorActivities)
            return false;
        return !(id != null ? !id.equals(city.id) : city.id != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (hasOutdoorActivities ? 1 : 0);
        return result;
    }
}
