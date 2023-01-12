package carGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManagement {
    public FileManagement() {
        try {
            File myObj = new File("point.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                writeFile(String.valueOf(0));
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String readFile() {

        String data = null;
        try {
            File myObj = new File("point.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    public void writeFile(String score) {
        try {
            FileWriter myWriter = new FileWriter("point.txt");
            myWriter.write(score);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
