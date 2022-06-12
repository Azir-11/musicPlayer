package xyz.qcbyt.mapper;
/**
 * 管理员  音乐审核二控制器
 */
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.qcbyt.entity.Lrc;
import xyz.qcbyt.entity.Song;
import java.util.List;

@Mapper
public interface AuditMusicMapper {
    //管理员查看所有的 审核页面的 歌曲
    @Select("select * from auditmusic")
    List<Song> AllAuditMusic();

    //查一条
    @Select("select * from auditmusic where id=#{id}")
    Song oneAuditMusic(Integer id);

    //更新审核状态
    @Update("update auditmusic set auditstatus=#{auditstatus} where id=#{id}")
    Integer updateAudit(Boolean auditstatus,Integer id);

    //把审核表的歌曲插入到 歌曲列表
    @Insert("insert into song (musicname,singerid,songwriter,composer,reltime,coverimg,mp3src,lrcid,likestatus,duration,languageid,sceneid,emotionid,topicid) values(#{musicname},#{singerid},#{songwriter},#{composer},#{reltime},#{coverimg},#{mp3src},#{lrcid},#{likestatus},#{duration},#{languageid},#{sceneid},#{emotionid},#{topicid})")
    Integer AuditPass(Song song);

    //查询音乐表的一条信息
    @Select("select * from song where id=#{id}")
    Song FindOne(Integer id);
    @Select("select * from lrc where id=#{id}")
    Lrc findLrc(Integer id);
}
