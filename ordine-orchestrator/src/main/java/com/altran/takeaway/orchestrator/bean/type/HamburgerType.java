package com.altran.takeaway.orchestrator.bean.type;


public enum HamburgerType {
    ANGUS("ANGUS"),SCOTTONA("SCOTTONA"),CHIANINA("CHIANINA"),KOBE("KOBE"),VITELLONE("VITELLONE");

    private String description;

    HamburgerType(String description){
        this.description=description;
    }

    public String getDescription() {
        return description;
    }
}
