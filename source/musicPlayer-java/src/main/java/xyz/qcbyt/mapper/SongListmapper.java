package xyz.qcbyt.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.qcbyt.entity.Song;

import java.util.List;

@Mapper
public interface SongListmapper {


    @Select("select * from song where musicname like #{musicname} or singername like #{singername} or albumname like #{albumname}")
    List<Song> songserach(String name);

}
