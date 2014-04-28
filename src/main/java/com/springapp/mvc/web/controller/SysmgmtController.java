package com.springapp.mvc.web.controller;

import com.springapp.mvc.model.Resource;
import com.springapp.mvc.mybatis.service.ResourceService;
import com.framework.Constant;
import com.framework.util.common.FormatUtils;
import com.framework.util.dom.DomHandler;
import com.framework.util.upload.UploadService;
import com.framework.util.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by LIUYONG on 14-2-9.
 */

@Controller
@RequestMapping("/sysmgmt")
public class SysmgmtController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private FormatUtils formatUtils;

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String tree() {
        return "sysmgmt/tree";
    }

    @RequestMapping(value = "/changepwd", method = RequestMethod.GET)
    //@ResponseBody
    public String changepwd(HttpServletRequest request) {
        return "sysmgmt/changepwd";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "sysmgmt/index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String preUpload(HttpServletRequest request, Map<String, Object> map) {
        return "sysmgmt/upload";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, Map<String, Object> map) {
        //上传接收保存文件名
        com.springapp.mvc.model.Resource resource = new com.springapp.mvc.model.Resource();
        String fileName = uploadService.upload(request, Constant.contextPath + Constant.uploadPath);
        resource.setConvertStatus("0");
        resource.setName(fileName);
        UUID uuid = UUID.randomUUID();
        resource.setResourceUUID(uuid.toString());
        resource.setUri(Constant.uploadPath);
        //对资源文件尝试转码，支持的视频格式都会被转为.flv格式
        String msg = videoService.comvertFormat(Constant.contextPath + Constant.uploadPath,
                fileName, Constant.contextPath + Constant.uploadPath, uuid.toString(), "flv", resource);
        map.put("msg", msg);

        return "sysmgmt/upload";
    }

    @RequestMapping(value = "/resourcesList", method = RequestMethod.GET)
    public String resourceList(HttpServletRequest request, Map<String, Object> map) {
        List<Resource> resourceList = resourceService.findAll();
        map.put("resourceList", resourceList);
        return "sysmgmt/resourcesList";
    }

    @RequestMapping(value = "/resource/view/{id}", method = RequestMethod.GET)
    public String play(HttpServletRequest request, Map<String, Object> map, @PathVariable Integer id) {
        com.springapp.mvc.model.Resource resource = resourceService.findByIds(formatUtils.convertStringArray(id.toString().split(","))).get(0);
        map.put("resource", resource);
        String url= "/" + resource.getUri() + resource.getName();
        return "sysmgmt/player";
    }

    @RequestMapping(value = "/shareCreate", method = RequestMethod.POST)
    public String shareCreate(HttpServletRequest request, com.springapp.mvc.model.Resource resource, Map<String,Object> map) {
        resourceService.save(resource);
        return "sysmgmt/shareCreate";
    }


    @RequestMapping(value = "/share", method = RequestMethod.GET)
    public String share(HttpServletRequest request, Map<String, Object> map) {
        DomHandler domHandler = new DomHandler();
        String htmlContent = domHandler.getContent("http://v.youku.com/v_show/id_XNjcyMzg4OTQw.html?f=21915175&ev=2");
        String domContent = domHandler.getSubStringFromStrToStr(htmlContent, "<body", "</body>");
        map.put("domContent", domContent);

        return "share";
    }

}
