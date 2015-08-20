/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heva.ui.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.heva.ui.client.GWTService;
import com.heva.ui.shared.Beacon;
import com.heva.ui.shared.Campaign;
import com.heva.ui.shared.User;
import com.heva.ui.shared.Visits;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yasmani
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {

    @Override
    public User isValidUser(String user, String password) {
        User myUser = new User();

        try {
            //TODO implement this method
            DBConnection dbc = new DBConnection();
            myUser = dbc.getUser(user, password);
            dbc.closeConnection();
        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

        return myUser;
    }

    @Override
    public List<Beacon> getBeacons() {
        List<Beacon> blist = new ArrayList<Beacon>();
        Beacon b = new Beacon();
        b.setName("Beacon 1");
        b.getTags().add("bags");
        b.getTags().add("short");
        b.getTags().add("shoes");
        b.setProximityUUID("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
        b.setBeaconColor("Blueberry Pie");
        b.setMacAddress("C5:3B:38:76:7F:23");
        b.setMajor(32547);
        b.setMinor(14454);
        b.setSecureUUID(null);

        blist.add(b);

        b = new Beacon();
        b.setName("Beacon 2");
        b.getTags().add("bags");
        b.getTags().add("short");
        b.getTags().add("shoes");
        b.setProximityUUID("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
        b.setBeaconColor("Blueberry Pie");
        b.setMacAddress("C5:3B:38:76:7F:23");
        b.setMajor(32547);
        b.setMinor(14454);
        b.setSecureUUID(null);

        blist.add(b);

        b = new Beacon();
        b.setName("Beacon 3");
        b.getTags().add("bags");
        b.getTags().add("short");
        b.getTags().add("shoes");
        b.setProximityUUID("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
        b.setBeaconColor("Blueberry Pie");
        b.setMacAddress("C5:3B:38:76:7F:23");
        b.setMajor(32547);
        b.setMinor(14454);
        b.setSecureUUID(null);

        blist.add(b);

        b = new Beacon();
        b.setName("Beacon 4");
        b.getTags().add("bags");
        b.getTags().add("short");
        b.getTags().add("shoes");
        b.setProximityUUID("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
        b.setBeaconColor("Blueberry Pie");
        b.setMacAddress("C5:3B:38:76:7F:23");
        b.setMajor(32547);
        b.setMinor(14454);
        b.setSecureUUID(null);

        blist.add(b);

        b = new Beacon();
        b.setName("Beacon 5");
        b.getTags().add("bags");
        b.getTags().add("short");
        b.getTags().add("shoes");
        b.setProximityUUID("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
        b.setBeaconColor("Blueberry Pie");
        b.setMacAddress("C5:3B:38:76:7F:23");
        b.setMajor(32547);
        b.setMinor(14454);
        b.setSecureUUID(null);

        blist.add(b);
        return blist;
    }

    @Override
    public List<Visits> getVisitors() {
        List<Visits> vList = new ArrayList<Visits>();
        Visits v = new Visits();
        v.setTime("78");
        v.setApp_in_background_visits(5);
        v.setApp_in_foreground_visits(3);
        v.setTotal_visits(8);
        vList.add(v);

        v = new Visits();
        v.setTime("45");
        v.setApp_in_background_visits(3);
        v.setApp_in_foreground_visits(23);
        v.setTotal_visits(38);
        vList.add(v);

        v = new Visits();
        v.setTime("100");
        v.setApp_in_background_visits(51);
        v.setApp_in_foreground_visits(33);
        v.setTotal_visits(85);
        vList.add(v);

        return vList;
    }

    @Override
    public List<Campaign> getCampaigns() {
        List<Campaign> clist = new ArrayList<Campaign>();
        Campaign c = new Campaign();
        c.setId(67);
        c.setTitle("some title");
        clist.add(c);
        c = new Campaign();
        c.setId(66);
        c.setTitle("some title1");
        clist.add(c);
        return clist;
    }
}
