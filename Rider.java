package cycling;

public class Rider {

    private String name;
    private int riderId;
    private int teamId;
    private int yearOfBirth;
    
    /**
     * This is the constructor for a rider object.
     * 
     * @param riderId
     * @param teamId
     * @param name
     * @param yearOfBirth
     */
    public Rider(int riderId, int teamId, String name, int yearOfBirth) {
        this.riderId = riderId;
        this.teamId = teamId;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * This will return the team id to which this rider belongs.
     * 
     * @return
     */
    public int getTeamId() {
        return this.teamId;
    }

    /**
     * Gets the rider Id.
     * 
     * @return The rider Id.
     */
    public int getId() {
        return riderId;
    }

    /**
     * This will return the name of the rider.
     * 
     * @return The string of the name of the rider.
     */
    public String getName() {
        return name;
    }

    /**
     * Used to obtain the year of birth of the rider.
     * 
     * @return The integer of the year of birth of the rider.
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }
}
