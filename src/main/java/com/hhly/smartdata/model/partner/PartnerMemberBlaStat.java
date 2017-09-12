package com.hhly.smartdata.model.partner;

import java.util.Date;

/**
 * 合作伙伴财务对账统计
 * @author wanghuang
 *
 */
public class PartnerMemberBlaStat {
	//月份
	private Date monthId;
	//合作伙伴账号userId
	private String partnerUserId;
	//推广编码
	private String partnerNo;
	//本月合格会员数
	private int qualMemberMonUCnt;
	//截止到该月份的所有合格会员数
	private int qualMemberTotalUCnt;
	//下级代理数
	private int subAgentUCnt;
	//会员佣金
	private double memberAmount;
	//代理佣金
	private double agentAmount;
	
	public Date getMonthId() {
		return monthId;
	}
	public void setMonthId(Date monthId) {
		this.monthId = monthId;
	}
	public String getPartnerUserId() {
		return partnerUserId;
	}
	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}
	public String getPartnerNo() {
		return partnerNo;
	}
	public void setPartnerNo(String partnerNo) {
		this.partnerNo = partnerNo;
	}
	public int getQualMemberMonUCnt() {
		return qualMemberMonUCnt;
	}
	public void setQualMemberMonUCnt(int qualMemberMonUCnt) {
		this.qualMemberMonUCnt = qualMemberMonUCnt;
	}
	public int getQualMemberTotalUCnt() {
		return qualMemberTotalUCnt;
	}
	public void setQualMemberTotalUCnt(int qualMemberTotalUCnt) {
		this.qualMemberTotalUCnt = qualMemberTotalUCnt;
	}
	public int getSubAgentUCnt() {
		return subAgentUCnt;
	}
	public void setSubAgentUCnt(int subAgentUCnt) {
		this.subAgentUCnt = subAgentUCnt;
	}
	public double getMemberAmount() {
		return memberAmount;
	}
	public void setMemberAmount(double memberAmount) {
		this.memberAmount = memberAmount;
	}
	public double getAgentAmount() {
		return agentAmount;
	}
	public void setAgentAmount(double agentAmount) {
		this.agentAmount = agentAmount;
	}
}
