package cycling;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * A class used to create an instance of a race.
 * These races will contain segments.
 * 
 * @author Joshua Weaver
 * 
 */

public class Race implements Serializable{

    //List to contain all the stages in a particular race
    private ArrayList<Stage> raceStages = new ArrayList<Stage>();

    //Instance variables
    private String raceDescription;
    private int raceId;
    private String raceName;

    /**
     * Used to obtain the race id.
     * 
     * @return The int value of the race id.
     */
    public int getRaceId() {
        return this.raceId;
    }

    /**
     * Used to obtain the race name.
     * 
     * @return The string value of the race name.
     */
    public String getRaceName() {
        return this.raceName;
    }

    /**
     * Used to get the description of the race.
     * This may be the same as other races.
     * 
     * @return A string value of the race description.
     */
    public String getRaceDescription() {
        return this.raceDescription;
    }

    /**
     * Used to get all the stages of the race from
     * the arraylist called raceStages.
     * 
     * @return An array of values of the stages of a race.
     */
    public Stage[] getRaceStages() {
        return this.raceStages.toArray(new Stage[0]);
    }

    /**
     * Used to add a stage to a race through
     * the raceStages array.
     * 
     * @param stage
     */
    public void addStageToRace(Stage stage) {
        this.raceStages.add(stage);
    }

    /**
     * Used to get all the stage ids from a race from
     * the raceStages arraylist.
     * 
     * @return An array containing all the stage ids from a race.
     */
    public int[] getStageIdsFromRace() {
        int[] listOfStageIds = new int[this.raceStages.size()];
        for (int i = 0; i < listOfStageIds.length; i++) {
            listOfStageIds[i] = this.raceStages.get(i).getStageId();
        }
        return listOfStageIds;
    }

    /**
     * This method will remove the selected stage from this race
     * by deleting it from raceStages.
     * 
     * @param stage
     */
    public void removeStageFromRace(Stage stage) {
        this.raceStages.remove(stage);
    }

    /**
     * This is the constructor for a race object.
     * 
     * @param raceId
     * @param name
     * @param description
     */
    public Race(int raceId, String name, String description) {
        //Assign parameters to variables
        this.raceName = name;
        this.raceId = raceId;
        this.raceDescription = description;
    }
}
