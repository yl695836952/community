package life.yl.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author yanglin
 * @create 2019-12-17 17:56
 */
@Data
public class TagDTO {
  //分类名
  private String categoryName;
  private List<String> tags;
}
