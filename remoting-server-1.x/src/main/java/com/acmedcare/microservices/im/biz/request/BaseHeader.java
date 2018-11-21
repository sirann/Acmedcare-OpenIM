package com.acmedcare.microservices.im.biz.request;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base Header
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version v1.0 - 09/08/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseHeader implements Serializable {

  private static final long serialVersionUID = 4276005619857794603L;
}
