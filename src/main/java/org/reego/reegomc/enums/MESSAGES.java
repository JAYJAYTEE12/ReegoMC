package org.reego.reegomc.enums;

public enum MESSAGES {

    NOT_PLAYER("§cThat player doesn't exist!"),
    NO_PERMISSION("§cYou don't have enough permissions to execute that command!");

    public final String message;

    private MESSAGES(String message){
        this.message = message;
    }

}
