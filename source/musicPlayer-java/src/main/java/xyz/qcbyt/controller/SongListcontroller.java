package xyz.qcbyt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.service.SongListSerive;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/search")
public class SongListcontroller {

    @Autowired
    private SongListSerive songListSerive;

    @GetMapping("/ser")
    public List<Song> serach(@RequestParam("name") String name){
        return songListSerive.songserach(name);
    }


}
