package cn.styxs.topilog.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @Author: styxs
 * @CreateTime: 2020/4/11
 * @Description: 实现了一个基于偏移量的分页
 */
public class OffsetPage implements Pageable {
    private int offset;
    private int limit;
    private final Sort sort;
    public OffsetPage(int offset, int limit, Sort sort) {
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }
    public OffsetPage(int offset, int limit) {
        this(offset, limit, Sort.by(Sort.DEFAULT_DIRECTION, "id"));
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetPage(offset + limit, limit, sort);
    }

    public OffsetPage previous() {
        return hasPrevious() ? new OffsetPage(offset - limit, limit, sort) : this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetPage(0, limit, sort);
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}
