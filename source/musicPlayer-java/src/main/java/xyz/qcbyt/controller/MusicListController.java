package xyz.qcbyt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.entity.UserLoveMusic;
import xyz.qcbyt.service.MusicListService;
import xyz.qcbyt.utils.Result;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 音乐列表
 */
@RestController
@CrossOrigin
@RequestMapping("/musicList")
public class MusicListController {
    @Autowired
    private MusicListService musicListService;

    /**
     * 显示用户 是否喜欢功能
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll(){
        List<Song> userLoveMusic = musicListService.findAllMusic(); //用户喜欢的所有音乐
        List<UserLoveMusic> allMusic = musicListService.selectAllLoverMusic();     //服务器的所有音乐（公共资源）

        for (int i = 0; i <userLoveMusic.size() ; i++) {
            for (int j = 0; j <allMusic.size() ; j++) {
                if(userLoveMusic.get(i).getId().equals(allMusic.get(j).getMusicid())){
                    userLoveMusic.get(i).setLikestatus(true);
                }
            }
        }
        return Result.succ(userLoveMusic);
    }
    //返回 我喜欢的歌曲 的 所有数据 返回值list
    public List<UserLoveMusic> findAllLove(){
        return musicListService.selectAllLoverMusic();
    }

    //返回一首歌的音乐的url 和 歌词
    @RequestMapping("/findOne")
    public Result findOneMusic(@RequestParam(value = "id")  Integer id){
        Song oneMusic = musicListService.findOneMusic(id);
        Map<String,Object> map=new HashMap<>();
        if(oneMusic!=null){
            String lrcurl = oneMusic.getLrcurl();
            JSONArray objects = JSONArray.parseArray(lrcurl);
            map.put("musicname",oneMusic.getMusicname());
            map.put("singername",oneMusic.getSingername());
            map.put("albumname",oneMusic.getAlbumname());
            map.put("duration",oneMusic.getDuration());
            map.put("songurl",oneMusic.getSongurl());
            map.put("lrcurl",objects);
            map.put("coverimg",oneMusic.getCoverimg());
            map.put("likestatus",oneMusic.getLikestatus());

            return Result.succ(map) ;
        }

        return Result.fail("操作失败",map);
    }
}
