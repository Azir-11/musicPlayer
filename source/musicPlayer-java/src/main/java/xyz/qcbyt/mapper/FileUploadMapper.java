package xyz.qcbyt.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.qcbyt.entity.Song;

/**
 * 文件上传 (歌曲上传页面)
 */
@Mapper
public interface FileUploadMapper {
    //上传歌曲文件
    Integer addSong(Song song);

}
