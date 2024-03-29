package streams;

import java.util.List;
import java.util.Objects;

public class Order {

    private String name;
    private List<Item> item;

    public Order(String name, List<Item> item) {
        this.name = name;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(name, order.name) &&
                Objects.equals(item, order.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, item);
    }

    @Override
    public String toString() {
        return name + item;
    }
}
