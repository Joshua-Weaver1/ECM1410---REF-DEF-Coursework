package cycling;

import java.util.ArrayList;

/**
 * A class used to create an instance of a race.
 * 
 * @author Joshua Weaver
 * 
 */

public class Race {

    private int raceId;
    private String raceDescription;
    private String raceName;

    private ArrayList<Stage> raceStages = new ArrayList<Stage>();

    /**
     * This is the constructor for a race object.
     * 
     * @param raceId
     * @param name
     * @param description
     */
    public Race(int raceId, String name, String description) {
        this.raceId = raceId;
        this.raceName = name;
        this.raceDescription = description;
    }

    /**
     * Used to obtain the race id.
     * 
     * @return The race id.
     */
    public int getRaceId() {
        return this.raceId;
    }

    /**
     * Used to get the name of the race.
     * 
     * @return The string value of the race name.
     */
    public String getRaceName() {
        return this.raceName;
    }

    /**
     * Used to get the description of the race.
     * 
     * @return A string values of the race description.
     */
    public String getRaceDescription() {
        return this.raceDescription;
    }

    /**
     * Used to get the stages of the race.
     * 
     * @return An array of values of the stages of a race.
     */
    public Stage[] getRaceStages() {
        return this.raceStages.toArray(new Stage[0]);
    }

    /**
     * Used to add a stage to a race.
     * 
     * @param stage
     */
    public void addStageToRace(Stage stage) {
        this.raceStages.add(stage);
    }

    /**
     * Used to get all the stage ids from a race.
     * 
     * @return A list containing all the stage ids from a race.
     */
    public int[] getStageIdsFromRace() {
        int[] listOfStageIds = new int[this.raceStages.size()];
        for (int i = 0; i < listOfStageIds.length; i++) {
            listOfStageIds[i] = this.raceStages.get(i).getStageId();
        }
        return listOfStageIds;
    }

    /**
     * This method will remove the selected stage from the race.
     * 
     * @param stage
     */
    public void removeStageFromRace(Stage stage) {
        this.raceStages.remove(stage);
    }
    
}
