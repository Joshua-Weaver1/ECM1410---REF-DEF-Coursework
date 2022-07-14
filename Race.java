package cycling;

public class Race {

    private int raceId;
    private String raceName;
    private String raceDescription;

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

    public String getRaceDescription() {
        return this.raceDescription;
    }
    
}
