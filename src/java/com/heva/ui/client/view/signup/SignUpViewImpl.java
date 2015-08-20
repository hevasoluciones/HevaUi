/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heva.ui.client.view.signup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.heva.ui.client.place.AuthenticatorPlace;
import com.heva.ui.client.place.SignUpPlace;
import com.heva.ui.client.view.MessagesPublisherImpl;

/**
 *
 * @author David
 */
public class SignUpViewImpl extends MessagesPublisherImpl implements SignUpView {
    
    private static SignUpViewImplUiBinder uiBinder = GWT.create(SignUpViewImplUiBinder.class);

    Presenter listener;
    
    @UiField
    Button btnSignUp;
    
    @Override
    public void setPresenter(Presenter listener) {
        this.listener = listener;
    }
    
    interface SignUpViewImplUiBinder extends UiBinder<Widget, SignUpViewImpl> {
    }
    
    public SignUpViewImpl() {
        //initWidget(uiBinder.createAndBindUi(this));
        initWidget(this.asWidget());
    }
    
    @Override
    public Widget asWidget() {
        Widget widget = uiBinder.createAndBindUi(this);
        init();
        return widget;
    } 
    
    private void init() {
        btnSignUp.setStyleName("btn btn-block btn-success btn-rad btn-lg");
    }
    
    @UiHandler("btnSignUp")
    void onClickBtnSignUp(ClickEvent e) {        
        listener.goTo(new AuthenticatorPlace(""));
    }
}