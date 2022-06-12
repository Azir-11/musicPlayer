package xyz.qcbyt.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.qcbyt.entity.Lrc;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.service.FileUploadService;
import xyz.qcbyt.utils.FileUploadUtil;
import xyz.qcbyt.utils.LrcUtil;
import xyz.qcbyt.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 歌曲上传控制器 (上传页面)
 * @author 茅启聪 2022 05 28
 *
 */
@RestController
@CrossOrigin
@Transactional(rollbackFor = Exception.class)
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
    Lrc lrc=new Lrc(); //设置插入数据库的lrc 信息

    @PostMapping("/upload")
    public Object upload(@RequestPart("file")MultipartFile file, HttpServletRequest request){
        Result result = saveFile(file, request);
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String suffixName = filename.substring(filename.lastIndexOf(".")); //获取后缀名 .mp3 .lrc .png
        Map<String,Object> map=new HashMap<>();
        if(suffixName.equals(".mp3")){
            long videoDuration = FileUploadUtil.getVideoDuration(file);
            map.put("mp3src",result.getData());
            map.put("duration",videoDuration);
        }
        else if(suffixName.equals(".lrc")) {
            if(lrc!=null){
                map.put("lrcpath",result.getData());
                map.put("lrcid",lrc.getId());
            }else {
                return Result.fail("文件已存在,上传失败");
            }

        } else {
            map.put("coverimg",result.getData());
        }
        return Result.succ(200,"上传成功",map);
    }
    @PostMapping("/multiUpload")
    public Object multiUpload(@RequestPart("file")MultipartFile[] files,HttpServletRequest request){
        System.out.println("文件的个数:"+files.length);
        Map<String,Object> map=new HashMap<>();
        for (MultipartFile f : files){
            Result result = saveFile(f, request); //前端上传的文件 返回给前端
            String filename = f.getOriginalFilename(); //获取上传文件原来的名称
            String suffixName = filename.substring(filename.lastIndexOf(".")); //获取后缀名 .mp3 .lrc .png
            //定义返回的 路径
            if(suffixName.equals(".mp3"))map.put("mp3src",result.getData());
            else if(suffixName.equals(".lrc"))map.put("lrcpath",result.getData());
            else map.put("coverimg",result.getData());
        }
        return Result.succ(200,"上传成功",map);
    }

    private Result saveFile(MultipartFile file,HttpServletRequest request){
        if (file.isEmpty()){
            return Result.fail(400,"未选择文件");
        }
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称

        String suffixName = filename.substring(filename.lastIndexOf(".")); //获取后缀名 .mp3 .lrc .png
        String lrcName=null;
        if (suffixName.equals(".lrc")){
             lrcName=file.getOriginalFilename(); //获取lrc 文件全名
        }
        String preName=filename.substring(0,filename.lastIndexOf("."));    //获取文件前缀名
        if(!(suffixName.equals(".mp3")||suffixName.equals(".jpg")||suffixName.equals(".jpeg")||suffixName.equals(".lrc")||suffixName.equals(".png"))){
            return Result.fail(400,"文件格式不正确,格式应为mp3,jpg,jpeg,lrc(lrc为歌词格式文件)");
        }
        String filePathMp3 = "C:/music/mp3/";      //指定上传的路径
        String filePathImg = "C:/music/img/";      //指定上传的路径
        String filePathLrc = "C:/music/lrc/";      //指定上传的路径
        File temp=null;
        if(suffixName.equals(".mp3")){
            temp = new File(filePathMp3);
        }else if(suffixName.equals(".png")||suffixName.equals(".jpg")||suffixName.equals(".jpeg")){
            temp = new File(filePathImg);
        }else {
           temp = new File(filePathLrc);
        }
        if (!temp.exists()){
            temp.mkdirs();
        }

        File localFile=null;
        if(suffixName.equals(".mp3")){
            localFile = new File(filePathMp3+filename);
//            if(song.getMusicname()==null){      //如果用户 不填写 歌曲名字
//                song.setMusicname(preName);     //就自动根据上传的文件名 设置歌曲名字
//            }else {
//                String musicname = song.getMusicname();
//                song.setMusicname(musicname);
//            }



        }else if(suffixName.equals(".png")||suffixName.equals(".jpg")||suffixName.equals(".jpeg")){
            localFile = new File(filePathImg+filename);
//            song.setCoverimg(localFile.toString());//如果是上传图片就设置 歌曲封面图片
        }else {
            localFile = new File(filePathLrc+filename);
            LrcUtil lrcUtil=new LrcUtil();
//            String lrc = lrcUtil.getLrc(filePathLrc + filename); //解析lrc
//            System.out.println("lrc:  "+lrc);

        }
        try {
//            file.transferTo(localFile); //把上传的文件保存至本地 (报错！！！！)
            FileUtils.copyInputStreamToFile(file.getInputStream(), localFile);
            System.out.println(file.getOriginalFilename()+" 上传成功");

            if(suffixName.equals(".mp3")){
//              long videoDuration = FileUploadUtil.getVideoDuration(file);
////              song.setDuration((int) videoDuration); //如果是MP3格式就 设置 歌曲时长
            }

        }catch (IOException e){
            e.printStackTrace();
            return Result.fail(400,"上传失败");
        }

        if(suffixName.equals(".mp3")){
            return Result.succ(200,"上传成功",filePathMp3+filename);

        }
        else if(suffixName.equals(".lrc")){
            LrcUtil lrcUtil=new LrcUtil();
            String lrcutil = lrcUtil.getLrc(filePathLrc + filename); //解析lrc
            System.out.println("lrc:  "+lrcutil);

            lrc.setLrc(lrcutil); //歌词解析的结果存到数据库
            lrc.setLrcpath(filePathLrc + filename);
            if(fileUploadService.findOneLrc(filePathLrc + filename)==null){ //如果数据库没有这个歌词，那就插入
                Integer integer = fileUploadService.addLrc(lrc);
                lrc.setId(lrc.getId()); //设置自增id 返回给前端
            }

            return Result.succ(200,"上传成功",filePathLrc+filename);
        }
        else {
            return Result.succ(200,"上传成功",filePathImg+filename);
        }
    }

}
