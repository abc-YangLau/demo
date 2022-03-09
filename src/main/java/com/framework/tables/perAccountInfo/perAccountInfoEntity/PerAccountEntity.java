package com.framework.tables.perAccountInfo.perAccountInfoEntity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Setter
@Getter
@Component
public class PerAccountEntity {
    //账号
    private String accountNo;
    //客户号
    private String custNo;
    //手机号
    private String numMob;
    //邮箱地址
    private String email;
    //客户姓名
    private String accountName;
    //账户昵称
    private String accountNick;
    //密码
    private String accountPassword;
    //证据类型
    private String certificateType;
    //证件号
    private String certificateNum;
    //账户余额
    private BigDecimal accountBal;
    //交易日期
    private String dateTr;
    //上一日交易日期
    private String accountTrLast;
    //上一日日终余额
    private BigDecimal accountBalLast;
    //上上一日交易日期
    private String accountTrBeforeLast;
    //上上日日终余额
    private BigDecimal accountBalBeforeLast;
    //账户开立日期
    private String dateOpen;
    //开立渠道
    private String chanelOpen;
    //开立方式
    private String typeOpen;
    //日借累计交易金额
    private BigDecimal dateDbtrAcml;
    //日贷累计交易金额
    private BigDecimal dateCrtrAcml;
    //年借累计交易金额
    private BigDecimal yearDbtrAcml;
    //年贷累计交易金额
    private BigDecimal yearCrtrAcml;
}
