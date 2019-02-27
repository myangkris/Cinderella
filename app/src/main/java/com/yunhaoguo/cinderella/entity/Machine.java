package com.yunhaoguo.cinderella.entity;
/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella
 * 文件名:     Machine
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/20 12:34 AM
 * 描述:      TODO
 */


public class Machine {
    //0 available 1 inuse 2 broken
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
