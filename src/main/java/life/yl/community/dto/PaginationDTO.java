package life.yl.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页(页面上的展示)
 * @author yanglin
 * @create 2019-12-04 14:54
 */
@Data
public class PaginationDTO {
  private List<QuestionDTO> questions;
  private boolean showPrevious; //前一页
  private boolean showFirstPage; //第一页
  private boolean showNext;//后一页
  private boolean showEndPage;//最后一页
  private Integer totalPage;
  private Integer totalPage1;
  private Integer page;

  private  List<Integer> pages = new ArrayList<>();


  /**
   * 计算出分页的所有 页数--
   * @param totalPage
   * @param page
   */
  public void setPagination(Integer totalPage, Integer page) {

    this.totalPage = totalPage;

    this.page = page;



    pages.add(page);
    for(int i=1;i<=3;i++){
      if(page-i > 0){
        pages.add(0,page - i);
      }
      if(page + i <=totalPage){
        pages.add(page+i);
      }
    }

    //时候展示上一页
    if(page == 1){
      showPrevious = false;
    } else {
      showPrevious = true ;
    }

    //是否展示下一页
    if(page.equals(totalPage)){
      showNext = false;
    } else {
      showNext = true;
    }

    //是否展示第一页
    if(pages.contains(1)){
      showFirstPage = false;
    }else {
      showFirstPage = true;
    }

    //是否展示最后一页
    if(pages.contains(totalPage)){
      showEndPage = false;
    }else {
      showEndPage = true;
    }
  }
}
