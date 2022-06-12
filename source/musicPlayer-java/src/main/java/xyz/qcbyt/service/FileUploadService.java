package xyz.qcbyt.service;

import org.apache.ibatis.annotations.Insert;
import xyz.qcbyt.entity.Lrc;
import xyz.qcbyt.entity.Song;

public interface FileUploadService {
    //查询lrc 从表中的一条歌词信息
    Lrc findOneLrc(String lrcpath);

    //上传lrc文件之后 把lrc信息插入到数据库
    @Insert("insert into lrc (lrc,lrcpath) values (#{lrc},#{lrcpath})")
    Integer addLrc(Lrc lrc);
}
