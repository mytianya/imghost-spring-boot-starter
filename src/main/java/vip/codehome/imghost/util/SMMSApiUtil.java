package vip.codehome.imghost.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;

/**
 * @author: www.codehome.vip 欢迎关注微信号：程序员众推
 * @description:
 * @creatTime: 2020/9/10--22:59
 */
public class SMMSApiUtil {
    public static Logger log= LoggerFactory.getLogger(SMMSApiUtil.class);
    private final static OkHttpClient client = new OkHttpClient();
    /**
     * 	登录获取token 图片上传只需要sm.ms自带token即可
     * @param username
     * @param password
     * @return
     */
    @Deprecated
    public static String login(String apiUrl,String username,String password,String secret){
    		RequestBody formBody=new FormBody.Builder()
    				.add("username",username)
    				.add("password",password)
    				.build();	
    		Request request=new Request.Builder()
    				.url(apiUrl)
    				.post(formBody)
    				.addHeader("Authorization",secret)
    				.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36")
    				.build();
    		Call call=client.newCall(request);
    		try {
        		Response response=call.execute();
        		return new String(response.body().bytes());
    		}catch(IOException e) {
    			throw new RuntimeException("网络请求失败",e);
    		}
    }
    public static String upload(String secret,File file) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("smfile", file.getName(),RequestBody.create(MediaType.parse("image/"+file.getName().substring(file.getName().indexOf(".")+1)), file))
        		.addFormDataPart("format", "json");
        Request request=new Request.Builder()
        		.addHeader("Authorization",secret)
				.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36")
        		.url("https://sm.ms/api/v2/upload")
        		.post(builder.build())
        		.build();
        Call call=client.newCall(request);
    	try {
    		Response response=call.execute();
    		return new String(response.body().bytes());
		}catch(IOException e) {
			throw new RuntimeException("图片上传失败",e);
		}
    }

}
