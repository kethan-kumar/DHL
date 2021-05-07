/* @Author: Kethan Kumar */

package org.statemachine;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.leaguemodel.GeneralManager;
import org.leaguemodel.HeadCoach;
import org.leaguemodel.ValidationsInLeague;
import org.leaguemodel.interfaces.IGeneralManager;
import org.leaguemodel.interfaces.IHeadCoach;
import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IValidationsInLeague;
import org.statemachine.interfaces.IValidate;

import java.util.List;

public class Validation implements IValidate {
    public boolean validateLOM(ILeague leagueLOM) {
        boolean validateStatus = false;
        IValidationsInLeague validateLeagueData = new ValidationsInLeague();
        if (validateLeagueData.validateConferenceList(leagueLOM)) {
            System.out.println("Duplicate conferences in the import");
            validateStatus = true;
        } else if (validateLeagueData.validateDivisionList(leagueLOM)) {
            System.out.println("Duplicate divisions in the conference");
            validateStatus = true;
        } else if (validateLeagueData.validateTeamList(leagueLOM)) {
            System.out.println("Duplicate teams in the league");
            validateStatus = true;
        } else if (validateLeagueData.validateCaptain(leagueLOM)) {
            System.out.println("Captain status is ambigious");
            validateStatus = true;
        }
        return validateStatus;
    }

    public IGeneralManager validateGeneralManager(ILeague leagueLOM, String generalManagerNumber) {
        List<IGeneralManager> managersList = leagueLOM.getGeneralManagers();
        int managerNumber;
        managerNumber = Integer.parseInt(generalManagerNumber);
        if (managerNumber > managersList.size() || managerNumber <= 0) {
            return null;
        } else {
            IGeneralManager generalManager = new GeneralManager();
            generalManager.setName(managersList.get(managerNumber - 1).getName());
            generalManager.setPersonality(managersList.get(managerNumber - 1).getPersonality());
            managersList.remove(managerNumber - 1);
            return generalManager;
        }
    }

    public IHeadCoach validateHeadCoach(ILeague leagueLOM, String headCoach) {
        List<IHeadCoach> coachesList = leagueLOM.getCoaches();
        int headCoachNumber;
        headCoachNumber = Integer.parseInt(headCoach);
        if (headCoachNumber > coachesList.size() || headCoachNumber <= 0) {
            return null;
        } else {
            IHeadCoach coach = new HeadCoach();
            coach.setName(coachesList.get(headCoachNumber - 1).getName());
            coach.setSkating(coachesList.get(headCoachNumber - 1).getSkating());
            coach.setShooting(coachesList.get(headCoachNumber - 1).getShooting());
            coach.setChecking(coachesList.get(headCoachNumber - 1).getChecking());
            coach.setSaving(coachesList.get(headCoachNumber - 1).getSaving());
            coachesList.remove(headCoachNumber - 1);
            return coach;
        }
    }

    public boolean validateString(String userInput) {
        String regex = "[0-9]+";
        if (userInput.matches(regex)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean schemaValidation(String leagueData, String jsonSchema) {
        boolean status = false;
        JSONObject jsonObject = new JSONObject(jsonSchema);
        try {
            Schema schema = SchemaLoader.load(jsonObject);
            schema.validate(new JSONObject(leagueData));
            status = true;
        } catch (ValidationException e) {
            System.out.println("Below values are incorrect, kindly provide proper values:");
            for (String schemaViolation : e.getAllMessages()) {
                System.out.println(schemaViolation);
            }
        } catch (JSONException e) {
            System.out.println("JSONException occurred!" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception occurred!" + e.getMessage());
        }
        return status;
    }
}
