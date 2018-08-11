package com.acmedcare.microservices.im.biz.bean;

import java.io.Serializable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Account
 *
 * @author Elve.Xu [iskp.me<at>gmail.com]
 * @version v1.0 - 09/08/2018.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Account implements Serializable {

  private static final long serialVersionUID = 6959404976377383795L;

  /** Account Login Username for IM Server */
  private String username;

  @Builder
  public Account(String username) {
    this.username = username;
  }
}