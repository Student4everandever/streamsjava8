package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {

        List<Order> orders = new ArrayList<>();

        Item item1 = new Item("Item1", Status.OPENED);
        Item item2 = new Item("Item2", Status.CLOSED);
        Item item3 = new Item("Item3", Status.CLOSED);
        Item item4 = new Item("Item4", Status.IN_PROCESS);
        Item item5 = new Item("Item5", Status.OPENED);
        Item item6 = new Item("Item6", Status.IN_PROCESS);

        Order order1 = new Order("Order1", item1);
        Order order2 = new Order("Order2", item2);
        Order order3 = new Order("Order3", item3);
        Order order4 = new Order("Order4", item4);
        Order order5 = new Order("Order5", item1);
        Order order6 = new Order("Order6", item3);
        Order order7 = new Order("Order7", item5);
        Order order8 = new Order("Order8", item6);
        Order order9 = new Order("Order9", item6);

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);
        orders.add(order7);
        orders.add(order8);
        orders.add(order9);

        List<Item> allItemsNoDuplicates = orders.stream()
                .flatMap(e -> Stream.of(e.getItem()))
                .distinct()
                .collect(Collectors.toList());

        System.out.println("#1 All items:\n" + allItemsNoDuplicates);

        List<Order> ordersWithClosedStatusOnItem = orders.stream()
                .filter(e -> e.getItem().getStatus() == Status.CLOSED)
                .collect(Collectors.toList());

        System.out.println("#2 Items with 'Closed' status:\n" + ordersWithClosedStatusOnItem);

        Map<String, Item> itemsInProcessWithOrderReference = orders.stream()
                .filter(e -> e.getItem().getStatus() == Status.IN_PROCESS)
                .collect(Collectors.toMap(Order::getName, Order::getItem));

        System.out.println("#3.1 Items with status 'In progress' and reference to Order:\n" + itemsInProcessWithOrderReference);

        List<String> stringItemsList = orders.stream()
                .filter(e -> e.getItem().getStatus() == Status.IN_PROCESS)
                .flatMap(e -> Stream.of(e.getItem().toString() + " in " + e.getName()))
                .collect(Collectors.toList());

        System.out.println("#3.2 Items with status 'In progress' and reference to Order:\n" + stringItemsList);

    }
}
