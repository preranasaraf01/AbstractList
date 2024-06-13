package collection;

import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        // OrderList demonstration
        OrderList<String> orderList = new OrderList<>();
        orderList.add("Order 1");
        orderList.add("Order 2");
        orderList.add("Order 3");

        System.out.println("Orders in OrderList:");
        for (int i = 0; i < orderList.size(); i++) {
            System.out.println(orderList.get(i));
        }

        orderList.remove(1); // Remove "Order 2"
        System.out.println("After removing Order 2:");
        for (int i = 0; i < orderList.size(); i++) {
            System.out.println(orderList.get(i));
        }

        // DeliveryRoute demonstration
        DeliveryRoute<String> deliveryRoute = new DeliveryRoute<>();
        deliveryRoute.add("Point A");
        deliveryRoute.add("Point B");
        deliveryRoute.add("Point C");

        System.out.println("\nDelivery points in DeliveryRoute:");
        ListIterator<String> iterator = deliveryRoute.listIterator(0);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Remove "Point B" by iterating to it and then calling remove()
        iterator = deliveryRoute.listIterator(0);
        while (iterator.hasNext()) {
            String point = iterator.next();
            if (point.equals("Point B")) {
                iterator.remove();
            }
        }

        System.out.println("After removing Point B:");
        iterator = deliveryRoute.listIterator(0);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // LRUCache demonstration
        LRUCache<Integer, String> lruCache = new LRUCache<>(2);
        lruCache.put(1, "Item 1");
        lruCache.put(2, "Item 2");
        System.out.println("\nLRU Cache:");
        System.out.println("Key 1: " + lruCache.get(1)); // Access "Item 1"
        lruCache.put(3, "Item 3"); // This should evict "Item 2"
        System.out.println("Key 2: " + lruCache.get(2)); // Should return null (evicted)
        System.out.println("Key 3: " + lruCache.get(3)); // Should return "Item 3"
    }
}
