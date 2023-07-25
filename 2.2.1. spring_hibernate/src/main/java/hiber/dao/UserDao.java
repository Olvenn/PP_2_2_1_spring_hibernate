package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> getListUsers();

    List<User> getUsersByCar(String model, int series);

    User getUserByCar(String model, int series);
}
