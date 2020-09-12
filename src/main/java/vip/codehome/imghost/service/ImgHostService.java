package vip.codehome.imghost.service;

import vip.codehome.imghost.common.UploadResult;

import java.io.File;

/**
 * @author:
 * @description:
 * @creatTime: 2020/9/10--22:52
 */
public interface ImgHostService {
    UploadResult upload(File file);
}
