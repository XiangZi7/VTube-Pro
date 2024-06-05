package com.vtube.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnum implements IEnum<String> {
    MALE("M", "男"),
    FEMALE("F", "女");

    private final String code;
    private final String description;

    GenderEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @JsonValue
    public String getDescription() {
        return this.description;
    }
}