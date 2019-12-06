package life.yl.community.service;

import life.yl.community.mapper.UserMapper;
import life.yl.community.model.User;
import life.yl.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanglin
 * @create 2019-12-06 13:44
 */
@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;


  public void createOrUpdate(User user) {
    UserExample example = new UserExample();
    example.createCriteria()
            .andAccountIdEqualTo(user.getAccountId());
    List<User> users = userMapper.selectByExample(example);
    if(users.size() == 0 ){
      //插入
      user.setGmtCreate(System.currentTimeMillis());
      user.setGmtModified(user.getGmtCreate());
      userMapper.insert(user);
    }else {
      //更新
      User dbUser = users.get(0);

      User updateUser = new User();
      updateUser.setGmtModified(System.currentTimeMillis());
      updateUser.setAvatarUrl(user.getAvatarUrl());
      updateUser.setName(user.getName());
      updateUser.setToken(user.getToken());

      UserExample userExample = new UserExample();
      userExample.createCriteria()
              .andIdEqualTo(dbUser.getId());
      userMapper.updateByExampleSelective(updateUser,userExample);
    }
  }

}
