package com.jhonyu.framework.frame.enums;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description: 异常类型枚举
 * @className: ExcpTypeEnum
 * @userName: jiangyu
 * @date: 2015年11月26日 上午11:21:49
 */
public enum ExcpTypeEnum {

    CONTROLLER("001", "控制器层CONTROLLER"), 
    
    SERVICE("002", "业务逻辑层SERVICE"), 
    
    DAO("003", "数据访问层DAO"), 
    
    COMPONENT("004", "组件COMPONENT");
    
    /**
     * 编号
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    private ExcpTypeEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

    public String getCode()
    {
        return code;
    }

    public String getDesc()
    {
        return desc;
    }
    
    public static ExcpTypeEnum getByValue(String code) {
        for (ExcpTypeEnum typeEnum : ExcpTypeEnum.values()) {
            if (typeEnum.getCode().equals(code))
                return typeEnum;
        }
        return null;
    }

    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (ExcpTypeEnum e : ExcpTypeEnum.values()) {
            map.put(e.code, e.desc);
        }
        return map;
    }
}
