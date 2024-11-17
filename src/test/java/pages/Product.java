package pages;

import java.util.Objects;

public class Product implements Comparable<Product> {
    private final String name;
    private final String description;
    private final String price;

    public Product(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
    @Override
    public int compareTo(Product other) {
        int nameComparison = name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        int descriptionComparison = description.compareTo(other.description);
        if (descriptionComparison != 0) {
            return descriptionComparison;
        }

        return price.compareTo(other.price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
