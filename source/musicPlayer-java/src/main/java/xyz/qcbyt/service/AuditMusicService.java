package xyz.qcbyt.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.qcbyt.entity.Lrc;
import xyz.qcbyt.entity.Song;

import java.util.List;

public interface AuditMusicService {
    //管理员查看所有的 审核页面的 歌曲
    List<Song> AllAuditMusic();

    //查一条
    Song oneAuditMusic(Integer id);

    //更新审核状态
     Integer updateAudit(Boolean auditstatus,Integer id);

    //把审核表的歌曲插入到 歌曲列表
     Integer AuditPass(Song song);

    //查询音乐表的一条信息
     Song FindOne(Integer id);

    @Select("select * from lrc where id=#{id}")
    Lrc findLrc(Integer id);
}
