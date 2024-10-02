import handlers.*;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    public static void main(String [] args) throws Exception {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        CollectionHandler collectionHandler = new CollectionHandler();

        while (true) {
            System.out.print("shell>>");
            var line = reader.readLine();

            CommandHandler.process(line, collectionHandler, reader);
        }
    }
}