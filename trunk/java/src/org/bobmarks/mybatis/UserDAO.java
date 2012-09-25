package org.bobmarks.mybatis;

import java.util.List;

public interface UserDAO {

    public User selectUserById (long id);
    
    public List<User> selectAllUsers ();
    
    public void insertUser(User user);
    
    public void updateUser(User user);

    public void deleteUser(long id);

    public void deleteAllUsers();
}