import java.util.InputMismatchException;
import java.util.Scanner;

public class RestaurantApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MenuService menuService = new MenuService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService(customerService);

        while (true) {
            try {
                System.out.println("\n===== RESTAURANT MANAGEMENT SYSTEM =====");
                System.out.println("1. Menu Management");
                System.out.println("2. Customer Management");
                System.out.println("3. Order Management");
                System.out.println("4. Exit");
                System.out.print("Enter your choice (number): ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> menuMenu(sc, menuService);
                    case 2 -> customerMenu(sc, customerService);
                    case 3 -> orderMenu(sc, menuService, customerService, orderService);
                    case 4 -> {
                        System.out.println("Application closed.");
                        System.exit(0);
                    }
                    default -> throw new MenuSelectionException("Please enter a valid menu number");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input type. Expected a number.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void menuMenu(Scanner sc, MenuService service) {
        System.out.println("\n--- MENU MANAGEMENT ---");
        System.out.println("1. Add Menu Item");
        System.out.println("2. View Menu Items");
        System.out.println("3. Update Menu Item Price");
        System.out.println("4. Delete Menu Item");
        System.out.print("Enter option (number): ");

        int ch = sc.nextInt();

        switch (ch) {
            case 1 -> {
                System.out.print("Enter Menu ID (integer): ");
                int id = sc.nextInt();

                System.out.print("Enter Item Name (text): ");
                String name = sc.next();

                System.out.print("Enter Price (decimal): ");
                double price = sc.nextDouble();

                System.out.print("Enter Category (text): ");
                String category = sc.next();

                service.addMenuItem(new MenuItem(id, name, price, category));
                System.out.println("Menu item added successfully.");
            }
            case 2 -> service.getAllMenuItems().forEach(System.out::println);
            case 3 -> {
                System.out.print("Enter Menu ID (integer): ");
                int id = sc.nextInt();

                System.out.print("Enter New Price (decimal): ");
                double price = sc.nextDouble();

                service.updateMenuItem(id, price);
                System.out.println("Menu item price updated.");
            }
            case 4 -> {
                System.out.print("Enter Menu ID to delete (integer): ");
                service.deleteMenuItem(sc.nextInt());
                System.out.println("Menu item deleted.");
            }
            default -> throw new MenuSelectionException("Invalid Menu Management option");
        }
    }

    private static void customerMenu(Scanner sc, CustomerService service) {
        System.out.println("\n--- CUSTOMER MANAGEMENT ---");
        System.out.println("1. Add Customer");
        System.out.println("2. View Customers");
        System.out.println("3. Update Customer Name");
        System.out.println("4. Delete Customer");
        System.out.print("Enter option (number): ");

        int ch = sc.nextInt();

        switch (ch) {
            case 1 -> {
                System.out.print("Enter Customer ID (integer): ");
                int id = sc.nextInt();

                System.out.print("Enter Customer Name (text): ");
                String name = sc.next();

                System.out.print("Enter Phone Number (text): ");
                String phone = sc.next();

                service.addCustomer(new Customer(id, name, phone));
                System.out.println("Customer added successfully.");
            }
            case 2 -> service.getAllCustomers().values().forEach(System.out::println);
            case 3 -> {
                System.out.print("Enter Customer ID (integer): ");
                int id = sc.nextInt();

                System.out.print("Enter New Name (text): ");
                String name = sc.next();

                service.updateCustomer(id, name);
                System.out.println("Customer updated.");
            }
            case 4 -> {
                System.out.print("Enter Customer ID to delete (integer): ");
                service.deleteCustomer(sc.nextInt());
                System.out.println("Customer deleted.");
            }
            default -> throw new MenuSelectionException("Invalid Customer option");
        }
    }

    private static void orderMenu(Scanner sc, MenuService menuService,
                                  CustomerService customerService,
                                  OrderService orderService) {

        System.out.println("\n--- ORDER MANAGEMENT ---");
        System.out.println("1. Create Order");
        System.out.println("2. View Orders");
        System.out.println("3. Add Item to Order");
        System.out.println("4. Remove Item from Order");
        System.out.println("5. Cancel Order");
        System.out.print("Enter option (number): ");

        int ch = sc.nextInt();

        switch (ch) {
            case 1 -> {
                System.out.println("Available Customers:");
                customerService.getAllCustomers().values().forEach(System.out::println);

                System.out.print("Enter Order ID (integer): ");
                int orderId = sc.nextInt();

                System.out.print("Enter Customer ID (integer): ");
                int customerId = sc.nextInt();

                orderService.createOrder(orderId, customerId);
                System.out.println("Order created successfully.");
            }
            case 2 -> orderService.getAllOrders().values().forEach(System.out::println);
            case 3 -> {
                System.out.println("Available Menu Items:");
                menuService.getAllMenuItems().forEach(System.out::println);

                System.out.print("Enter Order ID (integer): ");
                int orderId = sc.nextInt();

                System.out.print("Enter Menu Item ID (integer): ");
                int itemId = sc.nextInt();

                orderService.addItem(orderId, menuService.getMenuItemById(itemId));
                System.out.println("Item added to order.");
            }
            case 4 -> {
                System.out.print("Enter Order ID (integer): ");
                int orderId = sc.nextInt();

                System.out.print("Enter Menu Item ID (integer): ");
                int itemId = sc.nextInt();

                orderService.removeItem(orderId, menuService.getMenuItemById(itemId));
                System.out.println("Item removed from order.");
            }
            case 5 -> {
                System.out.print("Enter Order ID to cancel (integer): ");
                orderService.cancelOrder(sc.nextInt());
                System.out.println("Order cancelled.");
            }
            default -> throw new MenuSelectionException("Invalid Order option");
        }
    }
}
