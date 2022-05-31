package xyz.qcbyt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.mapper.SongListmapper;
import xyz.qcbyt.service.SongListSerive;

import java.util.List;

@Service
public class SongListServielmpl implements SongListSerive {

    @Autowired
    SongListmapper songListmapper;

    @Override
    public List<Song> songserach(String name) {
        return songListmapper.songserach(name);
    }
}
