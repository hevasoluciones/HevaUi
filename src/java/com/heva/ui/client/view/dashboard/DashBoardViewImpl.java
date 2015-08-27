/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heva.ui.client.view.dashboard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.dom.client.TableSectionElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.heva.ui.client.place.BeaconsAnalyticsPlace;
import com.heva.ui.client.place.BeaconsConfigPlace;
import com.heva.ui.client.place.BeaconsPlace;
import com.heva.ui.client.place.CampaignPlace;
import com.heva.ui.client.place.DashBoardPlace;
import com.heva.ui.client.place.EditCampaignPlace;
import com.heva.ui.client.view.MessagesPublisherImpl;
import com.heva.ui.client.view.campaign.EditCampaignView;
import com.heva.ui.shared.Beacon;
import com.heva.ui.shared.Campaign;
import com.heva.ui.shared.User;
import com.heva.ui.shared.Visits;
import java.util.List;

/**
 *
 * @author Yasmani
 */
public class DashBoardViewImpl extends MessagesPublisherImpl implements DashBoardView {

    private static DashBoardViewImplUiBinder uiBinder = GWT.create(DashBoardViewImplUiBinder.class);
    @UiField
    SpanElement spanName;

    @Override
    public void setUserData(User authenticatedUser) {
        spanName.setInnerText(authenticatedUser.getName());
    }

    @Override
    public void setBeacons(List<Beacon> beaconList) {
        Element b = DOM.getElementById("beaconTotals");
        b.setInnerText(String.valueOf(beaconList.size()));
        
        Element bOn = DOM.getElementById("bOn");
        bOn.setInnerHTML("<i class=\"fa fa-angle-up\"></i>" + "99%");
        
        Element bOff = DOM.getElementById("bOff");
        bOff.setInnerHTML("<i class=\"fa fa-angle-right\"></i>" + "1%");
        
        Element bAlert = DOM.getElementById("bAlert");
        bAlert.setInnerHTML("<i class=\"fa fa-angle-right\"></i>" + "0");
    }

    @UiField
    TableElement campaignTable;
       
    @Override
    public void setCampaigns(List<Campaign> campaignList) {
        final Element c = DOM.getElementById("campaignTotals");
        c.setInnerText(String.valueOf(campaignList.size()));
               
        int i = 0;
        TableSectionElement tbody = campaignTable.getTBodies().getItem(0);
        for (final Campaign cam : campaignList) {
            TableRowElement tr = tbody.insertRow(i);
            tr.setAttribute("class", "items");
            
            TableCellElement tdCat = tr.insertCell(0);
            SpanElement spancat = Document.get().createSpanElement();
            spancat.setClassName("label label-success");//depende del tag pudiera ser "label label-info"
            spancat.setInnerText(cam.getTags().get(0));
            tdCat.appendChild(spancat);

            TableCellElement tcHD = tr.insertCell(1);            
            String inner = "<p>\n" +
                           "    <strong>" + 
                                    cam.getTitle() +
                           "    </strong>\n" +
                           "    <span>" +
                                    cam.getContent() +
                           "    </span>\n" +
                           "</p>";
            tcHD.setInnerHTML(inner);

            TableCellElement tcProg = tr.insertCell(2);
            tcProg.setClassName("color-success");
            tcProg.setInnerHTML("<div class=\"progress\">\n" +
"                                      <div class=\"progress-bar progress-bar-success\" style=\"width: 100%\">100%</div>\n" +
"                                </div>");
            
            TableCellElement tcOp = tr.insertCell(3);
            tcOp.setClassName("text-right");
            
            AnchorElement aEdit = Document.get().createAnchorElement();
            aEdit.setClassName("label label-default");
            aEdit.setInnerHTML("<i class=\"fa fa-pencil\"></i>");
            
            AnchorElement aDel = Document.get().createAnchorElement(); 
            aDel.setClassName("label label-danger");
            aDel.setInnerHTML("<i class=\"fa fa-times\"></i>");

            tcOp.appendChild(aEdit);
            tcOp.appendChild(aDel);
            
            Event.sinkEvents(aEdit, Event.ONCLICK);
            Event.setEventListener(aEdit, new EventListener() {
                @Override
                public void onBrowserEvent(Event event) {
                    System.out.println("ok");
                    if (Event.ONCLICK == event.getTypeInt()) {                        
                        listener.goTo(new  EditCampaignPlace("", cam));
                    }
                }
            });
            
            Event.sinkEvents(aDel, Event.ONCLICK);
            Event.setEventListener(aDel, new EventListener() {
                @Override
                public void onBrowserEvent(Event event) {
                    if (Event.ONCLICK == event.getTypeInt()) {
                        Window.alert("Borrar " + cam.getTitle());
                    }
                }
            });
            i++;            
        }
    }

