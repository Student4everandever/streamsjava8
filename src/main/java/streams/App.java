package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {

        List<Order> orders = new ArrayList<>();

        Item item1 = new Item("Item1", Status.OPENED);
        Item item2 = new Item("Item2", Status.CLOSED);
        Item item3 = new Item("Item3", Status.CLOSED);
        Item item4 = new Item("Item4", Status.IN_PROCESS);
        Item item5 = new Item("Item5", Status.OPENED);
        Item item6 = new Item("Item6", Status.IN_PROCESS);

        List<Item> list1 = new ArrayList<>();
        list1.add(item1);
        list1.add(item3);
        list1.add(item5);
        List<Item> list2 = new ArrayList<>();  //No closed status
        list2.add(item1);
        list2.add(item6);
        List<Item> list3 = new ArrayList<>();
        list3.add(item1);
        list3.add(item4);
        List<Item> list4 = new ArrayList<>();
        list4.add(item2);
        list4.add(item3);
        list4.add(item4);
        List<Item> list5 = new ArrayList<>();
        list5.add(item2);
        list5.add(item3);
        List<Item> list6 = new ArrayList<>();   //No closed status
        list6.add(item4);
        list6.add(item6);
        List<Item> list7 = new ArrayList<>();
        list7.add(item5);


        Order order1 = new Order("Order1", list1);
        Order order2 = new Order("Order2", list2);   //No closed status
        Order order3 = new Order("Order3", list3);
        Order order4 = new Order("Order4", list4);
        Order order5 = new Order("Order5", list5);
        Order order6 = new Order("Order6", list6);   //No closed status
        Order order7 = new Order("Order7", list7);
        Order order8 = new Order("Order8", list2);
        Order order9 = new Order("Order9", list5);

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);
        orders.add(order7);
        orders.add(order8);
        orders.add(order9);


        System.out.println("List of all orders:");
        for (Order order : orders) {
            System.out.println(order);
        }

        List<Item> allItemsNoDuplicates = orders.stream()
                .flatMap(e -> e.getItem().stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("\n #1 All items without duplicates:\n" + allItemsNoDuplicates + "\n");

        List<Order> ordersWithClosedStatusOnItem = orders.stream()
                .filter(e -> e.getItem().stream().anyMatch(item -> item.getStatus() == Status.CLOSED))
                .collect(Collectors.toList());

        System.out.println("#2 Orders that have items with 'Closed' status:");
        for (Order order : ordersWithClosedStatusOnItem) {
            System.out.println(order);
        }

        // список айтемов с зааднным статусом, с указанием, в каком(-их) ордерах этот айтем фигурирует

        Map<String, List<Item>> itemsListWithReferenceToOrder = orders.stream()
                .filter(e -> e.getItem().stream()
                        .anyMatch(item -> item.getStatus() == Status.IN_PROCESS))
                .collect(Collectors
                        .toMap(Order::getName, r -> r.getItem().stream()
                                .filter(item -> item.getStatus() == Status.IN_PROCESS)
                                .collect(Collectors.toList())));

        System.out.println("\n #3 Items with status 'In progress' and reference to Order:");
        for (Map.Entry<String, List<Item>> item : itemsListWithReferenceToOrder.entrySet()) {
            System.out.println(item);
        }
    }

}
