package xyz.qcbyt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.qcbyt.entity.Lrc;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.mapper.AuditMusicMapper;
import xyz.qcbyt.service.AuditMusicService;

import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class AuditMusicServiceImpl implements AuditMusicService {

    @Autowired
    private AuditMusicMapper mapper;

    @Override
    public List<Song> AllAuditMusic() {
        return mapper.AllAuditMusic();
    }

    @Override
    public Song oneAuditMusic(Integer id) {
        return id!=null? mapper.oneAuditMusic(id) : null ;
    }

    @Override
    public Integer updateAudit(Boolean auditstatus, Integer id) {
        return auditstatus!=null&& id!=null ? mapper.updateAudit(auditstatus, id) :null;
    }

    @Override
    public Integer AuditPass(Song song) {

        return song!=null ? mapper.AuditPass(song) :null ;
    }

    @Override
    public Song FindOne(Integer id) {
        return id!=null?mapper.FindOne(id):null;
    }

    @Override
    public Lrc findLrc(Integer id) {
        return id!=null? mapper.findLrc(id) :null ;
    }
}
