package xyz.qcbyt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.qcbyt.service.MusicTabService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/musicTab")
public class MusicTabController {
    @Autowired
    private MusicTabService musicTabService;

    @GetMapping("/allmusicTab")
    public Map<String,Object> allMusicTab(){
        Map<String,Object> map=new HashMap<>();
        map.put("language",musicTabService.FindAllLanguage());
        map.put("scene",musicTabService.FindAllScene());
        map.put("emotion",musicTabService.FindAllEmotion());
        map.put("topic",musicTabService.FindAllTopic());
        map.put("code",200);
        map.put("message","操作成功");

        return map;
    }
}
