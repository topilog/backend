package cn.styxs.personalweb.repository;

import cn.styxs.personalweb.model.NavTabItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NavTabItemRepository extends CrudRepository<NavTabItem, Long> {
    public List<NavTabItem> findAllByOrderById();
}
