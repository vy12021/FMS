package com.framework.util.validatecode;

import com.framework.Constant;
import com.springapp.mvc.web.controller.SysmgmtController;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Calendar;
import java.util.TimerTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by LIUYONG on 14-3-4.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClearValicodeTask extends TimerTask {

    private final Log log = LogFactory.getLog(getClass());
    /** 晚上12点 也即0点* */
    private static final int C_SCHEDULE_HOUR = 0;

    /** 任务是否执行完或是否到达指定时间标志* */
    private static boolean isRunning = false;

    private ServletContext sc = null;

    public ClearValicodeTask() {
    }

    @Override
    public void run() {
        Calendar cal = Calendar.getInstance();
        if (!isRunning) {
            isRunning = true;
            if (log.isDebugEnabled()){
                log.debug("开始执行清除验证码临时目录任务");
            }
            try {
                File[] files = new File(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/") + "/WEB-INF/pages/resources/style/images/codetemp/").listFiles();
                if(files != null && files.length >0) {
                    for(File file : files) {
                        if(file.exists()) {
                            file.delete();
                        }
                    }
                }
                isRunning = false;
            }catch (Exception e){
                e.printStackTrace();
            }
            if (log.isDebugEnabled()){
                log.debug("清除验证码临时目录任务完成");
            }
        }
    }
}
