package com.example.qraai;

public class Cdetails {
    Integer sn=0,eqtyp=0,eprice=0;
    String edate="",eby="",name="";
    public Cdetails() {

    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public Integer getEqtyp() {
        return eqtyp;
    }

    public void setEqtyp(Integer eqtyp) {
        this.eqtyp = eqtyp;
    }

    public Integer getEprice() {
        return eprice;
    }

    public void setEprice(Integer eprice) {
        this.eprice = eprice;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getEby() {
        return eby;
    }

    public void setEby(String eby) {
        this.eby = eby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
