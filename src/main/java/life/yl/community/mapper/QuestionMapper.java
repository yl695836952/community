package life.yl.community.mapper;

import life.yl.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yanglin
 * @Date 2019-12-02-20:08
 */
@Mapper
public interface QuestionMapper {

  @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
  public void create(Question question);
}
