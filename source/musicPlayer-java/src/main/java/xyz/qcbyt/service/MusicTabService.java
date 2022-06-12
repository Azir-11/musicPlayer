package xyz.qcbyt.service;
import xyz.qcbyt.entity.Emotion;
import xyz.qcbyt.entity.Language;
import xyz.qcbyt.entity.Scene;
import xyz.qcbyt.entity.Topic;

import java.util.List;
public interface MusicTabService {

    /**
     * 返回所有音乐标签
     */
     List<Language> FindAllLanguage();


     List<Scene> FindAllScene();


     List<Emotion> FindAllEmotion();


     List<Topic> FindAllTopic();
}
