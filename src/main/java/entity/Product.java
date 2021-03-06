package entity;

import java.util.Objects;

public class Product {
    private int idProduct;
    private String name;
    private double cost;
    private String type;
    private String description;


    public Product() {
    }

    public Product(String name, double cost, String type, String description) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.description = description;
    }

    public Product(int idProduct, String name, double cost, String type, String description) {
        this.idProduct = idProduct;
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.description = description;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return idProduct == product.idProduct &&
                Double.compare(product.cost, cost) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(type, product.type) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProduct, name, cost, type, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
