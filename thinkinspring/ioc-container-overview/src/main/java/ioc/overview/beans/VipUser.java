package ioc.overview.beans;

import ioc.overview.annotation.Vip;

/**
 * @program: think-in-spring
 * @description: 重要用户
 * @author: wangbo67@github.com
 * @created: 2020-08-07 22:40
 **/
@Vip
public class VipUser extends User {
    private String ipNum;

    public String getIpNum() {
        return ipNum;
    }

    public void setIpNum(String ipNum) {
        this.ipNum = ipNum;
    }

    @Override
    public String toString() {
        return "VipUser{" +
                "ipNum='" + ipNum + '\'' +
                "} " + super.toString();
    }
}
