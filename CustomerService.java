import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private Map<Integer, Customer> customers = new HashMap<>();

    public void addCustomer(Customer customer) {
        if (customers.containsKey(customer.getId())) {
            throw new DuplicateEntryException("Customer ID already exists");
        }
        customers.put(customer.getId(), customer);
    }

    public Map<Integer, Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomer(int id) {
        Customer c = customers.get(id);
        if (c == null) {
            throw new ResourceNotFoundException("Customer not found");
        }
        return c;
    }

    public void updateCustomer(int id, String name) {
        getCustomer(id).setName(name);
    }

    public void deleteCustomer(int id) {
        if (customers.remove(id) == null) {
            throw new ResourceNotFoundException("Customer not found");
        }
    }
}
