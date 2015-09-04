/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heva.ui.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.heva.ui.shared.Beacon;
import com.heva.ui.shared.Campaign;
import com.heva.ui.shared.User;
import com.heva.ui.shared.Visits;
import java.util.List;

/**
 *
 * @author Yasmani
 */
public interface GWTServiceAsync {

    public void isValidUser(String user, String password, AsyncCallback<User> callback);
    
    public void getBeacons(AsyncCallback<List<Beacon>> callback);
    
    public void getVisitors(AsyncCallback<List<Visits>> callback);
    
    public void getCampaigns(AsyncCallback<List<Campaign>> callback);    
    
    public void signUp(String username, String email, String password, AsyncCallback<String> callback);
    
}
