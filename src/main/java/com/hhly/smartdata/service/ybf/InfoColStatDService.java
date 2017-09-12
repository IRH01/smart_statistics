package com.hhly.smartdata.service.ybf;

import com.hhly.smartdata.model.ybf.InfoColStatD;

public interface InfoColStatDService{

    /**
     * 获取每天某个类型的咨询总数信息
     *
     * @return
     */
    public InfoColStatD getInfoColStatD(String infoType, String domain, String date);
}
