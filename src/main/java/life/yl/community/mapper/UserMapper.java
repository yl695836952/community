package life.yl.community.mapper;

import life.yl.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author yanglin
 * @create 2019-12-02 14:04
 */
@Mapper
public interface UserMapper {

  @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
  void insert(User user);

  @Select("select * from user where token = #{token}")
  User findByToken(String token);
}
