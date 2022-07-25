package cycling;

/**
 * A class used to create an instance of a rider.
 * 
 * @author Joshua Weaver
 */
public class Rider {

    private String name;
    private int teamId;
    private int riderId;
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
     * Gets the rider Id.
     * 
     * @return An interger value of the rider Id.
     */
    public int getId() {
        return riderId;
    }

    /**
     * This will return the team id to which this rider belongs.
     * 
     * @return An integer value of the team id.
     */
    public int getTeamId() {
        return this.teamId;
    }

    /**
     * Used to obtain the year of birth of the rider.
     * 
     * @return The integer of the year of birth of the rider.
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * This will return the name of the rider.
     * 
     * @return The string of the name of the rider.
     */
    public String getName() {
        return name;
    }
}
