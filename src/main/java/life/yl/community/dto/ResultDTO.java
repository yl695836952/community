package life.yl.community.dto;

import life.yl.community.exception.CustomizeErrorCode;
import life.yl.community.exception.CustomizeException;
import lombok.Data;

/**
 * 返回结果
 * @author yanglin
 * @create 2019-12-09 16:13
 */
@Data
public class ResultDTO {

  private Integer code;

  private String message;

  public static ResultDTO errorOf(Integer code,String message){
    ResultDTO resultDTO = new ResultDTO();
    resultDTO.setCode(code);
    resultDTO.setMessage(message);
    return resultDTO;
  }

  public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
    return errorOf(errorCode.getCode(), errorCode.getMessage());
  }

  public static ResultDTO errorOf(CustomizeException e){
    return errorOf(e.getCode(), e.getMessage());
  }

  public static ResultDTO okOf(){
    ResultDTO resultDTO = new ResultDTO();
    resultDTO.setCode(200);
    resultDTO.setMessage("请求成功");
    return resultDTO;
  }


}
