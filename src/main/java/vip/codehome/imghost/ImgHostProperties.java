package vip.codehome.imghost;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author:
 * @description:
 * @creatTime: 2020/9/10--22:22
 */
@Data
@ConfigurationProperties(prefix = "imghost")
public class ImgHostProperties {
    SMMS smms;
    @Data
    public static class SMMS{
        String token;
    }
}
