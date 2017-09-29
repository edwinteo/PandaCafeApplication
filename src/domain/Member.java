/*
Author : Teo Jia Han 
Class  : DC02 D2
*/

package domain;

public class Member {

    private String id;
    private String name;
    private String addr;
    private String phno;
    private int lp;
    private String gender;
    private String expd;
    private String regd;
    private String dob;
    private String status;
    
    public Member(){
    }
    
    public Member(String id , String name, String addr, String phno,String gender, String regd, String expd,String dob , String status){
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.phno = phno;
        this.gender = gender;
        this.regd = regd;
        this.dob = dob;
        this.expd = expd;
        this.status = status;
        
    }
    public Member(String name){
        this.name = name;
    }
    public Member(String id,String name,String addr,String phno,int lp,String gender,String expd,String regd,String dob,String status){
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.phno = phno;
        this.lp = lp;
        this.gender = gender;
        this.expd = expd;
        this.regd = regd;
        this.dob = dob;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExpd() {
        return expd;
    }

    public void setExpd(String expd) {
        this.expd = expd;
    }

    public String getRegd() {
        return regd;
    }

    public void setRegd(String regd) {
        this.regd = regd;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
