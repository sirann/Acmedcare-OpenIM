package com.acmedcare.microservices.im.biz.body;

import com.acmedcare.microservices.im.biz.bean.Message;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Message Return Response Body
 *
 * @author Elve.Xu [iskp.me<at>gmail.com]
 * @version v1.0 - 09/08/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class MessageBody implements Serializable {

  private static final long serialVersionUID = -5226536202888349616L;

  private List<Message> messages;


  @Builder
  public MessageBody(List<Message> messages) {
    this.messages = messages;
  }
}