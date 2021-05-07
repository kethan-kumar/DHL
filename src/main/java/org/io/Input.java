/* @Author: Kethan Kumar */

package org.io;

import org.io.interfaces.IInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Input implements IInput {
    private Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String userInput(String str) {
        String strDuplicate = str;
        do {
            System.out.print("Enter the " + strDuplicate + ":");
            str = scanner.nextLine();
        } while (str.length() == 0);
        return str;
    }

    public String readJSON(String fileLocation) {
        String jsonString = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));
            String readLine;
            boolean emptyJSON = true;
            boolean status = true;
            while (status) {
                if ((readLine = bufferedReader.readLine()) == null) {
                    status = false;
                } else {
                    jsonString = jsonString.concat(readLine);
                    emptyJSON = false;
                }
            }
            if (emptyJSON)
                System.out.println("JSON file is empty!");
            else
                System.out.println("Successfully read the JSON File:" + fileLocation);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file at the specified location:" + fileLocation);
        } catch (IOException e) {
            System.out.println("IOException - Error while reading the file content!");
        }
        return jsonString;
    }
}
