package network;

import entities.Route;
import interfaces.Command;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 20L;
    Command command;
    String[] args;
    Route route;
    String scriptText;

    Integer keyToBeChecked;

    public Request(Command command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public Request(Command command, Route route) {
        this.command = command;
        this.route = route;
    }

    public Request(Command command, String scriptText) {
        this.command = command;
        this.scriptText = scriptText;
    }

    public Request(Integer keyToBeChecked) {
        this.keyToBeChecked = keyToBeChecked;
    }

    public Command getCommand(){
        return command;
    }
    public String[] getArgs(){
        return args;
    }
    public Route getRoute() { return route;}

    public String getScriptText() {return scriptText;}

    public String toString() {
        return "Request" +
                "\ncommand: " + command.getName() +
                "\narguments: " + String.join(" ", args) +
                "\nroute: " + route  +
                "\nscriptText: " + scriptText +
                "\nkeyToBeChecked: " + keyToBeChecked;
    }

}