    @Override
    public void setVisitors(List<Visits> visitList) {
        Element nv = DOM.getElementById("newVisitors");
        nv.setInnerText(String.valueOf(visitList.size()));
        Element nvg = DOM.getElementById("newvg");
        nvg.setInnerText(String.valueOf(visitList.size()));
        Element ovg = DOM.getElementById("oldvg");
        ovg.setInnerText(String.valueOf(visitList.size() + 10));
    }

    interface DashBoardViewImplUiBinder extends UiBinder<Widget, DashBoardViewImpl> {
    }
    Presenter listener;

    public DashBoardViewImpl() {
        //initWidget(uiBinder.createAndBindUi(this));
        initWidget(this.asWidget());
    }

    @Override
    public void setPresenter(Presenter listener) {
        this.listener = listener;
    }

    @Override
    public Widget asWidget() {
        Widget widget = uiBinder.createAndBindUi(this);
        init();
        return widget;
    }
    @UiField
    Button sidebar_collapse;
    @UiField
    Element sidebar_collapseIcon;

    private void init() {
        sidebar_collapse.setStyleName("btn btn-default");
        sidebar_collapseIcon.setAttribute("class", "fa fa-angle-left");
    }

    @UiHandler("sidebar_collapse")
    void onClickSidebar_collapse(ClickEvent e) {
        String clase = sidebar_collapseIcon.getAttribute("class");
        Element cl_wrapper = DOM.getElementById("cl-wrapper");
        if (clase.equals("fa fa-angle-left")) {
            sidebar_collapseIcon.setAttribute("class", "fa fa-angle-right");
            cl_wrapper.setAttribute("class", "sb-collapsed");
        } else {
            sidebar_collapseIcon.setAttribute("class", "fa fa-angle-left");
            cl_wrapper.setAttribute("class", "");
        }
    }

    @Override
    public void sinkEvents() {
        Element dashBoardView = DOM.getElementById("dashBoardView");
        Event.sinkEvents(dashBoardView, Event.ONCLICK);
        Event.setEventListener(dashBoardView, new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                if (Event.ONCLICK == event.getTypeInt()) {
                    listener.goTo(new DashBoardPlace(""));
                }
            }
        });

        Element beaconsView = DOM.getElementById("beaconsView");
        Event.sinkEvents(beaconsView, Event.ONCLICK);
        Event.setEventListener(beaconsView, new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                if (Event.ONCLICK == event.getTypeInt()) {
                    listener.goTo(new BeaconsPlace(""));
                }
            }
        });

        Element campaignView = DOM.getElementById("campaignView");
        Event.sinkEvents(campaignView, Event.ONCLICK);
        Event.setEventListener(campaignView, new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                if (Event.ONCLICK == event.getTypeInt()) {
                    listener.goTo(new CampaignPlace(""));
                }
            }
        });

        Element nvdetails = DOM.getElementById("nvdetails");
        Event.sinkEvents(nvdetails, Event.ONCLICK);
        Event.setEventListener(nvdetails, new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                if (Event.ONCLICK == event.getTypeInt()) {
                    //listener.goTo(new CampaignPlace(""));
                }
            }
        });

        Element cdetails = DOM.getElementById("cdetails");
        Event.sinkEvents(cdetails, Event.ONCLICK);
        Event.setEventListener(cdetails, new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                if (Event.ONCLICK == event.getTypeInt()) {
                    listener.goTo(new CampaignPlace(""));
                }
            }
        });

        Element bdetails = DOM.getElementById("bdetails");
        Event.sinkEvents(bdetails, Event.ONCLICK);
        Event.setEventListener(bdetails, new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                if (Event.ONCLICK == event.getTypeInt()) {
                    listener.goTo(new BeaconsPlace(""));
                }
            }
        });

    }
}