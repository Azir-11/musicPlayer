package xyz.qcbyt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.qcbyt.entity.Emotion;
import xyz.qcbyt.entity.Language;
import xyz.qcbyt.entity.Scene;
import xyz.qcbyt.entity.Topic;
import xyz.qcbyt.mapper.MusicTabMapper;
import xyz.qcbyt.service.MusicTabService;

import java.util.List;

@Service("MusicTabServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class MusicTabServiceImpl implements MusicTabService {

    @Autowired
    private MusicTabMapper musicTabMapper;

    @Override
    public List<Language> FindAllLanguage() {
        return musicTabMapper.FindAllLanguage();
    }

    @Override
    public List<Scene> FindAllScene() {
        return musicTabMapper.FindAllScene();
    }

    @Override
    public List<Emotion> FindAllEmotion() {
        return musicTabMapper.FindAllEmotion();
    }

    @Override
    public List<Topic> FindAllTopic() {
        return musicTabMapper.FindAllTopic();
    }
}
