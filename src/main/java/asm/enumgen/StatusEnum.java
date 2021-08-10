package asm.enumgen;

/**
 * @description: StatusEnum
 * @author: Steven
 * @time: 2021/8/4 22:20
 */
public enum StatusEnum {
    ONLINE(100, "在线"),
    OFFLINE(101, "下线"),
    BUSY(102, "忙碌")
    ;

    private int code;
    private String desc;

    StatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
