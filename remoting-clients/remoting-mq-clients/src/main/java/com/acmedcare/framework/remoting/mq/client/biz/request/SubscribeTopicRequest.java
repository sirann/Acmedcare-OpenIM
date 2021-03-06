package com.acmedcare.framework.remoting.mq.client.biz.request;

import com.acmedcare.framework.remoting.mq.client.Serializables;
import com.acmedcare.framework.remoting.mq.client.exception.BizException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SubscribeTopicRequest
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2018-12-18.
 */
@Getter
@Setter
@NoArgsConstructor
public class SubscribeTopicRequest extends BaseRequest {

  /** 定于的主题标识 */
  private String[] topicIds;

  public SubscribeTopicRequest(String passport, String passportId, String[] topicIds) {
    super(passport, passportId);
    this.topicIds = topicIds;
  }

  @Override
  public void validateFields() throws BizException {
    if (topicIds == null || topicIds.length == 0) {
      throw new BizException("topicIds must not be null.");
    }

    if (Serializables.isAnyBlank(getPassport(), getPassportId())) {
      throw new BizException("passport & passportId must not be null.");
    }
  }

  public interface Callback {

    /** Sub Succeed callback */
    void onSuccess();

    /**
     * Sub failed callback
     *
     * @param code error code
     * @param message error message
     */
    void onFailed(int code, String message);

    void onException(BizException e);
  }
}
