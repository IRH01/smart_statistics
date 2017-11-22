package com.hhly.smartdata.controller.smartdata;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by Iritchie.ren on 2017/11/16.
 */
@RestController
public class SseController{

    @RequestMapping(value = "push", produces = "text/event-stream")
    public String push() throws InterruptedException{
        Random random = new Random();
        Thread.sleep(1000);
        return "data:Testing 1,2,3" + random.nextInt() + "\n\n";
    }
}
