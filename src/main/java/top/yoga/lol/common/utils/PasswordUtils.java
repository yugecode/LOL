package top.yoga.lol.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * 对密码进行加密
 *
 * @author luojiayu
 * @date 2020/1/6 16:49
 */
@Component
public class PasswordUtils {
    //指定散列算法为MD5,还有别的算法如：SHA256、SHA1、SHA512
    private String algorithmName = "md5";
    //散列迭代次数 md5(md5(pwd)): new Md5Hash(pwd, salt, 2).toString()
    private int hashIterations = 2;

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    //加密：输入明文得到密文
    public String encodePassword(String pwd, String salt) {
        //user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                pwd,
                ByteSource.Util.bytes(salt),
                hashIterations).toHex();

        return newPassword;
    }

    //解密，得到密码
    public boolean verifyPassword(String targetPassword, String pwd, String salt) {
        String newPassword = this.encodePassword(targetPassword, salt);
        if (newPassword.equals(pwd)) {
            return true;
        } else {
            return false;
        }
    }
}
