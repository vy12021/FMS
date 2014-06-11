package com.vy12021.framework.util.upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Created by LIUYONG on 14-2-11.
 */

@Component
public class UploadService {

    public String upload(HttpServletRequest request, String uploadPath) {
        //上传接收
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String fileName = "";
        try {

            List items = upload.parseRequest(request);
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                    System.out.println("表单参数名:" + item.getFieldName() + "，表单参数值:" + item.getString("UTF-8"));
                } else {
                    if (item.getName() != null && !item.getName().equals("")) {
                        System.out.println("上传文件的大小:" + item.getSize());
                        System.out.println("上传文件的类型:" + item.getContentType());
                        // item.getName()返回上传文件在客户端的完整路径名称
                        System.out.println("上传文件的名称:" + item.getName());
                        //
                        File tempFile = new File(item.getName());
                        fileName = item.getName();
                        //上传文件的保存路径
                        File file = new File(uploadPath, tempFile.getName());
                        item.write(file);
                        request.setAttribute("upload.message", "上传文件成功！");
                    }else{
                        request.setAttribute("upload.message", "没有选择上传文件！");
                    }
                }
            }
        }catch(FileUploadException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("upload.message", "上传文件失败！");
        }

        return fileName;
    }

    public String exceptFormat(String fileName) {
        fileName = fileName.substring(0,fileName.lastIndexOf("."));
        return fileName;
    }

    public String getFileFormat(String fileName) {
        String fileFormat = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileFormat;
    }

}
