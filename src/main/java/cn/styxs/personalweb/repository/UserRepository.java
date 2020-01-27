package cn.styxs.personalweb.repository;

import cn.styxs.personalweb.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findUserByUsername(String username);
}
