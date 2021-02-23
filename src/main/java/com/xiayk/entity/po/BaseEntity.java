package com.xiayk.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
@MappedSuperclass
//@SQLDelete(sql = "update sys_user set deleted = 'Y' where id = ?")
//@SQLDeleteAll(sql = "update sys_user set deleted = 'Y' where id = ?")
//@Where(clause = "deleted = 'N")
//@EntityListeners(value = {AuditListener.class})
public class BaseEntity implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @Column(nullable = false,columnDefinition = "varchar(100) comment '主键ID'")
    private String id;

    /**
     * 创建时间
     */
    @Column(nullable = false,columnDefinition = "datetime(6) comment '创建时间'")
    @CreationTimestamp
    private Date createdTime;

    @Column(nullable = false,columnDefinition = "varchar(100) comment '创建人'")
    private String createdBy;
    /**
     * 修改时间
     */
    @Column(nullable = false,columnDefinition = "datetime(6) comment '修改时间'")
    @UpdateTimestamp
    private Date updatedTime;

    @Column(columnDefinition = "varchar(100) comment '修改人'")
    private String updatedBy;

    @Column(nullable = false,columnDefinition = "varchar(1) comment '状态 1, 正常， 2，删除  3，冻结'")
    private String status = "1";

    @Column(nullable = false,columnDefinition = "varchar(1) comment '是否删除'")
    private String deleted = "N";
}
