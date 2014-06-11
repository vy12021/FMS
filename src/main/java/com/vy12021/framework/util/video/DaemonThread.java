package com.vy12021.framework.util.video;

/**
 * Created by LIUYONG on 14-2-12.
 */

import com.vy12021.framework.sysmgmt.index.model.Resource;
import com.vy12021.framework.sysmgmt.index.service.ResourceService;
import com.vy12021.framework.Constant;
import com.vy12021.framework.util.upload.UploadService;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.*;
/**
 * 守护线程
 * 监控新线程的状态
 */
@Data
@Component
public class DaemonThread implements Runnable {

    public DaemonThread(){}

    private Resource resource;
    private Process process;
    private String resourcePath;
    private String resourcefileName;
    private String desPath;
    private String desfileName;
    private String desFormat;

    private ResourceService resourceService;

    private UploadService uploadService = new UploadService();

    public DaemonThread(ResourceService resourceService, Resource resource,
                        String resourcePath, String resourcefileName, String desPath,
                        String desfileName, String desFormat) {
        this.resourceService = resourceService;
        this.resource = resource;
        this.resourcePath = resourcePath;
        this.resourcefileName = resourcefileName;
        this.desPath = desPath;
        this.desfileName = desfileName;
        this.desFormat = desFormat;
    }

    @Override
    public void run() {
        String cmd = "start " + Constant.contextPath + Constant.ffmpegPath +
                " -threads 4 -i " + resourcePath + resourcefileName +
                " -ab 32 -ar 22050 -vcodec libx264 -qscale 6 -r 25 -s 600x400 -flags +loop " +
                "-crf 24 -bt 400k -vol 200 -vf yadif "
                + desPath + desfileName + "." + desFormat;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Constant.contextPath + Constant.uploadPath + desfileName + "." + "bat")));
            bw.write(cmd);
            bw.flush();
            bw.close();
            this.process = Runtime.getRuntime().exec(Constant.contextPath + Constant.uploadPath + desfileName + "." + "bat");
            /**
             * 以下非常重要
             * 要把程序输出缓冲区截获到java中
             * 只有这样守护线程wartFor()才能正常执行(即子线程结束之后才继续执行)
             */
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str;
            while((str=bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("thread id :" + Thread.currentThread().getId());
            System.out.println("thread name: " + Thread.currentThread().getName());
            /**
             * 返回0的时候转换完成
             */
            int exitCode = process.waitFor();
            if(0 == exitCode) {
                resource.setConvertStatus("1");
                resourceService.update(resource);
            } else {
                resource.setConvertStatus("-1");
                resourceService.update(resource);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

