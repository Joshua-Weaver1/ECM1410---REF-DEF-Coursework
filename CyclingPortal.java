package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

/**
 * CyclingPortal is a compiling implementor
 * of the MiniCyclingPortalInterface interface.
 * 
 * @author Joshua Weaver
 * @version 1.0
 *
 */
public class CyclingPortal implements MiniCyclingPortalInterface {

	private int idCounter = 1;
	private HashMap<Integer, Team> teams = new HashMap<Integer, Team>();
	private HashMap<Integer, Race> races = new HashMap<Integer, Race>();
	

	/**
	 * This will ensure that the name entered follows all the correct
	 * naming conventions.
	 * 
	 * @param name
	 * @throws IllegalNameException
	 * @throws InvalidNameException
	 */
	public void checkName(String name) throws IllegalNameException, InvalidNameException {
		boolean nameTaken = false;

		for (Team team : teams.values()) {
			if (team.getTeamName().equals(name)) {
				nameTaken = true;
			}
		}

		for (Race race : races.values()) {
			if (race.getRaceName().equals(name)) {
				nameTaken = true;
			}
		}

		
		if (name.length() > 30) {
			throw new InvalidNameException("The name was too long. Cannot be more than 30 Characters.");
		}
		if (name == null || name.length() == 0) {
			throw new InvalidNameException("The name was empty.");
		}
		if (nameTaken) {
			String response = "The name " + name + " is already taken.";
			throw new IllegalNameException(response);
		}
		if (name.contains(" ")) {
			throw new InvalidNameException("The name contains spaces.");
		}
	}

	/**
	 * This will retrieve a team based off its ID.
	 * 
	 * @param id
	 * @return The team with the corresponding id.
	 * @throws IDNotRecognisedException
	 */
	public Team getTeamFromId(int id) throws IDNotRecognisedException {
		if (!teams.containsKey(id)) {
			String response = "Team ID " + id + " does not exist.";
			throw new IDNotRecognisedException(response);
		}
		return teams.get(id);
	}

	/**
	 * This method will check if the input Rider details follow the correct 
	 * naming conventions.
	 * 
	 * @param name
	 * @param yearOfBirth
	 */
	public void checkRiderDetails(String name, int yearOfBirth) {
		if (yearOfBirth < 1900) {
			throw new IllegalArgumentException("The year of birth must be 1900 or higher.");
		}
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("The rider's name was left blank.");
		}
	}


	/**
	 * This will retrive a rider base off their id.
	 * 
	 * @param id
	 * @return The rider based off their id.
	 * @throws IDNotRecognisedException
	 */
	public Rider getRiderFromId(int id) throws IDNotRecognisedException {
		for (Team team : teams.values()) {
			for (Rider rider : team.getRiders()) {
				if (rider.getId() == id) {
					return rider;
				}
			}
		}
		String response = "The Rider ID " + id + " does not exist.";
		throw new IDNotRecognisedException(response);
	}

	@Override
	public int[] getRaceIds() {
		int[] listOfRaceIds = new int[races.size()];
		int count = 0;
		for (int id : races.keySet()) {
			listOfRaceIds[count] = id;
			count++;
		}
		return listOfRaceIds;
	}

	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		checkName(name);

		int raceId = idCounter++;
		Race race = new Race(raceId, name, description);
		races.put(raceId, race);
		return raceId;
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		checkName(name);

		int teamId = idCounter++;
		Team team = new Team(teamId, name, description);
		teams.put(teamId, team);
		return teamId;
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		getTeamFromId(teamId);
		teams.remove(teamId);
	}

	@Override
	public int[] getTeams() {
		int[] teamIds = new int[teams.size()];
		int i = 0;
		for (Team team : teams.values()) {
			teamIds[i] = team.getId();
			i++;
		}
		return teamIds;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		Team team = getTeamFromId(teamId);
		return team.getIdsFromRiders();
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		checkRiderDetails(name, yearOfBirth);

		Team team = getTeamFromId(teamID);
		
		int riderId = idCounter++;
		Rider newRider = new Rider(riderId, teamID, name, yearOfBirth);
		team.addRider(newRider);

		return riderId;
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		Rider riderToRemove = getRiderFromId(riderId);
		Team team = getTeamFromId(riderToRemove.getTeamId());
		team.removeRider(riderToRemove);
	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eraseCyclingPortal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

}
