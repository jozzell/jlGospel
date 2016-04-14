/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jvp.obj.video.videoBean;
import jvp.obj.video.videoObj;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class videoMetrixMgrObj extends mgrVideoProduction_EL implements Serializable {

    private List<videoMetrixMgrBean> metrixList;
    private int videoType;

    public videoMetrixMgrObj() {

    }
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;

    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }

    public List<videoMetrixMgrBean> getMetrixList() {
        int x = mgrVideoProduction.getVideoType();
        if (metrixList == null || mgrVideoProduction.isRefreshList()) { //videoType != x ||
            mgrVideoProduction.setRefreshList(false);
            videoType = x;
            genList();
        }
        return metrixList;
    }

    private synchronized void genList() {
        videoMetrixMgrBean bean = null;
        if (metrixList != null) {
            metrixList.clear();
        }
        metrixList = new ArrayList<videoMetrixMgrBean>();
        List<videoBean> list = null;
        if (mgrVideoProduction.isHome()) {
             list = (new videoObj()).getVideoBeanListRanked(1,getObj());
        } else {
            list = (new videoObj()).getVideoBeanList(videoType, getObj());
        }
        if (list != null && list.size() > 0) {
            for (videoBean l : list) {
                if (bean != null && bean.getSize() == 3) {
                    metrixList.add(bean);
                    bean = new videoMetrixMgrBean();
                } else if (bean == null) {
                    bean = new videoMetrixMgrBean();
                }
                bean.put(l);
            }
            if (bean != null) {
                metrixList.add(bean);
            }
        }

    }

}
