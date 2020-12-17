package com.project.customer.entity;

import com.project.customer.base.type.AbstractEditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cust")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity extends AbstractEditableEntity {

  @Id
  @Column(name = "cust_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long custId;

  @Basic
  @Column(name = "id_nmbr")
  private Long idNmbr;

  @Basic
  @Column(name = "name")
  private String name;

  @Basic
  @Column(name = "lastname")
  private String lastname;

  @Basic
  @Column(name = "mnthly_incm")
  private Long mnthlyIncm;

  @Basic
  @Column(name = "phone")
  private Long phone;

  @Basic
  @Column(name = "crrnt_crdt_scr_st_id")
  private Long crrntCrdtScrStId;

  @Basic
  @Column(name = "crrnt_limit")
  private Long crrntLimit;
}
