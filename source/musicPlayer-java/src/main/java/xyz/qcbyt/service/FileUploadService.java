package xyz.qcbyt.service;

import xyz.qcbyt.entity.Song;

public interface FileUploadService {
    //上传歌曲文件
    Integer addSong(Song song);
}
