package org.bobmarks.mybatis;

import java.util.List;

public class UserServiceTest {

    public static void main(String[] args) {
        UserService userService = new UserService();
       
        userService.deleteAllUsers();
       
        userService.addUser(new User ("David", "dave", "123"));
        userService.addUser(new User ("Robert", "bob", "456"));
        userService.addUser(new User ("John", "joe", "789"));
       
        List<User>users = userService.getUsers();
        long id = users.get(0).getId();
       
        User user = userService.getUser(id);
       
        User modifyUser = new User ();
        modifyUser.setId(id);
        modifyUser.setPassword("abc");
        userService.modifyUser(modifyUser);
       
        userService.deleteUser(id);
    }
}