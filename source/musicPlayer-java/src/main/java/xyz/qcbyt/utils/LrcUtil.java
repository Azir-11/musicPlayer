package xyz.qcbyt.utils;

import com.alibaba.fastjson.JSON;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.qcbyt.controller.MusicPlayController;
import xyz.qcbyt.entity.Song;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LrcUtil {
    /**
     * 歌词解析
     * @param path 歌词地址
     * @return 歌词信息list
     */
    private List<Map<String, String>> parse(String path) {
        //存储歌词信息的容器
        ArrayList<Map<String, String>> list = new ArrayList<>();
        try {
            //字符串编码格式
            String encoding = "UTF-8";
            File file = new File(path);
            //判断文件是否存在
            if (file.exists() && file.isFile()) {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(reader);
                //时间标签正则匹配 例：[02:34.94]
                String regex = "\\[(\\d{1,2}):(\\d{1,2}).(\\d{1,2})\\]";
                //创建pattern对象
                Pattern pattern = Pattern.compile(regex);
                //每次读取一行字符串
                String lineStr = null;
                while ((lineStr = bufferedReader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(lineStr);
                    while (matcher.find()) {
                        //存储当前时间和文字的容器
                        Map<String, String> hashMap = new HashMap<>();
                        //分钟
                        String min = matcher.group(1);
                        //秒
                        String second = matcher.group(2);
                        //毫秒
                        String mill = matcher.group(3);
                        String time = min + ":" + second + "." + mill;
                        String text = lineStr.substring(matcher.end());
                        hashMap.put(time, text);
                        list.add(hashMap);
                    }
                }
                reader.close();
                return list;
            } else {
                System.out.println("找不到指定的文件" + path);
            }
        } catch (Exception e) {
            System.out.println("读取文件出错");
            e.printStackTrace();
        }
        return null;
    }

    private void printLrc(List<Map<String, String>> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("没有任何歌词信息");
        } else {
            for (Map<String, String> map : list) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    System.out.println("[" + entry.getKey() + "]" + entry.getValue());
                }
            }
        }
    }

    public  String getLrc(String path){
        path.replace("/", "\\");
        System.out.println(path);
        LrcUtil lrc = new LrcUtil();
        List<Map<String, String>> list = lrc.parse(path);
        String s = JSON.toJSONString(list);
        System.out.println(s);
        return s;
    }

//    public static void main(String[] args) {
//        String path="src\\main\\resources\\static\\lrc\\七里香_歌词.lrc";
//        LrcUtil lrc = new LrcUtil();
//        List<Map<String, String>> lrc1 = lrc.getLrc(path);
//
//        JSON.toJSONString(lrc1);
//        System.out.println(lrc1);
//    }
}
