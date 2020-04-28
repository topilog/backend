package cn.styxs.topilog.repository;

import cn.styxs.topilog.model.DisplayOrderModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/25
 * @Description: DisplayOrderModel对应的Repository，继承DisplayOrderModel的类的Repository需要从这个类继承
 */
@NoRepositoryBean
public interface DisplayOrderRepository<T extends DisplayOrderModel, ID> extends CrudRepository<T, ID> {
    T findFirstByOrderByMainOrderDesc();

    List<T> findAllByMainOrder(int mainOrder);

    List<T> findAllBy(Pageable pageable);
}
