package com.hackathon.triage.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hackathon.triage.util.StringConstants.AGVSCHEDULER;
import static com.hackathon.triage.util.StringConstants.ASCSCHEDULER;
import static com.hackathon.triage.util.StringConstants.AUTOSHUTTLE;
import static com.hackathon.triage.util.StringConstants.AUTOSTOW;
import static com.hackathon.triage.util.StringConstants.BILLING;
import static com.hackathon.triage.util.StringConstants.CARGO;
import static com.hackathon.triage.util.StringConstants.CLUSTERINGMESSAGING;
import static com.hackathon.triage.util.StringConstants.DATAMODEL;
import static com.hackathon.triage.util.StringConstants.DISPACHER;
import static com.hackathon.triage.util.StringConstants.ECI;
import static com.hackathon.triage.util.StringConstants.EDI;
import static com.hackathon.triage.util.StringConstants.EXPERTDECKING;
import static com.hackathon.triage.util.StringConstants.FRAMEWORK;
import static com.hackathon.triage.util.StringConstants.GATE;
import static com.hackathon.triage.util.StringConstants.GENERAL;
import static com.hackathon.triage.util.StringConstants.PRIMEROUTE;
import static com.hackathon.triage.util.StringConstants.RAILTZDECKER;
import static com.hackathon.triage.util.StringConstants.REEFER;
import static com.hackathon.triage.util.StringConstants.SEMIAUTOMATED;
import static com.hackathon.triage.util.StringConstants.SIDELOADINGCRANE;
import static com.hackathon.triage.util.StringConstants.TECH;
import static com.hackathon.triage.util.StringConstants.VESSEL;

public class Topics {
    static Map<ComponentEnum, List<String>> mapOfCategoryAndListOftopics = null;

    public Map<ComponentEnum, List<String>> initialise() {
        mapOfCategoryAndListOftopics = new HashMap<>();
        for (ComponentEnum componentEnum : ComponentEnum.values()) {
            List<String> topics = getCorrespondingTopics(componentEnum);
            mapOfCategoryAndListOftopics.put(componentEnum, topics);
        }
        return mapOfCategoryAndListOftopics;
    }

    public List<String> getCorrespondingTopics(ComponentEnum inComponent) {
        switch (inComponent) {
            case A4:
                return getA4TopicList();
            case N4_general:
                return getN4GeneralList();
            case AGV:
                return getAgvList();
            case ASC:
                return getAscList();
            case ECI:
                return getEciList();
            case EDI:
                return getEdiList();
            case XPS:
                return getXpsList();
            case ECN4:
                return getEcn4List();
            case Vessel:
               return getVesselList();
            case Rail:
                return getRailList();
            case N4_mobile:
                return getN4MobileList();
            case N4_framework:
                return getN4FrameWorkList();
            case N4_clustering:
                return getN4ClusteringList();
            case Expert_decking:
                return getExpertDeckingList();
        }
        return Collections.emptyList();
    }

    private List<String> getExpertDeckingList() {
        return Arrays.asList();
    }

    private List<String> getN4ClusteringList() {
        return Arrays.asList();
    }

    private List<String> getN4FrameWorkList() {
        return Arrays.asList();
    }

    private List<String> getN4MobileList() {
        return Arrays.asList();
    }

    private List<String> getRailList() {
        return Arrays.asList();
    }

    private List<String> getVesselList() {
        return Arrays.asList();
    }

    private List<String> getEcn4List() {
        return Arrays.asList();
    }

    private List<String> getXpsList() {
        return Arrays.asList(GENERAL, TECH, EXPERTDECKING, PRIMEROUTE, DATAMODEL, SEMIAUTOMATED, AUTOSTOW);
    }

    private List<String> getEdiList() {
        return Arrays.asList();
    }

    private List<String> getEciList() {
        return Arrays.asList();
    }

    private List<String> getAscList() {
        return Arrays.asList();
    }

    private List<String> getAgvList() {
        List<String> topicAgvList = Arrays.asList();
        return topicAgvList;
    }

    private List<String> getN4GeneralList() {
        List<String> topicsN4GeneralList= Arrays.asList(FRAMEWORK, GENERAL, PRIMEROUTE, GATE, CLUSTERINGMESSAGING, BILLING, EDI, VESSEL, CARGO );
        return topicsN4GeneralList;
    }


    private List<String> getA4TopicList() {
        List<String> topicsA4 = Arrays.asList(AGVSCHEDULER, ASCSCHEDULER, DISPACHER, GENERAL,
                SIDELOADINGCRANE, AUTOSHUTTLE, ECI, DISPACHER, RAILTZDECKER, REEFER);
        return topicsA4;
    }


}
