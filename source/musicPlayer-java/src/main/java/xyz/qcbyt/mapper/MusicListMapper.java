package xyz.qcbyt.mapper;

import org.apache.ibatis.annotations.*;
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

    //返回一首歌曲 文件地址 歌曲信息
    @Select("select * from song where id=#{id}")
    Song findOneMusic(Integer id);

    //联表查询用户的所有 喜欢的音乐 (用户喜欢)
    List<UserLoveMusic> selectAllLoverMusic(Integer id);
    /**
     * 用户喜欢歌曲页
     */

    //添加喜欢功能
    @Insert("insert into personlove (lovemoment,musicid,userid) values(#{lovemoment},#{musicid},#{userid})")
    Integer addLoveSong(UserLoveMusic loveMusic);

    //查询一条 用户喜欢的歌曲,(添加歌曲的时候判断去重)
    @Select("select * from personlove where musicid=#{musicid} and userid=#{userid}")
    UserLoveMusic selectLoveSong(Integer musicid,Integer userid);

    //取消喜欢功能
    @Delete("delete from personlove where userid=#{id}")
    Integer cancelLikeSong(@Param("id") Integer id);




}
