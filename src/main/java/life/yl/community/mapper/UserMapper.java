package life.yl.community.mapper;

import life.yl.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @author yanglin
 * @create 2019-12-02 14:04
 */
@Mapper
public interface UserMapper {

  @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
  void insert(User user);

  @Select("select * from user where token = #{token}")
  User findByToken(String token);

  @Select("select * from user where id = #{id}")
  User findById(@Param("id") Integer id);

  @Select("select * from user where account_id = #{accountId}")
  User findByAccountId(@Param("accountId") String accountId);

  @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id=#{id}")
  void update(User dbUser);
}
