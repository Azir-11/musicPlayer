package xyz.qcbyt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.entity.UserLoveMusic;

import java.util.List;

/**
 * 歌曲列表 持久层
 */
@Mapper
public interface MusicListMapper {
    //查询服务器的所有音乐
    @Select("select * from song")
    List<Song> findAllMusic();
    //联表查询用户的所有 喜欢的音乐
    List<UserLoveMusic> selectAllLoverMusic();
    //返回一首歌曲 文件地址 歌曲信息
    @Select("select * from song where id=#{id}")
    Song findOneMusic(Integer id);

}
