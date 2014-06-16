package com.vy12021.framework.sysmgmt.index.controller;

import com.vy12021.framework.sysmgmt.index.model.Resource;
import com.vy12021.framework.sysmgmt.index.service.ResourceService;
import com.vy12021.framework.Constant;
import com.vy12021.framework.sysmgmt.security.model.SysModule;
import com.vy12021.framework.sysmgmt.security.service.SysModuleService;
import com.vy12021.framework.util.common.FormatUtils;
import com.vy12021.framework.util.dom.DomHandler;
import com.vy12021.framework.util.upload.UploadService;
import com.vy12021.framework.util.video.VideoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    private SysModuleService sysModuleService;

    @Autowired
    private FormatUtils formatUtils;

    @RequestMapping(value = "/tree/{moduleId}", method = RequestMethod.GET)
    public String tree(HttpServletRequest request, @PathVariable("moduleId")String moduleId, Map<String, Object> map) {
        List<SysModule> sysModuleList = sysModuleService.findModulesBySuperId((Long) SecurityUtils.getSubject().getSession().getAttribute("userId"), Long.parseLong(moduleId));
        map.put("moduleList", sysModuleList);
        return "sysmgmt/tree";
    }

    @RequestMapping(value = "/changepwd", method = RequestMethod.GET)
    //@ResponseBody
    public String changepwd(HttpServletRequest request) {
        return "sysmgmt/changepwd";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Map<String, Object> map) {
        List<SysModule> sysModuleList = sysModuleService.findModulesByUser((Long) SecurityUtils.getSubject().getSession().getAttribute("userId"));
        map.put("modules", sysModuleList);
        return "sysmgmt/index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String preUpload(HttpServletRequest request, Map<String, Object> map) {
        return "sysmgmt/upload";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, Map<String, Object> map) {
        //上传接收保存文件名
        com.vy12021.framework.sysmgmt.index.model.Resource resource = new com.vy12021.framework.sysmgmt.index.model.Resource();
        String fileName = uploadService.upload(request, Constant.contextPath + Constant.uploadPath);
        resource.setConvertStatus(0);
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
        com.vy12021.framework.sysmgmt.index.model.Resource resource = resourceService.findByIds(formatUtils.convertStringArrayToLong(id.toString().split(","))).get(0);
        map.put("resource", resource);
        String url= "/" + resource.getUri() + resource.getName();
        return "sysmgmt/player";
    }

    @RequestMapping(value = "/shareCreate", method = RequestMethod.POST)
    public String shareCreate(HttpServletRequest request, com.vy12021.framework.sysmgmt.index.model.Resource resource, Map<String,Object> map) {
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
