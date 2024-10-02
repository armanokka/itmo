package handlers;

import java.io.File;

/**
 * The FileHandler class provides methods for handling files.
 */
public class FileHandler {
    /**
     * Processes the file specified by the filePath.
     *
     * @param filePath the path to the file to process
     * @return the File object representing the processed file, or null if there are issues
     */
    public static File process(String filePath){
        File file = new File(filePath);
        if (!file.exists()){
//            System.out.println("File does not exist");
            return null;
        }

        if (!file.canRead()){
            System.out.println("Current permissions deny access to provided file");
            return null;
        }

        if(file.length() == 0){
            System.out.println("File is empty");
        }

        return file;
    }
}
