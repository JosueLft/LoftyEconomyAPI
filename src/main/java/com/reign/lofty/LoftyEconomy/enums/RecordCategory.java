package com.reign.lofty.LoftyEconomy.enums;

public enum RecordCategory {

    SPENDING(0),
    GAINS(1),
    INVESTMENT(2),
    PROFIT(3);

    private int code;

    RecordCategory(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static RecordCategory valueOf(int code) {
        for(RecordCategory value : RecordCategory.values()) {
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Record Category");
    }
}