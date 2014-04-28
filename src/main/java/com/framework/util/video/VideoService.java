package com.framework.util.video;

import com.springapp.mvc.mybatis.service.ResourceService;
import com.framework.Constant;
import com.framework.util.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by LIUYONG on 14-2-11.
 */
@Component
public class VideoService {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ResourceService resourceService;
    /**
     * TODO 对资源进行格式转换
     *
     * @param resourcePath 资源路径
     * @param resourcefileName 资源名称
     * @param desPath 目标路径
     * @param desfileName 目标文件名称
     * @param desFormat 目标文件格式
     */
    public String comvertFormat(String resourcePath, String resourcefileName, String desPath, String desfileName, String desFormat, com.springapp.mvc.model.Resource resource) {
        resource.setDesFmt(desFormat);
        resourceService.save(resource);

        //格式支持判断
        if(desPath != null && !desPath.equals("")
                && desfileName != null && !desfileName.equals("")
                && desFormat != null && !desFormat.equals("")
                ) {
            if(resourcePath != null && resourcePath.equals("")
                    || (resourcefileName != null && resourcefileName.equals(""))) {
                return "文件上传失败，请重新上传";
            } else {
                if(isFormatSupport(resourcefileName)) {
                    //视频转码
                    /*Process process = Runtime.getRuntime().exec("notepad.exe");*/
                    System.out.println(Constant.contextPath + Constant.ffmpegPath);

                    Thread thread = new Thread(new DaemonThread(resourceService, resource, resourcePath, resourcefileName, desPath, desfileName, desFormat));
                    thread.start();

                    System.out.println("thread id :" + Thread.currentThread().getId());
                    System.out.println("thread name: " + Thread.currentThread().getName());

                    return "upload success, please waiting convert format completed";
                } else {
                    return "non support format";
                }
            }
        } else {
            return "convert failure";
        }
    }

    /**
     * 从支持格式列表中匹配
     */
    public boolean isFormatSupport(String fileName) {
        fileName = uploadService.getFileFormat(fileName);
        for (String supportFormat : Constant.decodeSupport) {
            if(supportFormat.equals(fileName)) {
                return true;
            }
        }
        return false;
    }
}



