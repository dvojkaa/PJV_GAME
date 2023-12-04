package cz.cvut.fel.pjv.framework;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This Class is for writing string line to the file
 */
public class WriterToFile {


    /**
     * This function writes Line to txt document
     * @param Line String text
     */
    public void WriteInventar(String Line) throws IOException {
        File inventoryFile = new File("data/config/Inventory.txt");
        FileWriter myWriter = new FileWriter(inventoryFile);
        System.out.println("Writing to inventory....");
        myWriter.write(Line);
        myWriter.close();
        System.out.println(inventoryFile.getAbsolutePath());
    }


}
