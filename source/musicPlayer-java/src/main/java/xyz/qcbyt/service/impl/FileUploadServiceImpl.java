package xyz.qcbyt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.qcbyt.entity.Lrc;
import xyz.qcbyt.entity.Song;
import xyz.qcbyt.mapper.FileUploadMapper;
import xyz.qcbyt.service.FileUploadService;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private FileUploadMapper fileUploadMapper;


    @Override
    public Lrc findOneLrc(String lrcpath) {
        if(lrcpath!=null && !lrcpath.equals("")){
            return fileUploadMapper.findOneLrc(lrcpath);
        }
        return null;
    }

    @Override
    public Integer addLrc(Lrc lrc) {
        if (lrc!=null){
            return fileUploadMapper.addLrc(lrc);
        }
        return 0;
    }
}
