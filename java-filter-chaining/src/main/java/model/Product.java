package model;

public class Product {
    private String id;
    private long price;
    private long weight;

    public Product(String id, long price, long weight) {
        this.id = id;
        this.price = price;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public long getPrice() {
        return price;
    }

    public long getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id='" + id + '\'' +
            ", price=" + price +
            ", weight=" + weight +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Product product = (Product) o;

        if (price != product.price)
            return false;
        if (weight != product.weight)
            return false;
        return !(id != null ? !id.equals(product.id) : product.id != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (int) (price ^ (price >>> 32));
        result = 31 * result + (int) (weight ^ (weight >>> 32));
        return result;
    }
}
