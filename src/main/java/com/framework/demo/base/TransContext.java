package com.framework.demo.base;

import java.math.BigDecimal;

//处理逻辑前后交易传值使用
public class TransContext {
    private String dateReq;
    private BigDecimal amtTr;
    private String dbIdwltNo;
    private String crIdwltNo;

    public String getDateReq() {
        return dateReq;
    }

    public void setDateReq(String dateReq) {
        this.dateReq = dateReq;
    }

    public BigDecimal getAmtTr() {
        return amtTr;
    }

    public void setAmtTr(BigDecimal amtTr) {
        this.amtTr = amtTr;
    }

    public String getDbIdwltNo() {
        return dbIdwltNo;
    }

    public void setDbIdwltNo(String dbIdwltNo) {
        this.dbIdwltNo = dbIdwltNo;
    }

    public String getCrIdwltNo() {
        return crIdwltNo;
    }

    public void setCrIdwltNo(String crIdwltNo) {
        this.crIdwltNo = crIdwltNo;
    }
}
