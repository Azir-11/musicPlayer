package xyz.qcbyt.utils;

import com.alibaba.fastjson.JSON;

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
//                String regex = "\\[(\\d{1,2}):(\\d{1,2}).(\\d{1,2})\\]";
                String regex = "\\[(\\d{1,2}):(\\d{1,2}).(\\d{1,2})]";
                String regex1 = "\\[(\\d{1,2}):(\\d{1,2}).(\\d{1,3})]";
                //创建pattern对象
                Pattern pattern = Pattern.compile(regex);
                Pattern pattern1 = Pattern.compile(regex1);
                //每次读取一行字符串
                String lineStr = null;
                while ((lineStr = bufferedReader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(lineStr);
                    Matcher matcher1 = pattern1.matcher(lineStr);
//                    while (matcher.find() ) {
//                        //存储当前时间和文字的容器
//                        Map<String, String> hashMap = new HashMap<>();
//                        //分钟
//                        String min = matcher.group(1);
//                        //秒
//                        String second = matcher.group(2);
//                        //毫秒
//                        String mill = matcher.group(3);
//                        String time = min + ":" + second + "." + mill;
//                        String text = lineStr.substring(matcher.end());
//                        hashMap.put(time, text);
//                        list.add(hashMap);
//                    }
                    while (matcher1.find()) {
                        //存储当前时间和文字的容器
                        Map<String, String> hashMap = new HashMap<>();
                        //分钟
                        String min = matcher1.group(1);
                        //秒
                        String second = matcher1.group(2);
                        //毫秒
                        String mill = matcher1.group(3);
                        String time = min + ":" + second + "." + mill;
                        String text = lineStr.substring(matcher1.end());
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

}
