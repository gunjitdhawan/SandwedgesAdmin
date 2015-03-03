package com.grappes.sandwedgesadmin;

public class Order {
	
	String objid;
	String gt;
	String address;
	String user;
	String date;
	String det;
	String tot;
	String disc;
	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date=date;
	}
	public String getObjectId()
	{
		return objid;
	}
	public void setObjectId(String objid)
	{
		this.objid=objid;
	}
	
	public String getGrandtotal()
	{
		return gt;
	}
	public void setGrandtotal(String gt)
	{
		this.gt=gt;
	}
	
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	
	public String getUser()
	{
		return user;
	}
	public void setUser(String user)
	{
		this.user=user;
	}
	public String getProdDet()
	{
		return det;
	}
	public void setProdDet(String det)
	{
		this.det=det;
	}
	public String getiTotal()
	{
		return tot;
	}
	public void setiTotal(String tot)
	{
		this.tot=tot;
	}
	public String getdisc()
	{
		return disc;
	}
	public void setdisc(String disc)
	{
		this.disc=disc;
	}
}
