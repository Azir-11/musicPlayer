package xyz.qcbyt.service;

import org.apache.ibatis.annotations.Select;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.entity.UserLoveMusic;

import java.util.List;

public interface MusicListService {
    //查询服务器所有歌曲
    List<Song> findAllMusic();

    //联表查询用户的所有 喜欢的音乐
    List<UserLoveMusic> selectAllLoverMusic();

    //返回一首歌曲 文件地址 歌曲信息
    @Select("select * from song where id=#{id}")
    Song findOneMusic(Integer id);
}
