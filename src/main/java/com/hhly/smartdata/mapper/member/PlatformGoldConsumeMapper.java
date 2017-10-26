package com.hhly.smartdata.mapper.member;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface PlatformGoldConsumeMapper{

    List<Map<String, Object>> selectYesterdayConsumeUser() throws Exception;
}