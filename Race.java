package cycling;

import java.util.ArrayList;

public class Race {

    private int raceId;
    private String raceName;
    private String raceDescription;

    private ArrayList<Stage> stages = new ArrayList<Stage>();

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
        return this.stages.toArray(new Stage[0]);
    }

    /**
     * Used to add a stage to a race.
     * 
     * @param stage
     */
    public void addStageToRace(Stage stage) {
        this.stages.add(stage);
    }
    
}
