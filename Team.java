package cycling;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * A class used to create an instance of a team.
 * 
 * @author Joshua Weaver
 * 
 */
public class Team implements Serializable{

    //List to contain all the riders in a particular team
    private ArrayList<Rider> teamRiders = new ArrayList<>();

    //Instance variables
    private int teamId;
    private String teamDescription;
    private String teamName;

    /**
     * THe purpose of this method is to get the id from the team.
     * 
     * @return The team id of the requested team.
     */
    public int getTeamId() {
        return this.teamId;
    }

    /**
     * This will return the name of the Team.
     * 
     * @return The name of the Team string.
     */
    public String getTeamName() {
        return this.teamName;
    }

    /**
     * This will return the description of the Team.
     * 
     * @return The string value of the description for this Team.
     */
    public String getTeamDescription() {
        return this.teamDescription;
    }

    /**
     * This will add a rider to the riders on this team.
     * 
     * @param rider
     */
    public void addRiderToTeam(Rider rider) {
        this.teamRiders.add(rider);
    }

    /**
     * Returns a newly created array containing the riders in this team.
     * 
     * @return A list of riders of type rider.
     */
    public Rider[] getTeamRiders() {
        return this.teamRiders.toArray(new Rider[0]);
    }

    /**
     * This method will remove the associated rider from this team.
     * 
     * @param rider
     */
    public void removeTeamRider(Rider rider) {
        this.teamRiders.remove(rider);
    }

    public int[] getIdsFromRiders() {
        int[] listOfRiderIds = new int[this.teamRiders.size()];
        for (int i = 0; i < listOfRiderIds.length; i++) {
            listOfRiderIds[i] = this.teamRiders.get(i).getRiderId();
        }
        return listOfRiderIds;
    }

    /**
     * This is the constructor for a team object.
     * 
     * @param teamId
     * @param name
     * @param description
     */
    public Team(int teamId, String name, String description) {
        this.teamId = teamId;
        this.teamDescription = description;
        this.teamName = name;
    }
}
