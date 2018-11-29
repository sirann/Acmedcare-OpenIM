package com.acmedcare.framework.newim.storage.api;

import com.acmedcare.framework.newim.Message;
import java.util.List;

/**
 * Message Repository Api
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 13/11/2018.
 */
public interface MessageRepository {

  /**
   * 保存信息
   *
   * @param message 信息
   * @return mid
   */
  long saveMessage(Message message);

  /**
   * 批量保存消息
   *
   * @param messages 消息列表
   * @return 保存成功的消息
   */
  Long[] batchSaveMessage(Message... messages);

  /**
   * 查询群组信息
   *
   * @param groupId 群组 ID
   * @param receiverId 接收人 ID
   * @param limit 查询条数
   * @param queryLeast 是否查询最新的消息
   * @param leastMessageId 客户端最新的消息编号
   * @return 列表
   */
  List<? extends Message> queryGroupMessages(
      String groupId, String receiverId, long limit, boolean queryLeast, long leastMessageId);

  /**
   * 查询消息列表
   *
   * @param sender 发送人
   * @param receiverId 接收人
   * @param limit 条数
   * @param queryLeast 是否是查询最新的消息
   * @param leastMessageId 客户端最新的消息编号
   * @return 列表
   */
  List<? extends Message> querySingleMessages(
      String sender, String receiverId, long limit, boolean queryLeast, long leastMessageId);
}