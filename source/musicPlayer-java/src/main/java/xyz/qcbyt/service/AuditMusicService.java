package xyz.qcbyt.service;

import org.apache.ibatis.annotations.Insert;
import xyz.qcbyt.entity.Song;

import java.util.List;

public interface AuditMusicService {
    /**
     * @author MQC
     * @param  status 如果前端传 true 则数据库一首歌为通过状态
     *                 如果传    false 则不通过
     */
    Integer UpdateAuditStatus(Boolean status,Integer id);

    //查一条审核页歌曲的信息  或者查 多条数据 可分页 可不分页
    List<Song> findSong(Integer id, Integer pageNum, Integer pageSize);

    //审核成功后 自动 添加到歌曲列表
     Integer auditSuccess(Song song);
}
