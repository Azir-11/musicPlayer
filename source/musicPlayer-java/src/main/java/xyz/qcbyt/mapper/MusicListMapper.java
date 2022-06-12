package xyz.qcbyt.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.qcbyt.Vo.SongVo;
import xyz.qcbyt.entity.Song;

import java.util.List;
/**
 * song  歌曲列表
 */
@Mapper
public interface MusicListMapper {

    //查询所有歌曲列表的歌
    List<Song> SelectAll();

    //查询一条歌曲列表的歌
    Song Selectone(Integer id);

    //把一首歌上传到审核表中
    Integer addSongToAudit(SongVo song);

    //插入到审核表时候查看 是否重复
    @Select("select * from auditmusic where duration=#{duration} and userid=#{userid}")
    Song addSongUnique(SongVo song);


}
