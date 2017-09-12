package com.hhly.smartdata.service.authentication;

import java.io.File;

public interface FileUploadService {

    public String uploadPic(File file, String fileExtensionName, boolean needSmallPic);

    
}
