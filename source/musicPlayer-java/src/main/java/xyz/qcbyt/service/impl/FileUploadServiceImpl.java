package xyz.qcbyt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.mapper.FileUploadMapper;
import xyz.qcbyt.service.FileUploadService;
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private FileUploadMapper fileUploadMapper;

    @Override
    public Integer addSong(Song song) {
        if(song.getSingername()!=null){
            fileUploadMapper.addSong(song);
            return 1;
        }
        return null;
    }
}
