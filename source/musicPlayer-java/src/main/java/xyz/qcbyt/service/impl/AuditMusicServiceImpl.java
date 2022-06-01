package xyz.qcbyt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.mapper.AuditMusicMapper;
import xyz.qcbyt.service.AuditMusicService;

import java.util.List;

/**
 * 音乐审核
 */
@Service
public class AuditMusicServiceImpl implements AuditMusicService {
    @Autowired
    private AuditMusicMapper auditMusicMapper;
    @Override
    public Integer UpdateAuditStatus(Boolean status,Integer id) {
        if(status!=null && id!=null){
            Integer integer = auditMusicMapper.UpdateAuditStatus(status,id);
            return integer;
        }
        return 0;
    }

    @Override
    public List<Song> findSong(Integer id, Integer pageNum, Integer pageSize) {
        return auditMusicMapper.findSong(id, pageNum, pageSize);
    }

    @Override
    public Integer auditSuccess(Song song) {
        if(song!=null){
            return auditMusicMapper.auditSuccess(song);
        }
        return 0;
    }
}
