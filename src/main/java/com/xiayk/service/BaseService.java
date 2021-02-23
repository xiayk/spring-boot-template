package com.xiayk.service;

import com.xiayk.entity.param.BaseQuery;
import com.xiayk.entity.po.BaseEntity;
import com.xiayk.repository.BaseRepository;
import com.xiayk.utils.JpaHelp;
import org.springframework.data.domain.Page;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public interface BaseService<T extends BaseEntity, R extends BaseRepository<T, String>, Q extends BaseQuery<T>> {

    R baseRepository();

    default Page<T> page(Q query){
        return baseRepository().findAll((Specification<T>)(root, criteriaQuery, criteriaBuilder) -> JpaHelp.query(root, query, criteriaBuilder), query.getPage());
    }

    default T get(String var){
        return baseRepository().findById(var).orElse(null);
    }

    default T save(T var){
        return baseRepository().save(var);
    }

    default List<T> saveAll(List<T> data){
        return baseRepository().saveAll(data);
    }

    default void del(String id){
        baseRepository().deleteById(id);
    }

    default void delAll(List<String> vars){
        vars.forEach(id -> {
            baseRepository().deleteById(id);
        });
    }

    default List<T> list(List<String> vars){
        return baseRepository().findAllById(vars);
    }
}
