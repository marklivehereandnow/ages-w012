/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livehereandnow.ages.biz;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Singleton;

/**
 *
 * @author mark
 */
@ManagedBean
//@Singleton
@SessionScoped
//@RequestScoped
public class Biz {

//    private String NOCARD = "http://2nd2go.org/ages/img/m1000.jpg";
    private String[] addr = new String[30];

    private String IMG_DIR = "http://2nd2go.org/ages/img/abcd/";
    private String username;
    private String player;
    private String imgSize = "a";

    Server server;
    GameLive gl;
    int[] cardId;

    public Biz() {
        server = new Server();
       
        cardId = new int[13];

        updateImgLink();
    }
    int removeCnt;

    private void updateImgLink() {
        gl = server.getGameLive();
        System.out.println("  init... " + gl.toString());

        String[] idStr = gl.getCardRow().split(",");

        for (int k = 0; k < 13; k++) {
            cardId[k] = Integer.parseInt(idStr[k]);
            System.out.println("" + k + " " + cardId[k]);
        }

        System.out.println("...init");
        removeCnt = 0;
        for (int k = 0; k < 13; k++) {
            addr[k] = IMG_DIR + this.getImgSize() + cardId[k] + ".jpg";
            System.out.println(""+addr[k]);
        }

    }

    public String getImgSize() {
        return imgSize;
    }

    public void doNewGame() {
        updateImgLink();
    }

    public String getAddr(int k) {
        return addr[k];
    }

    public void setAddr(int k, String str) {
        removeCnt++;

        addr[k] = str;
    }

    public void doSetImgSizeA() {
        imgSize = "a";
        updateImgLink();
    }

    public void doSetImgSizeB() {
        imgSize = "b";
        updateImgLink();
    }

    public void doSetImgSizeC() {
        imgSize = "c";
        updateImgLink();
    }

    public void doSetImgSizeD() {
        imgSize = "d";
        updateImgLink();
    }

    public void doAct(int k) {
//        if (!isAllowed()) {
//            System.out.println("...NOT PLAYER, NOT ALLOW TO TOUCH CARD ROW ");
//            return;
//        }
        System.out.println(player + ", take-card " + k);
        setAddr(k, IMG_DIR + this.getImgSize() + "1000.jpg");
//        setAddr("http://2nd2go.org/ages/img/p1000.jpg");
    }
}
