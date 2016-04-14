/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bladwin.web;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jvp.obj.bean.calendarBean;
import mgn.obj.cal.calendarObj;

/**
 *
 * @author lmeans
 */
@ManagedBean
@ViewScoped
public class calMgr  extends mgrVideoProduction_EL  implements Serializable {
    private calendarBean calendarBean;
    // ---------------------------------------------------------------
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }

    /**
     * @return the calendarBean
     */
    public void cancelEdit(){
        
    }
    public void saveEdit(){
        calendarObj calendarObj = new calendarObj();
        calendarObj.saveCalBean(calendarBean, mgrVideoProduction.getObj());
    }
    public calendarBean getCalendarBeanHome() {
        if (calendarBean == null){
            calendarObj calendarObj = new calendarObj();
            //calendarBean = calendarObj.getsqlSelectScheduleId(-1, mgrVideoProduction.getObj());   
        }
        return getCalendarBean();
    }
    
    public calendarBean getCalendarBean() {
        if (calendarBean == null){
            calendarBean = new calendarBean();
        }
        return calendarBean;
    }
}
