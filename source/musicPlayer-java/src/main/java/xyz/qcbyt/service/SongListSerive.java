package xyz.qcbyt.service;

import xyz.qcbyt.entity.Song;

import java.util.List;

public interface SongListSerive {

    //搜素
    List<Song> songserach(String name);
}
