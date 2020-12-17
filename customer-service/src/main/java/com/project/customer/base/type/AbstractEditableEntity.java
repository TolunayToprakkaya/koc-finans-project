package com.project.customer.base.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEditableEntity {

  @Basic
  @CreatedDate
  @Column(name = "cdate", columnDefinition = "TIMESTAMP")
  private Date cdate;

  @Basic
  @LastModifiedDate
  @Column(name = "udate", columnDefinition = "TIMESTAMP", insertable = false)
  private Date udate;
}
