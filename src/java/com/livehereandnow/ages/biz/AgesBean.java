/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livehereandnow.ages.biz;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
//import javax.inject.Singleton;

/**
 *
 * @author mark
 */
//@ManagedBean
@Startup
@Singleton
//@SessionScoped
//@RequestScoped
public class AgesBean {
    private int counter;
    private String cmd;
    private String feedback;
public void doCmd(){
    feedback="this is "+ (counter++);
}
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
}
