package com.xiayk.entity.param;

import com.xiayk.entity.po.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

@Getter
@Setter
public class BaseQuery<T extends BaseEntity> implements Serializable {

    private int limit;

    private int page;

    private String sort;

    public Pageable getPage(){
        if (limit <= 0){
            this.limit = 10;
        }
        return PageRequest.of(this.page, this.limit);
    }
}
