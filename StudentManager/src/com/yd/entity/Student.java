package com.yd.entity;

import java.util.Date;

/**
 * 学生的实体类
 * @author john
 * @Date 2018年1月11日
 * @company 亚帝科技有限公司
 */
public class Student {
	private int sid;
	private String sname;
	private int sage;
	private String ssex;
	private Date sbirthday;
	private String saddress;
	private int cid;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getSage() {
		return sage;
	}
	public void setSage(int sage) {
		this.sage = sage;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public Date getSbirthday() {
		return sbirthday;
	}
	public void setSbirthday(Date sbirthday) {
		this.sbirthday = sbirthday;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", sage=" + sage + ", ssex=" + ssex + ", sbirthday="
				+ sbirthday + ", saddress=" + saddress + ", cid=" + cid + "]";
	}
}
