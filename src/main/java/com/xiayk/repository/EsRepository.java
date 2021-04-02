//package com.xiayk.repository;
//
//import org.elasticsearch.index.query.QueryBuilder;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
//import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
//
//import java.io.Serializable;
//
//public interface EsRepository<T, ID extends Serializable> extends ElasticsearchCrudRepository<T, ID> {
//
//    <S extends T> S index(S var1);
//
//    Iterable<T> search(QueryBuilder var1);
//
//    Page<T> search(QueryBuilder var1, Pageable var2);
//
//    Page<T> search(SearchQuery var1);
//
//    Page<T> searchSimilar(T var1, String[] var2, Pageable var3);
//
//    void refresh();
//
//    Class<T> getEntityClass();
//}
