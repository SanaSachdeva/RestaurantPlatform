import java.util.LinkedList;

public class Order {

    private int orderId;
    private int customerId;
    private LinkedList<MenuItem> items;

    public Order(int orderId, int customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = new LinkedList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LinkedList<MenuItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "\nOrder ID: " + orderId +
                "\nCustomer ID: " + customerId +
                "\nItems: " + items +
                "\n--------------------";
    }
}
