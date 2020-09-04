package com.duanyi.load;

public class User {

    public String name;
    public String sex;
    public String csrq;
    public int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public User() {
        //System.out.println("***************load  User******************");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void sout(){

        System.out.println("============自己的加载器加载类调用方法=========================");
    }
}
