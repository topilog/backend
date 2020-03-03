package cn.styxs.topilog.repository;

import cn.styxs.topilog.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: StyxS
 * @CreateTime: 2020/2/18
 * @Description:
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
