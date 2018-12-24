package com.acmedcare.framework.remoting.mq.client.biz.request;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PullTopicListRequest
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2018-12-24.
 */
@Getter
@Setter
@NoArgsConstructor
public class PullTopicListRequest extends BaseRequest {

  public interface Callback {

    /**
     * Succeed callback
     *
     * @param topics topic list
     */
    void onSuccess(List<?> topics);

    /**
     * Sub failed callback
     *
     * @param code error code
     * @param message error message
     */
    void onFailed(int code, String message);
  }
}
