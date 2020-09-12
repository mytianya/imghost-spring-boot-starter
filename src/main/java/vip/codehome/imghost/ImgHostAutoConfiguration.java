package vip.codehome.imghost;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vip.codehome.imghost.service.impl.SMMSImgHostService;

/**
 * @author:
 * @description:
 * @creatTime: 2020/9/10--22:22
 */
@Configuration
@EnableConfigurationProperties(ImgHostProperties.class)
@ConditionalOnProperty(prefix = "imghost",name = "enabled",havingValue = "true",matchIfMissing = true)
public class ImgHostAutoConfiguration {
	private final ImgHostProperties imgHostProperties;

	public ImgHostAutoConfiguration(ImgHostProperties imgHostProperties) {
		this.imgHostProperties = imgHostProperties;
	}

	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix="imghost.smms",name="token")
	@Bean
	public SMMSImgHostService imgHostService() {
		return new SMMSImgHostService(imgHostProperties);
	}
}
