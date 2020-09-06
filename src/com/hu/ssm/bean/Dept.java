package com.hu.ssm.bean;

import java.io.Serializable;

public class Dept implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer did;
	private String dname;

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	@Override
	public String toString() {
		return "Dept [did=" + did + ", dname=" + dname + "]";
	}

	public Dept(Integer did, String dname) {
		super();
		this.did = did;
		this.dname = dname;
	}

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}

}
