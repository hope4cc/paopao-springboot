package com.paopao.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseMapper<T> {
    void inserts(List<T> items);

    void delete(int id);

    void deletes(List<Integer> ids);

    void update(T item);

    void updates(List<T> items);

    List<T> selects(Map<String, Object> condition);
}
