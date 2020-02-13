package cn.styxs.personalweb.repository;

import cn.styxs.personalweb.model.Role;
import cn.styxs.personalweb.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
