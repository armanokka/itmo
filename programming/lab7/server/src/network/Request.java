package network;

import entities.Route;
import interfaces.Command;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 20L;

    String type; // request type
    Command command;
    String[] args;
    Route route;
    String scriptText;

    Integer keyToBeChecked;

    String login;
    String password;

    public Request(Command command, String[] args) {
        this.type = "run_command";
        this.command = command;
        this.args = args;
    }

    public Request(Command command, Route route) {
        this.type = "save_route";
        this.command = command;
        this.route = route;
    }

    public Request(Integer keyToBeChecked) {
        this.type = "key_availability_check";
        this.keyToBeChecked = keyToBeChecked;
    }

    public Request(String login, String password, boolean registerAccount) {
        this.type = registerAccount ? "register" : "login";
        this.login = login;
        this.password = password;
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
        switch (type) {
            case "login":
                return "Login request: login="+login+ " password=" + password;
            case "register":
                return "Register request: login="+login+ " password=" + password;
            default:
                return type;
        }
    }

}

