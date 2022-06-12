package xyz.qcbyt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.qcbyt.entity.Emotion;
import xyz.qcbyt.entity.Language;
import xyz.qcbyt.entity.Scene;
import xyz.qcbyt.entity.Topic;

import java.util.List;

/**
 *  (language,scene,genre,emotion,topic)  音乐标签 mapper
 */
@Mapper
public interface MusicTabMapper {
    /**
     * 返回所有音乐标签
     */
    @Select("select * from language_type")
    List<Language> FindAllLanguage();


    @Select("select * from scene_type")
    List<Scene> FindAllScene();


    @Select("select * from emotion_type")
    List<Emotion> FindAllEmotion();


    @Select("select * from topic_type")
    List<Topic> FindAllTopic();
}
