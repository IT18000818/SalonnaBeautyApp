package com.example.beautician;

public class appointment {

    //Shh means Start hour
    //Smm means Start minute
    //Sss means Start second
    // In Ess,Emm & Ess, E means end


    private int hrs;
    private int mins;
    private int seconds;

    private int totalprice;

    public appointment() {

    }



    public int getHrs() {
        return hrs;
    }

    public void setHrs(int hrs) {
        this.hrs = hrs;
    }

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}
