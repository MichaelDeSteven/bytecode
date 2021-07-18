package asm.enumgen;

public enum Beverage {
    COLA(0),
    JUICE(1)
    ;

    private int code;

//    private String desc;
//
//    private String remark;
//
//    Beverage(int code, String desc, String remark) {
//        this.code = code;
//        this.desc = desc;
//        this.remark = remark;
//    }

    Beverage(int code) {
        this.code = code;
    }
}
