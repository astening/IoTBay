/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aneir
 */

package uts.isd.model;

import java.io.Serializable;
import java.util.Date;

public class AccessLog implements Serializable {
    private int logID;
    private int userID;
    private Date loginDateTime;
    private Date logoutDateTime;
    private String logDetails;

    public AccessLog() {
    }

    public AccessLog(int logID, int userID, Date loginDateTime, Date logoutDateTime, String logDetails) {
        this.logID = logID;
        this.userID = userID;
        this.loginDateTime = loginDateTime;
        this.logoutDateTime = logoutDateTime;
        this.logDetails = logDetails;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(Date loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    public Date getLogoutDateTime() {
        return logoutDateTime;
    }

    public void setLogoutDateTime(Date logoutDateTime) {
        this.logoutDateTime = logoutDateTime;
    }

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails;
    }
}
