package com.example.unitkesihatanehealth;

public class UserClass {
    public String address, fullname, id, location, password, privilege;

    public UserClass() {

    }

    public UserClass ( String address, String fullname, String id, String location, String password, String privilege) {

        this.address = address;
        this.fullname = fullname;
        this.id = id;
        this.location = location;
        this.password = password;
        this.privilege = privilege;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
