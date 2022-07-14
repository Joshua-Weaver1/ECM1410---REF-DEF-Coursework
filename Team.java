package cycling;

import java.util.ArrayList;

public class Team {

    private int teamId;
    private String teamName;
    private String teamDescription;

    private ArrayList<Rider> teamRiders = new ArrayList<>();

    /**
     * This is the constructor for a team object.
     * 
     * @param teamId
     * @param name
     * @param description
     */
    public Team(int teamId, String name, String description) {
        this.teamId = teamId;
        this.teamName = name;
        this.teamDescription = description;
    }

    /**
     * A method to get the id of the team.
     * 
     * @return The team id of the request team.
     */
    public int getId() {
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
    public void addRider(Rider rider) {
        this.teamRiders.add(rider);
    }

    /**
     * This method will remove the associated rider from this team.
     * 
     * @param rider
     */
    public void removeRider(Rider rider) {
        this.teamRiders.remove(rider);
    }

    /**
     * Returns a newly created array containing the riders in this team.
     * 
     * @return An arrray of riders.
     */
    public Rider[] getRiders() {
        return this.teamRiders.toArray(new Rider[0]);
    }

    public int[] getIdsFromRiders() {
        int[] listOfRiderIds = new int[this.teamRiders.size()];
        for (int i = 0; i < listOfRiderIds.length; i++) {
            listOfRiderIds[i] = this.teamRiders.get(i).getId();
        }
        return listOfRiderIds;
    }
}
