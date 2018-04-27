package com.taotao.manager.service;

import com.taotao.common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    PictureResult uploadPicture(MultipartFile multipartFile);
}
