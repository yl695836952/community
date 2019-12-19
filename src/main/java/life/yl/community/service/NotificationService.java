package life.yl.community.service;

import life.yl.community.dto.NotificationDTO;
import life.yl.community.dto.PaginationDTO;
import life.yl.community.enums.NotificationStatusEnum;
import life.yl.community.enums.NotificationTypeEnum;
import life.yl.community.exception.CustomizeErrorCode;
import life.yl.community.exception.CustomizeException;
import life.yl.community.mapper.NotificationMapper;
import life.yl.community.mapper.UserMapper;
import life.yl.community.model.Notification;
import life.yl.community.model.NotificationExample;
import life.yl.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yanglin
 * @create 2019-12-18 18:21
 */
@Service
public class NotificationService {

  @Autowired
  private NotificationMapper notificationMapper;

  @Autowired
  private UserMapper userMapper;

  public PaginationDTO list(Long id, Integer page, Integer size) {
    Integer totalPage;

    PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<NotificationDTO>();
    //分页赋值
    NotificationExample example = new NotificationExample();
    example.createCriteria()
            .andReceiverEqualTo(id);
    Integer totalCount = (int)notificationMapper.countByExample(example);

    //判断页数能不能整除
    if(totalCount % size == 0){
      totalPage = totalCount / size;
    }else {
      totalPage = totalCount / size + 1;
    }

    if(page<1){
      page = 1;
    }
    if(page>totalPage){
      page = totalPage;
    }

    paginationDTO.setPagination(totalPage,page);

    //分页 size*(page-1)
    Integer offset = size * (page - 1 );

    NotificationExample example1 = new NotificationExample();
    example1.createCriteria()
            .andReceiverEqualTo(id);
    example1.setOrderByClause("gmt_create desc");
    List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, size));

    if(notifications.size()==0){
      return paginationDTO;
    }

    List<NotificationDTO> notificationDTOList = new ArrayList<>();
    for (Notification notification : notifications) {
      NotificationDTO notificationDTO = new NotificationDTO();
      BeanUtils.copyProperties(notification, notificationDTO);
      notificationDTO.setTypeName(NotificationTypeEnum.nameofType(notification.getType()));
      notificationDTOList.add(notificationDTO);
    }
    paginationDTO.setData(notificationDTOList);
    return paginationDTO;
  }

  public Long unreadCount(Long id) {
    NotificationExample example = new NotificationExample();
    example.createCriteria()
            .andReceiverEqualTo(id)
            .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
    return notificationMapper.countByExample(example);
  }

  public NotificationDTO read(Long id, User user) {
    Notification notification = notificationMapper.selectByPrimaryKey(id);

    if(notification == null){
      throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUNT);
    }

    if(!Objects.equals(notification.getReceiver(),user.getId())){
        throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
    }
    notification.setStatus(NotificationStatusEnum.READ.getStatus());
    notificationMapper.updateByPrimaryKey(notification);

    NotificationDTO notificationDTO = new NotificationDTO();
    BeanUtils.copyProperties(notification, notificationDTO);
    notificationDTO.setTypeName(NotificationTypeEnum.nameofType(notification.getType()));

    return  notificationDTO;
  }
}
