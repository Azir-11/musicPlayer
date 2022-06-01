package xyz.qcbyt.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.entity.UserLoveMusic;

import java.util.List;

public interface MusicListService {
    //查询服务器所有歌曲
    List<Song> findAllMusic();

    //返回一首歌曲 文件地址 歌曲信息
    @Select("select * from song where id=#{id}")
    Song findOneMusic(Integer id);

    /**
     * 用户喜欢页面
     *
     */
    //联表查询用户的所有 喜欢的音乐
    List<UserLoveMusic> selectAllLoverMusic(Integer id);

    //添加喜欢功能
    Integer addLoveSong( UserLoveMusic loveMusic);

    //查询一条 用户喜欢的歌曲,(添加歌曲的时候判断去重)
     UserLoveMusic selectLoveSong(Integer musicid,Integer userid);

    //取消喜欢功能
    Integer cancelLikeSong(@Param("id") Integer id);
}
