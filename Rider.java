package cycling;

import java.io.Serializable;

/**
 * A class used to create an instance of a rider.
 * These riders will belong to an instance of a team class.
 * 
 * @author Joshua Weaver
 * 
 */

public class Rider implements Serializable{

    //Instance variables
    private String riderName;
    private int teamId;
    private int riderId;
    private int riderYearOfBirth;

    /**
     * This will return the rider id of the rider.
     * 
     * @return An integer value of the rider id.
     */
    public int getRiderId() {
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
        return this.riderYearOfBirth;
    }

    /**
     * This will return the name of the rider.
     * 
     * @return The string value of the name of the rider.
     */
    public String getRiderName() {
        return this.riderName;
    }

    /**
     * This is the constructor for a rider object.
     * 
     * @param riderId
     * @param teamId
     * @param name
     * @param yearOfBirth
     */
    public Rider(int riderId, int teamId, String name, int yearOfBirth) {
        this.riderName = name;
        this.riderYearOfBirth = yearOfBirth;
        this.teamId = teamId;
        this.riderId = riderId;
    }
}
