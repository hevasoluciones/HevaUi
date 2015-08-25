/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heva.ui.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.heva.ui.client.ClientFactory;
import com.heva.ui.client.place.AuthenticatorPlace;
import com.heva.ui.client.place.CampaignPlace;
import com.heva.ui.client.view.campaign.CampaignView;

/**
 *
 * @author David
 */
public class CampaignActivity extends AbstractActivity implements
        CampaignView.Presenter {

    private ClientFactory clientFactory;
    
    public CampaignActivity(CampaignPlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        if (!clientFactory.getAuthenticatedUser().isAuthenticated()) {
            goTo(new AuthenticatorPlace(""));
        }
        CampaignView campaignView = clientFactory.getCampaignView();
        campaignView.setPresenter(this);
        containerWidget.setWidget(campaignView.asWidget());
        campaignView.sinkEvents();
        campaignView.setUserData(clientFactory.getAuthenticatedUser());      
    }

    
    /**
     * Ask user before stopping this activity
     *
     *
     * @Override public String mayStop() { return null;//"Please hold on. This
     * activity is stopping."; }
     */
    /**
     * Navigate to a new Place in the browser
     */
    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
   
}
