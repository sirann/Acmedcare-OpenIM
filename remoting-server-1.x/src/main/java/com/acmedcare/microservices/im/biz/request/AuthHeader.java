package com.acmedcare.microservices.im.biz.request;

import com.acmedcare.tiffany.framework.remoting.CommandCustomHeader;
import com.acmedcare.tiffany.framework.remoting.exception.RemotingCommandException;
import lombok.Getter;
import lombok.Setter;

/**
 * Auth Header
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version v1.0 - 09/08/2018.
 */
@Getter
@Setter
public class AuthHeader implements CommandCustomHeader {

  private String username;

  @Override
  public void checkFields() throws RemotingCommandException {}
}
