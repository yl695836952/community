package life.yl.community.mapper;

import life.yl.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yanglin
 * @create 2019-12-02 14:04
 */
@Mapper
public interface UserMapper {

  @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
  void insert(User user);

}
