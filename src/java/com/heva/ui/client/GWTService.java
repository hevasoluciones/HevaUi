/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heva.ui.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.heva.ui.shared.Beacon;
import com.heva.ui.shared.Campaign;
import com.heva.ui.shared.User;
import com.heva.ui.shared.Visits;
import java.util.List;

/**
 *
 * @author Yasmani
 */
@RemoteServiceRelativePath("gwtservice")
public interface GWTService extends RemoteService {

    public User isValidUser(String user, String password);
    
    public List<Beacon> getBeacons();
    
    public List<Visits> getVisitors();
    
    public List<Campaign> getCampaigns();
    
}
