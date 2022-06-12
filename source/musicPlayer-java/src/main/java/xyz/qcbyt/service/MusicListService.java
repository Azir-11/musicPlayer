package xyz.qcbyt.service;

import org.apache.ibatis.annotations.Select;
import xyz.qcbyt.Vo.SongVo;
import xyz.qcbyt.entity.Song;

import java.util.List;
import java.util.Map;

public interface MusicListService {
    //查询所有歌曲列表的歌
    List<Song> SelectAll();

    //查询一条歌曲列表的歌
    Map<String,Object> Selectone(Integer id);

    //把一首歌上传到审核表中
    Integer addSongToAudit(SongVo song);

    //插入到审核表时候查看 是否重复
     Song addSongUnique(SongVo song);


}
