package xyz.qcbyt.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import xyz.qcbyt.entity.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongVo {

    private Integer id;
    @NotBlank(message = "音乐名称为必传项")
    @Length(max = 10)
    private String musicname; //音乐名称
    private Integer singerid;   //歌手id
    private String songwriter;  //词作家
    private String composer;    //曲作家
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String reltime;       //歌曲发行时间
    private String coverimg;    //封面图片地址
    @NotBlank(message = "MP3文件为必传项")
    private String mp3src;      //音乐文件地址
    @NotNull(message = "Lrcid为必传项")
    private Integer lrcid;      //歌词id
    private Integer duration; //歌曲时长 秒数

    private Integer languageid; //语种id
    private Integer sceneid;    //场景id
    private Integer emotionid;  //情感id
    private Integer topicid;    //主题id

    private Language language;
    private Scene scene;
    private Emotion emotion;
    private Topic topic;


    private Boolean auditstatus;//审核页 显示的一首歌的审核状态
    private Boolean likestatus;
    @NotNull(message = "userid为必传项")
    private Integer userid;   //用户id (上传到审核表的时候用)


    private Lrc lrc;//一对一映射 歌词表
    private Singer singer;  //歌手表

}
