package cn.styxs.topilog.service;

import cn.styxs.topilog.model.OffsetPage;
import cn.styxs.topilog.model.DisplayOrderModel;
import cn.styxs.topilog.repository.DisplayOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/25
 * @Description: 承载修改展示顺序具体逻辑的Service，派生的model类对应Service需要从这里继承
 */
@Slf4j
public class DisplayOrderService<T extends DisplayOrderModel, TRepository extends DisplayOrderRepository> {

    @Autowired
    protected TRepository repository;

    // 插入item：将设置一个最后的展示顺序
    public T insert(T t) {
        DisplayOrderModel max = repository.findFirstByOrderByMainOrderDesc();
        int mainOrder = max == null ? 0 : max.getMainOrder() + 1;
        t.setMainOrder(mainOrder);
        log.info("insert: order[" + mainOrder + "]" + t.toString());
        return (T)repository.save(t);
    }

    // 移除item
    public void remove(T t) {
        repository.delete(t);
    }

    // 移除item
    public void remove(Long id) {
        repository.deleteById(id);
    }

    // 移动顺序
    public void changeOrder(T t, long targetOrder) {
        long count = repository.count();
        if (t == null || targetOrder < 0 || targetOrder >= count || t.getMainOrder() == targetOrder || count < 2) {
            // do nothing
            return;
        }
        Integer subOrder = null;
        // mainOrder统一朝前参照, subOrder计算有3种情况
        if (targetOrder == 0) {
            List<DisplayOrderModel> top = repository.findAllBy(new OffsetPage(0, 1));
            DisplayOrderModel model = top.get(0);
            t.setMainOrder(model.getMainOrder());
            subOrder = calSubOrder(null, model.getSubOrder());
        } else {
            List<DisplayOrderModel> betweenList = repository.findAllBy(new OffsetPage(targetOrder - 1, 2));
            t.setMainOrder(betweenList.get(0).getMainOrder());
            if (t.getMainOrder() == betweenList.get(1).getMainOrder()) {
                subOrder = calSubOrder(betweenList.get(0).getSubOrder(), betweenList.get(1).getSubOrder());
            } else {
                subOrder = calSubOrder(betweenList.get(0).getSubOrder(), null);
            }
        }
        // 如果subOrder为null则表示无法插入，此时触发一次重排序
        if (subOrder != null) {
            t.setSubOrder(subOrder);
            repository.save(t);
        } else {
            log.warn("resort sub order");
            subSort(t.getMainOrder());
            changeOrder(t, targetOrder);
        }
    }

    // 按序展示所有Item
    public List<T> listByOrder() {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "mainOrder"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "subOrder"));
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(orders));
        return repository.findAllBy(pageRequest);
    }

    private Integer calSubOrder(Integer l, Integer r) {
        if (l == null) {
            if (r == null) {
                return 0;
            } else {
                return (r - Integer.MIN_VALUE) / 2;
            }
        } else if (r == null) {
            return (Integer.MAX_VALUE) - l / 2;
        } else {
            Integer result = (r - l) / 2;
            return result == l ? null : result;
        }
    }

    private void subSort(int mainOrder) {
        List<DisplayOrderModel> list = repository.findAllByMainOrder(mainOrder);
        int interval = Integer.MAX_VALUE / (list.size()+1) * 2;
        int order = Integer.MIN_VALUE;
        for (DisplayOrderModel model : list) {
            order += interval;
            model.setSubOrder(order);
            repository.save(model);
        }
    }


}
