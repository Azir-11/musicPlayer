package xyz.qcbyt.controller;
import com.alibaba.fastjson.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xyz.qcbyt.entity.Lrc;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.service.AuditMusicService;
import xyz.qcbyt.service.MusicListService;
import xyz.qcbyt.service.MusicTabService;
import xyz.qcbyt.utils.Result;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@RestController
@CrossOrigin
@Transactional(rollbackFor = Exception.class)
@RequiresRoles(value = "admin")
@RequestMapping("/audit")
public class AuditMusicController {

    @Autowired
    private AuditMusicService auditMusicServiceservice;
//    @Autowired
//    private  MusicTabService musicTabService;


    /**
     * 不传id      直接返回搜友歌曲
     * @param id  传id就查一条
     * @return    一首歌曲的信息
     */
    @GetMapping("/findAll")
    public Result AllAuditMusic(@RequestParam(required = false) Integer id){
        Map<String,Object> map=new HashMap<>();
        if(id!=null){
            Song song = auditMusicServiceservice.oneAuditMusic(id);
            map.put("row",song);
            Lrc lrc = auditMusicServiceservice.findLrc(song.getLrcid());
            map.put("lrc", JSONArray.parse(lrc.getLrc()));
            return Result.succ(map);
        }
        return Result.succ(auditMusicServiceservice.AllAuditMusic());
    }


    /**
     * admin才能访问
     * 把审核表的歌曲放入 音乐列表
     * @param id
     * @param auditstatus
     * @return
     */
    @RequiresRoles(value = "admin")
    @PostMapping("/auditmusic")
    public Result PassAudit(@RequestParam Integer id,@RequestParam Boolean auditstatus){

        if (id!=null && auditstatus ){
            Song song1 = auditMusicServiceservice.FindOne(id);
            auditMusicServiceservice.updateAudit(auditstatus,id);
            if(song1!=null)return Result.succ("你已经审核过了");
            auditMusicServiceservice.AuditPass(auditMusicServiceservice.oneAuditMusic(id));    //防止重复上传
            return Result.succ("操作成功");
        }
        return Result.fail("操作失败");
    }

//    @GetMapping("/allmusicTab")
//    public Map<String,Object> allMusicTab(){
//        Map<String,Object> map=new HashMap<>();
//        map.put("language",musicTabService.FindAllLanguage());
//        map.put("scene",musicTabService.FindAllScene());
//        map.put("emotion",musicTabService.FindAllEmotion());
//        map.put("topic",musicTabService.FindAllTopic());
//        map.put("code",200);
//        map.put("message","操作成功");
//
//        return map;
//    }
}
