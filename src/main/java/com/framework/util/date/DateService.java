package com.framework.util.date;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LIUYONG on 14-2-22.
 */
@Data
@Component
public class DateService {

    private String now = "";

    public String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.now = simpleDateFormat.format(date);
        return now;
    }

}
