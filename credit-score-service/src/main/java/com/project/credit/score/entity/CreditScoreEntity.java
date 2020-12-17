package com.project.credit.score.entity;

import com.project.credit.score.base.type.AbstractEditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "crdt_scr")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditScoreEntity extends AbstractEditableEntity {

  @Id
  @Column(name = "crdt_scr_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long crdtScrId;

  @Basic
  @Column(name = "cust_id")
  private Long custId;

  @Basic
  @Column(name = "crdt_scr_st_id")
  private Long crdtScrStId;
}
