package xyz.qcbyt.controller;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xyz.qcbyt.Vo.SongVo;
import xyz.qcbyt.service.impl.MusicListServiceImpl;
import xyz.qcbyt.utils.Result;
import java.util.HashMap;

import java.util.Map;
@RestController
@CrossOrigin
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/musicList")
public class MusicListController {
    @Autowired
    private MusicListServiceImpl musicListService;

    //传id 就查一条 , 不传id就查询所有
    @GetMapping("/findAll")
    public Result FindSong(@RequestParam(required = false) Integer id){
        Map<String,Object> map=new HashMap<>();
        if(id==null){
            return Result.succ(JSON.toJSON(musicListService.SelectAll()));
        }
        Map<String,Object> oneSong = musicListService.Selectone(id);
        return Result.succ(oneSong); //字符串转 list
    }

    //用户上传歌曲到审核表
    @PostMapping("/addSongToAudit")
    public Result addSongToAudit(@RequestBody SongVo song){
        if (song!=null){
            if (musicListService.addSongUnique(song)==null){ //防止用户重复上传
                musicListService.addSongToAudit(song);
                return Result.succ("上传成功");
            }
        }
        return Result.fail("操作失败");
    }
}
