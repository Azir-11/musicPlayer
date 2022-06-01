package xyz.qcbyt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.service.AuditMusicService;
import xyz.qcbyt.utils.Result;

import java.util.List;

/**
 * 音乐审核控制器
 */
@RestController
@CrossOrigin
@RequestMapping("/audit")
public class AuditMusicController {
    @Autowired
    private AuditMusicService auditMusicService;

    /**
     * 功能:   管理员审核歌曲
     * @param status 前端传 true 或者 false
     * @param id     一首歌的id
     * @return
     */
    @RequestMapping("/auditmusic")
    public Result AuditMusic(@RequestParam("status") Boolean status,Integer id){
        Integer pageNum=null;//无用值，为了调接口用
        Integer pageSize=null;
        if(status!=null && id!=null){
            Integer integer = auditMusicService.UpdateAuditStatus(status,id);
            if(integer!=0){
                //如果当前审核歌曲的 审核状态为true 则添加到歌曲列表之中!!!!!!
                List<Song> song = auditMusicService.findSong(id, pageNum, pageSize);//判断查询结果是否为空
                if(!song.isEmpty() && song.get(0).getAuditstatus()){
                    Integer integer1 = auditMusicService.auditSuccess(song.get(0));
                }
                return Result.succ("操作成功");
            }
        }
        return Result.fail("操作失败,数据库没有此记录，或者传值错误");
    }

    @RequestMapping("/findSong")
    public Result FindSong(Integer id,Integer pageNum,Integer pageSize){
        List<Song> song = auditMusicService.findSong(id, pageNum, pageSize);
        return Result.succ(song);
    }
//    //审核成功后 自动 添加到歌曲列表
//    @RequestMapping("/auditSuc")
//    public Result auditSuccess(Song song){
//        if(song!=null){
//            Integer integer = auditMusicService.auditSuccess(song);
//            if(integer!=0){
//                return Result.succ(200,"操作成功");
//            }
//        }
//        return Result.fail(400,"操作失败");
//    }
}
