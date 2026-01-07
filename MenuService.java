import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuService {

    private List<MenuItem> menuItems = new ArrayList<>();
    private Set<String> categories = new HashSet<>();

    public void addMenuItem(MenuItem item) {
        for (MenuItem m : menuItems) {
            if (m.getId() == item.getId()) {
                throw new DuplicateEntryException("Menu Item ID already exists");
            }
        }
        menuItems.add(item);
        categories.add(item.getCategory());
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItems;
    }

    public MenuItem getMenuItemById(int id) {
        for (MenuItem m : menuItems) {
            if (m.getId() == id) {
                return m;
            }
        }
        throw new ResourceNotFoundException("Menu Item not found");
    }

    public void updateMenuItem(int id, double price) {
        getMenuItemById(id).setPrice(price);
    }

    public void deleteMenuItem(int id) {
        menuItems.remove(getMenuItemById(id));
    }
}
