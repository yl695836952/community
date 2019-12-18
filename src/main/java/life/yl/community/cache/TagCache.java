package life.yl.community.cache;

import life.yl.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 缓存的一个东西
 * @author yanglin
 * @create 2019-12-17 17:56
 */
public class TagCache {
  public static List<TagDTO> get(){
    ArrayList<TagDTO> tagDTOS = new ArrayList<>();
    TagDTO program = new TagDTO();//开发语言
    program.setCategoryName("开发语言");
    program.setTags(Arrays.asList("js","php","css","html5","java","node.js","python",
            "c++","c","golang","swift","c#","sass","ruby","bash","less","asp.net",
            "lua","scala","shell","typescript","objective-c","coffeescript","actionscript",
            "rust","erlang","perl"));
    tagDTOS.add(program);

    TagDTO framework = new TagDTO();
    framework.setCategoryName("平台框架");
    framework.setTags(Arrays.asList("laravel","spring","spring mvc","express",
            "django","flask","yii","ruby-on-rails","tornado","koa","struts"));
    tagDTOS.add(framework);

    TagDTO server = new TagDTO();
    server.setCategoryName("服务器");
    server.setTags(Arrays.asList("linux","nginx","docker","apache",
            "ubuntu","centos","缓存","tomcat","负载均衡","unix","hadoop",
            "windows-server"));
    tagDTOS.add(server);

    TagDTO db = new TagDTO();
    db.setCategoryName("数据库");
    db.setTags(Arrays.asList("mysql","radis","mongodb","sql",
            "oracle","nosql","memcached","sqlserver","postgresql","sqlite"));
    tagDTOS.add(db);

    TagDTO tool = new TagDTO();
    tool.setCategoryName("工具");
    tool.setTags(Arrays.asList("git","github","visual-studio-code","vim",
            "sublime-test","xcode","intellij-idea","eclipse","maven","ide",
            "svn","visual-studio","atom","emacs","textmate","hg"));
    tagDTOS.add(tool);

    return tagDTOS;
  }

  public  static String filterInvalid(String tags){
    String[] split = StringUtils.split(tags, ",");
    List<TagDTO> tagDTOS = get();
    List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
    String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
    return invalid;
  }
}
