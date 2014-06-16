package com.vy12021.framework.util.common;

/**
 * Created by LIUYONG on 2014/6/16 0016.
 */
import com.vy12021.framework.sysmgmt.security.model.SysModule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 获取子节点
 */
public class NodeUtil {

    private List<Long> returnList = new ArrayList<Long>();

    /**
     * 根据父节点的ID获取所有子节点
     * @param sysModuleList 分类表
     * @param superId 传入的父节点ID
     * @return String
     */
    public String getChildSysModules(List<SysModule> sysModuleList, Long superId) {
        if(sysModuleList == null && superId == null) {
            return "";
        }
        for (Iterator<SysModule> iterator = sysModuleList.iterator(); iterator.hasNext();) {
            SysModule sysModule = (SysModule) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (sysModule.getSuperId() == 0 && superId == sysModule.getId()) {
                recursionFn(sysModuleList, sysModule);
            }
            // 二、遍历所有的父节点下的所有子节点
			/*if (SysModule.getParentId()==0) {
				recursionFn(sysModuleList, sysModule);
			}*/
        }
        return returnList.toString();
    }

    private void recursionFn(List<SysModule> sysModuleList, SysModule sysModule) {
        List<SysModule> childList = getChildList(sysModuleList, sysModule);// 得到子节点列表
        if (hasChild(sysModuleList, sysModule)) {// 判断是否有子节点
            returnList.add(sysModule.getId());
            Iterator<SysModule> it = childList.iterator();
            while (it.hasNext()) {
                SysModule n = (SysModule) it.next();
                recursionFn(sysModuleList, n);
            }
        } else {
            returnList.add(sysModule.getId());
        }
    }

    // 得到子节点列表
    private List<SysModule> getChildList(List<SysModule> sysModuleList, SysModule sysModule) {
        List<SysModule> sysModules = new ArrayList<SysModule>();
        Iterator<SysModule> it = sysModuleList.iterator();
        while (it.hasNext()) {
            SysModule n = (SysModule) it.next();
            if (n.getSuperId() == sysModule.getId()) {
                sysModules.add(n);
            }
        }
        return sysModules;
    }

    // 判断是否有子节点
    private boolean hasChild(List<SysModule> sysModuleList, SysModule sysModule) {
        return getChildList(sysModuleList, sysModule).size() > 0 ? true : false;
    }

}

