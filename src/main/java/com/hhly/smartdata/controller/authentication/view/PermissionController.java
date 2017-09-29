package com.hhly.smartdata.controller.authentication.view;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.authentication.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Iritchie.ren on 2017/9/22.
 */
@Controller
@RequestMapping("/sys/perm")
public class PermissionController extends BaseController{

    @Autowired
    private PermissionService permissionService;
}
