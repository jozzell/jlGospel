/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jvp.obj.video.videoBean;

/**
 *
 * @author lmeans
 */
public class videoMetrixMgrBean  implements Serializable{
    List<videoBean> l = new ArrayList<videoBean>();
    videoBean bean = new videoBean();
   
    public void put(videoBean b){
        l.add(b);
    }
    public int getSize(){
        return l.size();
    }
    public videoBean pullMainVideoBean(){
        if (l.isEmpty()){
            return bean;
        } else {
            return l.get(0);
        }
    }
    // ----------------------------------------------------
    public boolean isTopSubVideoBeanAd(){
        return l.size() < 2;
    }
    public boolean isTopSubVideoBean(){
        return l.size() >= 2;
    }
    public videoBean pullTopSubVideoBean(){
        if (l.size() < 2){
            return bean;
        } else {
            return l.get(1);
        }
    }
    // ----------------------------------------------------
    public boolean isMiddleSubVideoBeanAd(){
        return l.size() < 3;
    }
    public boolean isMiddleSubVideoBean(){
        return l.size() >=3;
    }
    public videoBean pullMiddleSubVideoBean(){
        if (l.size() < 3){
            return bean;
        } else {
            return l.get(2);
        }
    }
    // ----------------------------------------------------
    public boolean isBottomSubVideoBeanAd(){
        return l.size() < 4;
    }
    public boolean isBottomSubVideoBean(){
        return l.size() >=4;
    }
    public videoBean pullBottomSubVideoBean(){
        if (l.size() < 4){
            return bean;
        } else {
            return l.get(3);
        }
    }
    
}
