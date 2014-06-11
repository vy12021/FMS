package com.vy12021.framework.util.validatecode;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

/**
 * Created by LIUYONG on 14-3-4.
 */
public class ClearValicodeImageListener implements ServletContextListener {

    private Timer myTimer = null;
    private ClearValicodeTask myTask = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
        servletContextEvent.getServletContext().log("定时器已经启动！");
        myTimer = new Timer(true);
        myTask = new ClearValicodeTask();
        try {
            myTask.setSc(servletContextEvent.getServletContext());
            //设置任务计划，启动和间隔时间(每隔24(24*60*60*1000)小时触发一次，即凌晨0点)
            //调用myTask，0表示任务无延迟
            myTimer.schedule(myTask, 30*1000, 60*1000) ;
        } catch (Exception e) {
            System.out.println("异常信息如下：" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //在这里关闭监听器，所以在这里销毁定时器。
        myTimer.cancel();
        servletContextEvent.getServletContext().log("定时器已经销毁！");
    }
}
