package life.yl.community.service;

import life.yl.community.mapper.UserMapper;
import life.yl.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanglin
 * @create 2019-12-06 13:44
 */
@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;


  public void createOrUpdate(User user) {
    User dbUser = userMapper.findByAccountId(user.getAccountId());
    if(dbUser == null){
      //插入
      user.setGmtCreate(System.currentTimeMillis());
      user.setGmtModified(user.getGmtCreate());
      userMapper.insert(user);
    }else {
      //更新
      dbUser.setGmtModified(System.currentTimeMillis());
      dbUser.setAvatarUrl(user.getAvatarUrl());
      dbUser.setName(user.getName());
      dbUser.setToken(user.getToken());
      userMapper.update(dbUser);
    }
  }

}
