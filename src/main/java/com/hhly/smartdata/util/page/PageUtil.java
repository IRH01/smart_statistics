package com.hhly.smartdata.util.page;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class PageUtil{
    private static ThreadLocal<Page> threadPage = new ThreadLocal<Page>();

    public static Page getPage(){
        return threadPage.get();
    }

    public static Page removePage(){
        Page page = threadPage.get();
        threadPage.set(null);
        return page;
    }

    /**
     * 开始分页，只能对接下来的一个进行分页
     */
    public static Page startPage(Page page){
        threadPage.set(page);
        initPageParam(page);
        return page;
    }

    /**
     * 开始分页，只能对接下来的一个进行分页
     */
    public static Page startPage(int pageNo, int pageSize){
        Page page = new Page(pageNo, pageSize);
        return startPage(page);
    }

    private static void initPageParam(Page page){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        page.initParams(req);
    }

}
