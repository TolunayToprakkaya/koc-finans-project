package com.project.credit.score.entity;

import com.project.credit.score.base.type.AbstractEditableEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "crdt_scr_st")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditScoreStatusEntity extends AbstractEditableEntity {

  @Id
  @Column(name = "crdt_scr_st_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long crdtScrStId;

  @Basic
  @Column(name = "name")
  private String name;
}
