package vip.codehome.imghost.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResult {
    boolean ok;
    String url;
    String errMsg;
}
