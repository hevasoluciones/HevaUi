/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heva.ui.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.event.shared.EventBus;
import com.heva.ui.client.ClientFactory;
import com.heva.ui.client.place.SignUpPlace;
import com.heva.ui.client.view.signup.SignUpView;

/**
 *
 * @author Yasmani
 */
public class SignUpActivity extends AbstractActivity implements
        SignUpView.Presenter {

    private ClientFactory clientFactory;
    
    public SignUpActivity(SignUpPlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        SignUpView signUpView = clientFactory.getSignUpView();
        clientFactory.getAuthenticatedUser().setAuthenticated(false);
        signUpView.setPresenter(this);
        containerWidget.setWidget(signUpView.asWidget());
        //init metods here     
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