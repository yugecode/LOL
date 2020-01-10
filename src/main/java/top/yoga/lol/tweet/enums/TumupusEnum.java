package top.yoga.lol.tweet.enums;

import lombok.Getter;
import top.yoga.lol.common.exception.AppException;

/**
 * 点赞枚举
 *
 * @author luojiayu
 * @date 2020/1/10 15:45
 */
@Getter
public enum TumupusEnum {

    TUMUPUS(1, "点赞"),
    TUMUPUSNOT(0, "未点赞");

    private Integer code;

    private String desc;

    TumupusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
