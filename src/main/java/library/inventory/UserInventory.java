package library.inventory;

import library.data.User;
import java.util.ArrayList;
import java.util.List;

public class UserInventory {

    List<User> users = new ArrayList<>();

    {
        users.add(new User(1, "Alicja", "Kos"));
        users.add(new User(2, "Barbara", "Las"));
        users.add(new User(3, "James", "Fox"));
        users.add(new User(4, "Patryk", "Rid"));
        users.add(new User(5, "Paweł", "Kyrc"));
        users.add(new User(6, "Alicja", "Duda"));
        users.add(new User(7, "Mateusz", "Swarowski"));
        users.add(new User(8, "Martyna", "Tomaszewska"));

    }
    public User getById(int id) {
        return users.get(id);
    }

}
