package xyz.qcbyt.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.qcbyt.entity.Song;

import java.util.List;

/**
 * 音乐审核
 */

@Mapper
public interface AuditMusicMapper {
    /**
     * @author MQC
     * @param  status 如果前端传 true 则数据库一首歌为通过状态
     *                 如果传    false 则不通过
     */
    @Update("update auditmusic set auditstatus=#{status} where id=#{id}")
    Integer UpdateAuditStatus(Boolean status,Integer id);

    //查一条审核页歌曲的信息  或者查 多条数据 可分页 可不分页
    List<Song> findSong(Integer id,Integer pageNum,Integer pageSize);

    //审核成功后 自动 添加到歌曲列表
    @Insert("insert into song (musicname,singername,albumname,duration,songurl,lrcurl,coverimg,likestatus) VALUES(#{musicname},#{singername},#{albumname},#{duration},#{songurl},#{lrcurl},#{coverimg},#{likestatus})")
    Integer auditSuccess(Song song);

}
