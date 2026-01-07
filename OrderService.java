import java.util.HashMap;
import java.util.Map;

public class OrderService {

    private Map<Integer, Order> orders = new HashMap<>();
    private CustomerService customerService;

    public OrderService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void createOrder(int orderId, int customerId) {
        if (orders.containsKey(orderId)) {
            throw new DuplicateEntryException("Order ID already exists");
        }
        customerService.getCustomer(customerId);
        orders.put(orderId, new Order(orderId, customerId));
    }

    public Order getOrder(int orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new ResourceNotFoundException("Order not found");
        }
        return order;
    }

    public void addItem(int orderId, MenuItem item) {
        getOrder(orderId).getItems().add(item);
    }

    public void removeItem(int orderId, MenuItem item) {
        getOrder(orderId).getItems().remove(item);
    }

    public Map<Integer, Order> getAllOrders() {
        return orders;
    }

    public void cancelOrder(int orderId) {
        if (orders.remove(orderId) == null) {
            throw new ResourceNotFoundException("Order not found");
        }
    }
}
