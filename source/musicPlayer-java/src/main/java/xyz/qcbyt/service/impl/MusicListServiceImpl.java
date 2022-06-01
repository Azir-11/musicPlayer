package xyz.qcbyt.service.impl;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.entity.UserLoveMusic;
import xyz.qcbyt.mapper.MusicListMapper;
import xyz.qcbyt.service.MusicListService;

import java.util.Date;
import java.util.List;
@Service
public class MusicListServiceImpl implements MusicListService {
    @Autowired
    private MusicListMapper musicListMapper;

    //查询服务器中的 所有音乐
    @Override
    public List<Song> findAllMusic() {
        List<Song> allMusic = musicListMapper.findAllMusic();
        for (Song song: allMusic) {
            String songurl = song.getSongurl();
            String lrcurl = song.getLrcurl();
            String coverimg = song.getCoverimg();
            if(song.getSongurl()!=null)song.setSongurl(songurl.replace("\\","/" ));
            if (song.getLrcurl()!=null)song.setLrcurl(lrcurl.replace("\\","/" ));
            if(song.getCoverimg()!=null)song.setCoverimg(coverimg.replace("\\","/" ));
        }
        return allMusic;
    }
    //联表查询查询用户 喜欢的歌曲
    @Override
    public List<UserLoveMusic> selectAllLoverMusic(Integer id) {
        List<UserLoveMusic> userLoveMusics = musicListMapper.selectAllLoverMusic(id);
        for (UserLoveMusic userLoveMusic: userLoveMusics) {
            String songurl = userLoveMusic.getSong().getSongurl();
            String lrcurl = userLoveMusic.getSong().getLrcurl();
            String coverimg = userLoveMusic.getSong().getCoverimg();
            if(userLoveMusic.getSong().getSongurl()!=null) userLoveMusic.getSong().setSongurl(songurl.replace("\\","/" ));
            if(userLoveMusic.getSong().getLrcurl()!=null) userLoveMusic.getSong().setLrcurl(lrcurl.replace("\\","/" ));
            if(userLoveMusic.getSong().getCoverimg()!=null) userLoveMusic.getSong().setCoverimg(coverimg.replace("\\","/" ));
        }

        return userLoveMusics;
    }

    @Override
    public Song findOneMusic(Integer id) {
        Song oneMusic = musicListMapper.findOneMusic(id);
        if(oneMusic!=null){
            return oneMusic;
        }
        return null;
    }
    //添加喜欢的歌曲
    @Override
    public Integer addLoveSong(UserLoveMusic loveMusic) {
        if(loveMusic!=null){
            loveMusic.setLovemoment(new Date());
            return musicListMapper.addLoveSong(loveMusic);
        }
        return null;
    }
    //添加喜欢的歌曲的时候 去重
    @Override
    public UserLoveMusic selectLoveSong(Integer musicid,Integer userid) {
        if (musicid!=null && userid!=null){
            return musicListMapper.selectLoveSong(musicid,userid);
        }
        return null;
    }

    @Override
    public Integer cancelLikeSong(Integer id) {
        Integer res = musicListMapper.cancelLikeSong(id);
        System.out.println(res);
        if (res!=null &&res!=0){
            return 1;
        }
        return null;
    }
}
