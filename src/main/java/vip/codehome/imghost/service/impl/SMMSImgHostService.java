package vip.codehome.imghost.service.impl;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections.MapUtils;
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
 */
@Data
@AllArgsConstructor
public class SMMSImgHostService implements ImgHostService {
	ImgHostProperties imgHostProperties;
	@Override
    public UploadResult upload(File file) {
        String res=SMMSApiUtil.upload(imgHostProperties.getSmms().getToken(),file);
        Map<String,Object> retMap=JSONUtil.toObject(res, new TypeReference<Map<String,Object>>() {
        });
        UploadResult uploadResult=new UploadResult();
        if("success".equals(MapUtils.getString(retMap,"code"))){
            uploadResult.setOk(true);
            Map<String,Object> tmpMap= (Map<String, Object>) retMap.get("data");
            uploadResult.setUrl(tmpMap.get("url").toString());
            uploadResult.setUrl("上传到sm.ms成功");
        }else{
            uploadResult.setOk(false);
        }
        return uploadResult;
    }
}
