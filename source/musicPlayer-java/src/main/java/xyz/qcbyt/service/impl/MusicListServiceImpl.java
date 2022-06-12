package xyz.qcbyt.service.impl;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.qcbyt.Vo.SongVo;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.mapper.MusicListMapper;
import xyz.qcbyt.service.MusicListService;
import xyz.qcbyt.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class MusicListServiceImpl implements MusicListService {
    @Autowired
    private MusicListMapper musicListMapper;
    @Override
    public List<Song> SelectAll() {
        return musicListMapper.SelectAll();
    }

    @Override
    public Map<String,Object> Selectone(Integer id) {
        Song oneSong = musicListMapper.Selectone(id);
        if (id!=null){ Map<String,Object> map=new HashMap<>();
        String lrc = oneSong.getLrc().getLrc();
        Object parse = JSONArray.parse(lrc);
        map.put("id",oneSong.getId());
        map.put("musicname",oneSong.getMusicname());
        map.put("singerid",oneSong.getSingerid());
        map.put("songwriter",oneSong.getSongwriter());
        map.put("composer",oneSong.getComposer());
        map.put("reltime", oneSong.getReltime());
        map.put("coverimg",oneSong.getCoverimg());
        map.put("mp3src",oneSong.getMp3src());
        map.put("lrcid",oneSong.getLrcid());
        map.put("likestatus",oneSong.getLikestatus());
        map.put("duration",oneSong.getDuration());
        map.put("lrc",parse);
        map.put("lrcpath",oneSong.getLrc().getLrcpath());
        map.put("languageid",oneSong.getLanguageid());
        map.put("language",oneSong.getLanguage().getLanguage());
        map.put("sceneid",oneSong.getSceneid());
        map.put("scene",oneSong.getScene().getScene());
        map.put("emotionid",oneSong.getEmotionid());
        map.put("emotion",oneSong.getEmotion().getEmotion());
        map.put("topicid",oneSong.getTopicid());
        map.put("topic",oneSong.getTopic().getTopic());
            return map;
        }
        return null;
    }

    @Override
    public Integer addSongToAudit(SongVo song) {

            return song!=null?musicListMapper.addSongToAudit(song):null;

    }

    @Override
    public Song addSongUnique(SongVo song) {
        if(song!=null){
            return musicListMapper.addSongUnique(song);
        }
        return null;
    }
}
