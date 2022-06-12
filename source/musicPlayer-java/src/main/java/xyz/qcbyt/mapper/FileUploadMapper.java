package xyz.qcbyt.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import xyz.qcbyt.entity.Lrc;
import xyz.qcbyt.entity.Song;

/**
 * 文件上传 (歌曲上传页面)
 */
@Mapper
public interface FileUploadMapper {

    //查询lrc 从表中的一条歌词信息
    @Select("select * from lrc where lrcpath =#{lrcpath}")
    Lrc findOneLrc(String lrcpath);

    //上传lrc文件之后 把lrc信息插入到数据库
    @Insert("insert into lrc (lrc,lrcpath) values (#{lrc},#{lrcpath})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Integer addLrc(Lrc lrc);


}
