package com.example.demo.common;

/**
 * @author lagon
 * @time 2017/6/28 9:43
 * @description 服务提供方枚举
 */
public enum ProviderRealm {

    PHO("PHO","普惠运营"),
    LAS("LAS","贷后系统"),
	ACS("ACS","账户体系"),
    CRMS("CRMS","债匹系统"),
    YQD("YQD","云钱袋系统"),
    JKD("JKD","线上借款端"),
    PGW("PGW","结算平台");


    private String mark;
    private String name;

    ProviderRealm(String mark, String name) {
        this.mark=mark;
        this.name=name;
    }

    public String getMark() {
        return mark;
    }

    public String getName() {
        return name;
    }

    public static ProviderRealm getEnum(String mark){
        for (ProviderRealm providerRealm : ProviderRealm.values()) {
            if (providerRealm.getMark().equals(mark)) {
                return providerRealm;
            }
        }
        return null;
    }

}
