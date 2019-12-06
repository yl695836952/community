package life.yl.community.mapper;

import life.yl.community.dto.QuestionDTO;
import life.yl.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yanglin
 * @Date 2019-12-02-20:08
 */
@Mapper
public interface QuestionMapper {

  @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag,comment_count,view_count) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag},#{commentCount},#{viewCount})")
  public void create(Question question);

  @Select("select * from question limit #{offset},#{size}")
  List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

  @Select("select count(1) from question")
  Integer count();

  @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
  List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

  @Select("select count(1) from question where creator=#{userId}")
  Integer countByUserId(@Param(value = "userId")Integer userId);

  @Select("select * from question where id=#{id}")
  Question getById(@Param("id")Integer id);

  @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified}," +
          "tag=#{tag} where id=#{id}")
  void update(Question question);
}
