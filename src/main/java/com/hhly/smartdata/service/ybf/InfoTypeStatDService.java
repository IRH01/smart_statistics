package com.hhly.smartdata.service.ybf;

import com.hhly.smartdata.model.ybf.InfoTypeStatD;

public interface InfoTypeStatDService{

    /**
     * 获取每天某个类型的咨询总数信息
     *
     * @return
     */
    public InfoTypeStatD getInfoTypeStatD(String infoType, String domain, String date);
}
