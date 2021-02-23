package com.xiayk.utils;

import com.xiayk.annotation.Query;
import com.xiayk.entity.param.BaseQuery;
import com.xiayk.entity.po.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JpaHelp {

    public static <T extends BaseEntity, Q extends BaseQuery> Predicate query(Root<T> root, Q query, CriteriaBuilder cb){
        Predicate predicate = cb.conjunction();
        getFields(query).forEach(e -> {
            Predicate pre = null;
            switch (e.query.type()){
                case EQUAL:
                    pre = cb.equal(root.get(e.getKey()), e.getValue());
                    break;
                case IS_NULL:
                    predicate.getExpressions().add(cb.isNotNull(root.get(e.getKey())));
                    break;
                case LEFT_LIKE:
                    predicate.getExpressions().add(cb.like(root.get(e.getKey()), "%"+e.getValue()));
                    break;
                case INNER_LIKE:
                    predicate.getExpressions().add(cb.like(root.get(e.getKey()), "%"+e.getValue() + "%"));
                    break;
                case RIGHT_LIKE:
                    predicate.getExpressions().add(cb.like(root.get(e.getKey()), e.getValue() + "%"));
                    break;
                case NOT_NULL:
                    pre = cb.isNotNull(root.get(e.getKey()));
            }
            if (pre != null){
                predicate.getExpressions().add(pre);
            }
        });
        return predicate;
    }

    private static List<Entity> getFields(Object obj){
        Class<?> clazz = obj.getClass();
        List<Entity> list = new ArrayList<>();
        // 遍历往上获取父类，直至最后一个父类
        Field[] field = clazz.getDeclaredFields();
        for (Field f : field) {
            f.setAccessible(true);
            System.out.println(f.getDeclaredAnnotation(Query.class));
            try {
                list.add(new Entity(f.getName(), f.get(obj).toString(), f.getGenericType().getTypeName(), f.getAnnotation(Query.class)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Getter
    @Setter
    private static class Entity{
        private String key;

        private String value;

        private String type;

        private Query query;

        public Entity(String key, String value, String type, Query query){
            this.key = key;
            this.value = value;
            this.query = query;
            this.type = type;
        }
    }
}
