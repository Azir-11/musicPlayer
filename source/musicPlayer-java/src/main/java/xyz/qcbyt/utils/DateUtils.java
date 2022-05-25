package xyz.qcbyt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public String getDate(){
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        return format.replace(" ", "").replace(":","").replace("-","");

    }
    public String getDate(String format){
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat(format);
        return dateFormat.format(date);

    }
}
