/* @Author: Kethan Kumar */

package org.statemachine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.leaguemodel.interfaces.ILeague;
import org.statemachine.abstractfactory.StateAbstractFactory;
import org.statemachine.interfaces.IImportLeagueData;
import org.statemachine.interfaces.ISerialization;

import java.io.FileWriter;
import java.io.IOException;

public class Serialization implements ISerialization {
    Logger logger;
    private StateAbstractFactory stateFactory;

    public Serialization() {
        logger = Logger.getLogger(Serialization.class.getName());
        stateFactory = StateAbstractFactory.instance();
    }

    public void serialize(ILeague leagueLOM) {
        try {
            FileWriter fileWriter = new FileWriter("SerializedLeague.json");
            new GsonBuilder().setPrettyPrinting().create().toJson(leagueLOM, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ILeague deserialize(String filename) {
        ILeague leagueLOM = null;
        IImportLeagueData leagueData = stateFactory.createImportLeagueData();
        try {
            leagueLOM = leagueData.loadLeagueMemory(filename);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return leagueLOM;
    }

    public String mySerialize(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public Object myDeserialize(String jsonString, Class classType) {
        Gson gson = new Gson();

        return gson.fromJson(jsonString, classType);
    }

    public void writeJsonToFile(String jsonString, String filename) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void date() throws IOException {
        FileWriter fileWriterdate = new FileWriter("leagueDate.json");

        new GsonBuilder().setPrettyPrinting().create().toJson("", fileWriterdate);
        fileWriterdate.close();
    }
}
