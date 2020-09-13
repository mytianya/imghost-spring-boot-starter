package vip.codehome.imghost.service.impl;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import vip.codehome.imghost.ImgHostProperties;
import vip.codehome.imghost.common.UploadResult;
import vip.codehome.imghost.service.ImgHostService;
import vip.codehome.imghost.util.JSONUtil;
import vip.codehome.imghost.util.SMMSApiUtil;

/**
 * @author:
 * @description:
 * @creatTime: 2020/9/10--22:53
 * {"success":true,"code":"success","message":"Upload success.",
 * "data":{"file_id":0,"width":4608,"height":3456,
 * "filename":"f067996a4743d55b38c900b7e74677df.jpg",
 * "storename":"Y2I5PcMpxiwWUav.jpg",
 * "size":1528779,
 * "path":"\/2020\/09\/11\/Y2I5PcMpxiwWUav.jpg",
 * "hash":"LfOxlyJESMaVnvbZtcQgw1Fjki",
 * "url":"https:\/\/i.loli.net\/2020\/09\/11\/Y2I5PcMpxiwWUav.jpg",
 * "delete":"https:\/\/sm.ms\/delete\/LfOxlyJESMaVnvbZtcQgw1Fjki",
 * "page":"https:\/\/sm.ms\/image\/Y2I5PcMpxiwWUav"},"RequestId":"3429CAA0-26D7-4F5B-BCA3-3AEF6EB23417"}
 * {"success":false,"code":"image_repeated",
 * "message":"Image upload repeated limit, this image exists at: https:\/\/i.loli.net\/2020\/09\/12\/dbs4LOUJghy7olm.jpg",
 * "images":"https:\/\/i.loli.net\/2020\/09\/12\/dbs4LOUJghy7olm.jpg",
 * "RequestId":"160F5EB9-970D-4405-85F7-D8F044DD2E1D"}
 */
@Data
@AllArgsConstructor
public class SMMSImgHostService implements ImgHostService {
    private final static Logger log= LoggerFactory.getLogger(SMMSImgHostService.class);
	ImgHostProperties imgHostProperties;
	@Override
    public UploadResult upload(File file) {
        String res=SMMSApiUtil.upload(imgHostProperties.getSmms().getToken(),file);
        log.debug("图片{}上传请求结果为:{}",file.getName(),res);
        Map<String,Object> retMap=JSONUtil.toObject(res, new TypeReference<Map<String,Object>>() {
        });
        Map<String,Object> tmpMap= MapUtils.getMap(retMap,"data");
        UploadResult uploadResult=new UploadResult();
        if("success".equals(MapUtils.getString(retMap,"code"))){
            uploadResult.setOk(true);
            uploadResult.setUrl(tmpMap.get("url").toString());
        }else{
            uploadResult.setOk(false);
            uploadResult.setErrMsg(MapUtils.getString(retMap,"message"));
        }
        return uploadResult;
    }
}
