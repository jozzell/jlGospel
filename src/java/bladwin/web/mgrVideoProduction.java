/*

Download FastStone Photo Resizer
http://www.faststone.org/FSResizerDownload.htm


 */

package bladwin.web;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jvp.obj.bean.userBean;
import jvp.obj.eNum.eNumVideoIDs;
import jvp.obj.video.menu.videoMgrObjMenu;
import jvp.obj.video.menu.videoMgrObjMenuInterface;
import mgn.obj.usr.userObj;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lmeans
 */

@ManagedBean(name = "mgrVideoProduction")   
@SessionScoped
public final class mgrVideoProduction extends mgrVideoProduction_EL implements videoMgrObjMenuInterface,Serializable {
    
    //private videoMgrObj videoMgrObj;
    private videoMgrObjMenu videoMgrObjMenu;
    private boolean start = true,login=false,videoEditing=false,refreshList=true,home=true;
    private String newPath = null;
    private userBean userBean;
    private final String               
            panelWest="./menu.xhtml";
     private String 
             misc,
            url="videoViewPgEditingBrw-Top.xhtml",
            panelCenter= null;
    private int cntr=0,videoSysId,videoId,selectId;//videoType=208,
    public eNumVideoIDs Current_eNumVideoIDs = eNumVideoIDs.BeerScott;
    public mgrVideoProduction(){
         refreshSession();
    }
    
   
    public void userLogin(){
        getUserBean().setUserPass(null);
        forwardCntr("user/login.xhtml");
    }
    public void chkLogin(){
         userObj userObj = new userObj();
         String email = this.getUserBean().getEMail();
         this.userBean = userObj.login(getUserBean().getEMail(), getUserBean().getUserPass(), this.getObj());
         if(userBean != null) {
             getUserBean().setUserPass(null);
             login = true;
             this.setHome();
         } else {
             this.getUserBean().setEMail(email);
             login = false;
             RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
             
         }
     }
    public void userLogout(){
        this.login = false;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        try {
            ec.redirect(ec.getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(mgrVideoProduction.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.forward();
       
    }
     public boolean isLogin() {
        return login;
    }
    
    // ==========================================
    public void setVideoId(int i){
        this.videoId = i;
       
    }
    public int getVideoId(){
        return videoId;
    }
    public String getVideoGrpDesc(){
        return this.Current_eNumVideoIDs.getTitle();
    }
    @Override
    public void setHome(){
        //url = "pgCal/BaldwinBlazerHome_Edit.xhtml";
        url = "pgCal/BaldwinBlazerHome.xhtml";
        this.home = true;
        setVideoType();
        //this.setVideoGrpDesc("Spinney Hill Documentary");
        //videoType = 208;
        //panelCenter = "./spinneyHill_Cover.xhtml";
       //panelCenter = "./videoViewPgEditingBrw.xhtml";
    }
    
    public void setSpinneyHill(){
        setVideoType(Current_eNumVideoIDs.SpinMain);
        //setHome();
        //this.setVideoGrpDesc("Spinney Hill Documentary");
       // videoType = 208;
        //panelCenter = "./spinneyHill_Cover.xhtml";
        //this.forward();
    }
    //private void setVideoGrpDesc(String str){
    //    videoGroupDesc = str;
    //}
    //rrent_eNumVideoIDs
    // public void setVideoType(int x,String desc) {
    //     setVideoGrpDesc(desc);
     //    setVideoType(x);
     //}
     @Override //Cu
     public void setVideoType(eNumVideoIDs x) {
         refreshList = true;
         Current_eNumVideoIDs = x;
         this.home = false;
         url = "pgCal/BaldwinBlazerHome.xhtml";
         /*
         switch(x.getBrwDisplayType()){
             case 1:
                 //url = "VideoList_Grid.xhtml";
                 url = "pgCal/BaldwinBlazerHome.xhtml";
                 //forwardCntr("pgVideo/VideoList_Grid.xhtml");
                 break;
             default:
                 url = "pgCal/BaldwinBlazerHome.xhtml";
                 //url = "videoViewPgEditingBrw-Top.xhtml";
                 //forwardCntr("pgVideo/videoViewPgEditingBrw.xhtml");
                 break;
         }
         */
         setVideoType();
         
     }
    //public void setVideoType(int x) {
     //   if (x != 0){
     //       videoType = x;
     //       setVideoType();
     //   }
    //}
    public void setVideoType() {
        forwardCntr("pgVideo/videoViewPgEditingBrw.xhtml");
        //forwardCntr(url);
    }
    
    public int getVideoType() {
        return Current_eNumVideoIDs.getId();
    }
    @Override
    public void setVideoSysId(int videoSysId) {
        this.videoSysId = videoSysId;
        this.forwardCntr("pgVideo/videoBrwEvents.xhtml");
    }
    public int getVideoSysId() {
        return videoSysId;
    }
    
    // ==========================================
    public int getCntr(){
        return cntr++;
    }
    public int refreshSessionCnt(){
        return cntr;
    }
    
    public String getSpinnellHillImage(){
        return "/jvp/spinneyHill_Cover.jpg";
    }
    public String getJvpInclude(){
        if (!start){
            return "jvp_include.xhtml";
        } else {
            return "jvp_include_start.xhtml";
        }
        //
        
    }
   
    
    
    public void forward() {
       
        //prefix= "";
        start= false;
        (new factoryObj()).FacesContextForward("/MusicVideos", FacesContext.getCurrentInstance());
    }
    
     public void forwardCntr(String center){
         panelCenter = center;
         System.out.println(center+":"+url);
         forward();
     }
    
    
    
    public videoMgrObjMenu getVideoMgrObjMenu() {
        if (videoMgrObjMenu == null) videoMgrObjMenu = new videoMgrObjMenu(this);
        return videoMgrObjMenu;
    }

    /**
     * @return the panelWest
     */
    public String getPanelWest() {
        return panelWest;
    }

    /**
     * @return the panelCenter
     */
    public String getPanelCenter() {
        if (panelCenter == null){
            setHome();
        }
        return panelCenter;
    }

    /**
     * @return the login
     */
  
   

    /**
     * @return the newPath
     */
   
    public String getNewPath() {
       if (newPath == null)newPath = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
        //if (newPath == null)newPath = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        return "/"+newPath;
    }

    /**
     * @return the misc
     */
    public String getMisc() {
        return misc;
    }

    /**
     * @param misc the misc to set
     */
    public void setMisc(String misc) {
        this.misc = misc;
    }

    /**
     * @return the videoEditing
     */
    public boolean isVideoEditing() {
        return videoEditing;
    }

    /**
     * @param videoEditing the videoEditing to set
     */
    public void setVideoEditing(boolean videoEditing) {
        this.videoEditing = videoEditing;
        misc = null;
    }

    /**
     * @return the userBean
     */
    public userBean getUserBean() {
        if (userBean == null){
            userBean = new userBean();
        }
        return userBean;
    }

    /**
     * @return the selectId
     */
    //public int getSelectId() {
    //    return selectId;
    //}

    /**
     * @param selectId the selectId to set
     */
    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }

    /**
     * @return the refreshList
     */
    public boolean isRefreshList() {
        return refreshList;
    }

    /**
     * @param refreshList the refreshList to set
     */
    public void setRefreshList(boolean refreshList) {
        this.refreshList = refreshList;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the home
     */
    public boolean isHome() {
        return home;
    }
   
   
     


   

    
    

   
}

