package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.LoginTrack;

import java.util.List;
import java.util.Map;

public interface PlatformGoldConsumeMapper{

    List<Map<String, Object>> selectYesterdayConsumeUser() throws Exception;
}