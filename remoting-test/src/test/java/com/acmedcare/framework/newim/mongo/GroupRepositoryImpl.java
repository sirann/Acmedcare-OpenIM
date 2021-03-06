package com.acmedcare.framework.newim.mongo;

import com.acmedcare.framework.newim.Group;
import com.acmedcare.framework.newim.Group.GroupMembers;
import com.acmedcare.framework.newim.TestApplication;
import com.acmedcare.framework.newim.client.bean.Member;
import com.acmedcare.framework.newim.storage.api.GroupRepository;
import com.google.common.collect.Lists;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Group Repository Impl
 *
 * @author <a href="mailto:iskp.me@gmail.com">Elve.Xu</a>
 * @version ${project.version} - 22/11/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class GroupRepositoryImpl {

  @Autowired private GroupRepository groupRepository;

  @Test
  public void queryGroup() {

    Group group = this.groupRepository.queryGroup("DEFAULT", "gid-20181122");
    Assert.assertEquals("gid-20181122", group.getGroupId());
    Assert.assertEquals("misselvexu", group.getGroupOwner());
    Assert.assertEquals("gname-test-group", group.getGroupName());
  }

  @Test
  public void testSaveGroup() {

    Group group =
        Group.builder()
            .groupId("gid-20181122")
            .groupOwner("misselvexu")
            .groupName("gname-test-group")
            .groupBizTag("G-Z")
            .groupExt("")
            .build();

    this.groupRepository.saveGroup(group);
  }

  @Test
  public void testSaveGroupMembers() {

    List<Member> members = Lists.newArrayList();
    members.add(Member.builder().memberId(3934770676638720L).memberName("超级管理员").build());
    members.add(Member.builder().memberId(3837142362366976L).memberName("7669用户").build());
    members.add(Member.builder().memberId(3837142362366977L).memberName("7666用户").build());

    GroupMembers groupMembers =
        GroupMembers.builder().groupId("gid-20181122").members(members).build();

    this.groupRepository.saveGroupMembers(groupMembers);
  }

  @Test
  public void testSaveGroupMembersFailTransaction() {

    List<Member> members = Lists.newArrayList();
    members.add(Member.builder().memberId(1010206815324416L).memberName("AA").build());
    members.add(Member.builder().memberId(1033050009520384L).memberName("BB").build());

    GroupMembers groupMembers =
        GroupMembers.builder().groupId("gid-20181122").members(members).build();

    // save
    this.groupRepository.saveGroupMembers(groupMembers);

    // query
    List<String> memberIds = this.groupRepository.queryGroupMemberIds("DEFAULT", "gid-20181122");
    Assert.assertEquals(2, memberIds.size());
  }

  @Test
  public void testQueryGroupList() {}
}
