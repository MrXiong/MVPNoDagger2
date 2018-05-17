package com.mvp.ultimate.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;


@Entity
public class ZxUser {
    @Property(nameInDb = "id")
    private int id;
    @Property(nameInDb = "name")
    private String name;
    private String phone;
    @Generated(hash = 574699029)
    public ZxUser(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    @Generated(hash = 1368428813)
    public ZxUser() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
