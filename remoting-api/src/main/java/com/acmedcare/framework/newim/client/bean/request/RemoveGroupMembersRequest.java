package com.acmedcare.framework.newim.client.bean.request;

import com.acmedcare.framework.newim.client.MessageConstants;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * {@link RemoveGroupMembersRequest}
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 21/11/2018.
 */
@Getter
@Setter
public class RemoveGroupMembersRequest implements Serializable {

  private static final long serialVersionUID = 4499697628571057249L;

  private String namespace = MessageConstants.DEFAULT_NAMESPACE;
  private String groupId;
  private List<String> memberIds;
}
