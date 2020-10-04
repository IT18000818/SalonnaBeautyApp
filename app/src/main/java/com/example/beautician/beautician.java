package com.example.beautician;

public class beautician {
    private String nic_id;
    private String first_name;
    private String last_name;
    private String birthday;
    private String address;
    private String email;
    private String services;

    public beautician() {
    }

    public String getNic_id() {
        return nic_id;
    }

    public void setNic_id(String nic_id) {
        this.nic_id = nic_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }
}
