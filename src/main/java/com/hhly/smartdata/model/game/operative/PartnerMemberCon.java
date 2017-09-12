package com.hhly.smartdata.model.game.operative;

/**
 * 代理合作伙伴关联关系
 *
 * @author wanghuang
 */
public class PartnerMemberCon{
    //会员账号ID
    private String memberId;
    //代理ID
    private String partnerId;
    //会员注册时填写的代理编码
    private String partnerNo;
    //代理姓名
    private String partnerName;

    public String getMemberId(){
        return memberId;
    }

    public void setMemberId(String memberId){
        this.memberId = memberId;
    }

    public String getPartnerId(){
        return partnerId;
    }

    public void setPartnerId(String partnerId){
        this.partnerId = partnerId;
    }

    public String getPartnerNo(){
        return partnerNo;
    }

    public void setPartnerNo(String partnerNo){
        this.partnerNo = partnerNo;
    }

    public String getPartnerName(){
        return partnerName;
    }

    public void setPartnerName(String partnerName){
        this.partnerName = partnerName;
    }
}
