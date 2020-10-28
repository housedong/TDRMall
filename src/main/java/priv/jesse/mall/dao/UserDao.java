package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import priv.jesse.mall.entity.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    /**
     * 根据用户名，密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    List<User> findByUsername(String username);

    /**
     * 根据用户类型查询用户
     * @return
     */
    @Query(value = "select * FROM `user` u where u.department='1'",nativeQuery = true)
    List<User> findByDepartment();


}
