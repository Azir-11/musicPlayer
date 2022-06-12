package xyz.qcbyt.utils;

import ch.qos.logback.core.util.FileUtil;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;

public class FileUploadUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 获取时长
     *
     * @param file 音频/视频文件
     * @return
     */
    public static long getVideoDuration(MultipartFile file) {
        long duration = 0;
        try {
            File source = new File(getFilePath() + file.getOriginalFilename());
//            file.transferTo(source);//报错！！！！不要用
            FileUtils.copyInputStreamToFile(file.getInputStream(), source);
            Encoder encoder = new Encoder();
            MultimediaInfo info = encoder.getInfo(source);
            duration = info.getDuration();
            if (source.exists()) {
                source.delete();
            }
            return duration / 1000;
        } catch (Exception e) {
            log.error("获取时长", e);
            return duration;
        }
    }

    /**
     * 获取路径
     *
     * @return
     * @throws Exception
     */
    private static String getFilePath() throws Exception {
        //获取当前文件的根路径
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) path = new File("");

        //盘符路径
        StringBuilder codeUrl = new StringBuilder();
        codeUrl.append(path.getAbsolutePath()).append("/static/video/");
        File file = new File(codeUrl.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return codeUrl.toString();
    }

}
