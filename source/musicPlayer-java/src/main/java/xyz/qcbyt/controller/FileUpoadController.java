package xyz.qcbyt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.qcbyt.utils.DateUtils;
import xyz.qcbyt.utils.Result;

import javax.validation.constraints.NotNull;
import java.io.File;

@RestController
public class FileUpoadController {

    @PostMapping("/upload")
    public Object upload(@NotNull(message = "文件不能为空") MultipartFile fileUpload){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        System.out.println("文件名为 "+fileName);
        //获取文件后缀名

        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String preName=fileName.substring(0,fileName.indexOf("."));
        if(!(suffixName.equals(".mp3") ||suffixName.equals(".lrc"))){
            return  Result.fail(400,"上传失败，文件格式为mp3,或者mp4");
        }
        DateUtils dateUtils=new DateUtils();
        fileName=preName+"-"+dateUtils.getDate()+suffixName;
        //重新生成文件名
//        fileName = UUID.randomUUID()+suffixName;

        //指定本地文件夹存储图片，写到需要保存的目录下
        String filePath1 = "E:\\img\\mp3\\";
        String filePath2 = "E:\\img\\lrc\\";
        try {
            //将图片保存到static文件夹里
            if(suffixName.equals(".mp3")){
                fileUpload.transferTo(new File(filePath1+fileName));
            }else {
                fileUpload.transferTo(new File(filePath2+fileName));
            }
            //返回提示信息
            return  Result.succ(200,"上传成功",fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("上传失败，文件格式为mp3,或者mp4");
        }
    }

}
