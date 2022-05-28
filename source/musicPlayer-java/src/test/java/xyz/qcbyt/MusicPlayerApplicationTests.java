package xyz.qcbyt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


class MusicPlayerApplicationTests {

    @Test
    void contextLoads() throws ParseException {
        String filename="周杰伦-《花海》.mp3";
        String preName=filename.substring(0,filename.lastIndexOf("."));
        System.out.println(preName);
    }

}
