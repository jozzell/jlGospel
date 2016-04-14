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
import javax.faces.bean.RequestScoped;
import jvp.obj.bean.calendarBean;
import mgn.obj.cal.calendarObj;

/**
 *
 * @author lmeans
 */
@ManagedBean
@RequestScoped
public class calMgrDisplay extends mgrVideoProduction_EL  implements Serializable{
    private List<calendarBean> list;
    // ---------------------------------------------------------------
    @ManagedProperty("#{mgrVideoProduction}")
    private mgrVideoProduction mgrVideoProduction = null;
    public void setMgrVideoProduction(mgrVideoProduction mgrVideoProduction) {
        this.mgrVideoProduction = mgrVideoProduction;
    }

    /**
     * @return the list
     */
    public List<calendarBean> grabListHome() {
        if (list == null){
            list = new ArrayList<calendarBean>();
            calendarObj calendarObj = new calendarObj();
            //calendarBean b = calendarObj.getsqlSelectScheduleId(-1, mgrVideoProduction.getObj());
            //list.add(b);
        }
        return list;
    }
    
}
