package life.yl.community.enums;

/**
 * @author yanglin
 * @create 2019-12-09 16:19
 */
public enum CommentTypeEnum {
  QUESTION(1),
  COMMENT(2);
  private Integer type;

  public Integer getType() {
    return type;
  }

  CommentTypeEnum(Integer type) {
    this.type = type;
  }

  public static boolean isExist(Integer type) {
    for (CommentTypeEnum value : CommentTypeEnum.values()) {
      if(value.getType().equals(type)){
        return true;
      }
    }
    return false;
  }


}
