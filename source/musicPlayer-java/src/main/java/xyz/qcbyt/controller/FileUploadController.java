package xyz.qcbyt.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.service.FileUploadService;
import xyz.qcbyt.utils.FileUploadUtil;
import xyz.qcbyt.utils.Result;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 歌曲上传控制器 (上传页面)
 * @author 茅启聪 2022 05 28
 *
 */
@RestController
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public Object upload(@RequestPart("file")MultipartFile file, HttpServletRequest request,@RequestPart Song song){
        return saveFile(file,request,song);
    }
    @PostMapping("/multiUpload")
    public Object multiUpload(@RequestPart("file")MultipartFile[] files,HttpServletRequest request, @RequestPart Song song){
        System.out.println("文件的个数:"+files.length);
        for (MultipartFile f : files){
            saveFile(f,request,song);
        }
        Integer integer = fileUploadService.addSong(song);
        return Result.succ(200,"上传成功");
    }
    private Object saveFile(MultipartFile file,HttpServletRequest request,Song song){
        if (file.isEmpty()){
            return Result.fail(400,"未选择文件");
        }
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String suffixName = filename.substring(filename.lastIndexOf(".")); //获取后缀名 .mp3 .lrc .png
        String preName=filename.substring(0,filename.lastIndexOf("."));    //获取文件前缀名
        if(!(suffixName.equals(".mp3")||suffixName.equals(".jpg")||suffixName.equals(".jpeg")||suffixName.equals(".lrc")||suffixName.equals(".png"))){
            return Result.fail(400,"文件格式不正确,格式应为mp3,jpg,jpeg,lrc(lrc为歌词格式文件)");
        }
        String filePathMp3 = "src/main/resources/static/mp3/";      //指定上传的路径
        String filePathImg = "src/main/resources/static/img/";      //指定上传的路径
        String filePathLrc = "src/main/resources/static/lrc/";      //指定上传的路径
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
            if(song.getMusicname()==null){      //如果用户 不填写 歌曲名字
                song.setMusicname(preName);     //就自动根据上传的文件名 设置歌曲名字
            }else {
                song.setMusicname(song.getMusicname());
            }

            song.setSongurl(localFile.toString());  //设置歌曲音乐文件url

        }else if(suffixName.equals(".png")||suffixName.equals(".jpg")||suffixName.equals(".jpeg")){
            localFile = new File(filePathImg+filename);
            song.setCoverimg(localFile.toString());//如果是上传图片就设置 歌曲封面图片
        }else {
            localFile = new File(filePathLrc+filename);
            song.setLrcurl(localFile.toString());   //如果用户传歌词文件 就设置歌词文件url插入到数据库
        }
        try {
//            file.transferTo(localFile); //把上传的文件保存至本地 (报错！！！！)
            FileUtils.copyInputStreamToFile(file.getInputStream(), localFile);
            System.out.println(file.getOriginalFilename()+" 上传成功");

            if(suffixName.equals(".mp3")){
              long videoDuration = FileUploadUtil.getVideoDuration(file);
              song.setDuration((int) videoDuration); //如果是MP3格式就 设置 歌曲时长
            }

        }catch (IOException e){
            e.printStackTrace();
            return Result.fail(400,"上传失败");
        }
        return Result.succ(200,"上传成功");
    }

}
