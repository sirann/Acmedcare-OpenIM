package com.acmedcare.framework.newim.storage.mongo;

import static com.acmedcare.framework.newim.storage.IMStorageCollections.TOPIC;
import static com.acmedcare.framework.newim.storage.IMStorageCollections.TOPIC_SUBSCRIBE;

import com.acmedcare.framework.newim.Topic;
import com.acmedcare.framework.newim.Topic.TopicSubscribe;
import com.acmedcare.framework.newim.storage.IMStorageCollections;
import com.acmedcare.framework.newim.storage.api.TopicRepository;
import com.google.common.collect.Lists;
import com.mongodb.client.result.DeleteResult;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Topic Repository Impl
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 2018-12-14.
 */
public class TopicRepositoryImpl implements TopicRepository {

  private static final Logger logger = LoggerFactory.getLogger(TopicRepositoryImpl.class);
  private final MongoTemplate mongoTemplate;
  private final TransactionTemplate transactionTemplate;

  public TopicRepositoryImpl(MongoTemplate mongoTemplate, TransactionTemplate transactionTemplate) {
    this.mongoTemplate = mongoTemplate;
    this.transactionTemplate = transactionTemplate;
  }

  @Override
  public void save(Topic[] topics) {
    List<Topic> list = Lists.newArrayList(topics);
    Collection<Topic> result = this.mongoTemplate.insert(list, IMStorageCollections.TOPIC);
    if (result != null && !result.isEmpty()) {
      logger.info("主题存储成功,{}", result.size());
    }
  }

  @Override
  public List<Topic> queryTopics(String namespace, String topicTag) {
    Query query = new Query(Criteria.where("namespace").is(namespace));
    if (topicTag != null && topicTag.trim().length() > 0) {
      query = new Query(Criteria.where("namespace").is(namespace).and("topicTag").is(topicTag));
    }
    return this.mongoTemplate.find(query, Topic.class, IMStorageCollections.TOPIC);
  }

  @Override
  public List<Topic> queryTopics(Long[] topicIds) {
    Query query = new Query(Criteria.where("topicId").in(Lists.newArrayList(topicIds)));
    return this.mongoTemplate.find(query, Topic.class, IMStorageCollections.TOPIC);
  }

  @Override
  public void saveSubscribes(TopicSubscribe... subscribes) {
    List<TopicSubscribe> list = Lists.newArrayList(subscribes);
    this.mongoTemplate.insert(list, TOPIC_SUBSCRIBE);
  }

  @Override
  public void removeSubscribes(String namespace, String passportId, String[] topicIds) {

    logger.info("取消订阅参数:{},{},{}", namespace, passportId, Arrays.toString(topicIds));
    List<Long> ids = Lists.newArrayList();
    for (String topicId : topicIds) {
      ids.add(Long.parseLong(topicId));
    }
    Query query = new Query(Criteria.where("namespace").is(namespace).and("topicId").in(ids));

    if (passportId != null && passportId.trim().length() > 0) {
      query =
          new Query(
              Criteria.where("namespace")
                  .is(namespace)
                  .and("passportId")
                  .is(Long.parseLong(passportId))
                  .and("topicId")
                  .in(ids));
    }

    DeleteResult deleteResult = this.mongoTemplate.remove(query, TOPIC_SUBSCRIBE);
    logger.info("取消订阅执行结果:{},{}", deleteResult.getDeletedCount());
  }

  /**
   * Query Topic Detail
   *
   * @param namespace namespace
   * @param topicId topic id
   * @return a instance of {@link Topic}
   */
  @Override
  public Topic queryTopicDetail(String namespace, Long topicId) {
    Query query = new Query(Criteria.where("topicId").is(topicId).and("namespace").is(namespace));
    return this.mongoTemplate.findOne(query, Topic.class, TOPIC);
  }

  /**
   * Query Topic Subscribe Passports
   *
   * @param namespace namespace
   * @param topicId topic id
   * @return list mapping
   */
  @Override
  public List<TopicSubscribe> queryTopicSubscribes(String namespace, Long topicId) {
    Query query = new Query(Criteria.where("namespace").is(namespace).and("topicId").is(topicId));
    return this.mongoTemplate.find(query, TopicSubscribe.class, TOPIC_SUBSCRIBE);
  }

  @Override
  public void removeTopic(String namespace, Long topicId) {
    Query query = new Query(Criteria.where("namespace").is(namespace).and("topicId").is(topicId));
    this.mongoTemplate.remove(query, TOPIC);
  }
}
