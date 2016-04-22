package ru.cadmy.finance.service;

/**
 * Created by Cadmy on 10.03.2016.
 */
import java.util.List;
import ru.cadmy.finance.model.User;

public interface UserService
{

    public void addUser(User user);
    public List<User> getUserList();
    public void removeUser(Integer id);
    public User getCurrentUser();
    public String getCurrentUsername();
    public User getUserById(String userId);
    public User getUserByUsername(String username);
}
