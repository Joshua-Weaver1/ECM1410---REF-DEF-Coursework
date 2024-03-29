package cycling;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	//List to contain all the teams in a particular cycling portal
	private HashMap<Integer, Team> teams = new HashMap<Integer, Team>();
	//List to contain all the races in a particular cycling portal
	private HashMap<Integer, Race> races = new HashMap<Integer, Race>();

	//Instance variables
	private int teamIdCounter = 1;
	private int raceIdCounter = 1;
	private int stageIdCounter = 1;
	private int riderIdCounter = 1;
	private int segmentIdCounter = 1;

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

		int raceId = raceIdCounter++;
		Race race = new Race(raceId, name, description);
		races.put(raceId, race);
		return raceId;
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		Race race = getRaceFromId(raceId);

		double lengthOfRace = 0;
		for (Stage stage : race.getRaceStages()) {
			lengthOfRace = lengthOfRace + stage.getStageLength();
		}

		String raceDetails = "The race id is " + raceId + ". " + "The race name is " + 
		race.getRaceName() + ". " + "The race description is: " + race.getRaceDescription() +
		". " + "The number of stages in this race are " + race.getRaceStages().length + ". " +
		"The total length of the race is " + lengthOfRace + "km. ";

		return raceDetails;
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		Race race = getRaceFromId(raceId);
		for (Stage stage : race.getRaceStages()) {
			race.removeStageFromRace(stage);
		}
		races.remove(race.getRaceId());

	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		
		Race race = getRaceFromId(raceId);
		return race.getRaceStages().length;
	}

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
				
		Race race = getRaceFromId(raceId);
		checkName(stageName);
		
		if (length < 5) {
			throw new InvalidLengthException("The stage must be at least 5 kilometers.");
		}

		int stageId = stageIdCounter++;
		Stage stage = new Stage(raceId, stageId, stageName, description, length, startTime, type);
		race.addStageToRace(stage);

		return stageId;
	}

	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {

		Race race = getRaceFromId(raceId);
		return race.getStageIdsFromRace();
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		Stage stage = getStageFromId(stageId);
		return stage.getStageLength();
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		Stage stage = getStageFromId(stageId);
		int idOfRace = stage.getStageRaceId();
		races.get(idOfRace).removeStageFromRace(stage);

	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {

		checkSegmentDetails(stageId, location, type, length);
		Stage stage = getStageFromId(stageId);
		int segmentId = segmentIdCounter++;
		CategorizedClimb categorizedClimb = new CategorizedClimb(stageId, segmentId, length, location, averageGradient, type);
		stage.addSegmentToStage(categorizedClimb);

		return segmentId;
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {

		checkSegmentDetails(stageId, location, SegmentType.SPRINT, 0.0);
		Stage stage = getStageFromId(stageId);
		int segmentId = segmentIdCounter++;
		IntermediateSprint intermediateSprint = new IntermediateSprint(stageId, segmentId, location, SegmentType.SPRINT);
		stage.addSegmentToStage(intermediateSprint);

		return segmentId;
	}

	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		Segment segment = getSegmentFromId(segmentId);
		int stageId = segment.getStageId();
		Stage stage = getStageFromId(stageId);
		stage.isStageNotWaitingForResultsCheck();
		stage.removeStageSegment(segment);

	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		Stage stage = getStageFromId(stageId);
		stage.isStageNotWaitingForResultsCheck();
		stage.setStageState("waiting for results");

	}

	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		Stage stage = getStageFromId(stageId);
		return stage.getSegmentIdsFromStage();
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		checkName(name);

		int teamId = teamIdCounter++;
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
			teamIds[i] = team.getTeamId();
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
		
		int riderId = riderIdCounter++;
		Rider newRider = new Rider(riderId, teamID, name, yearOfBirth);
		team.addRiderToTeam(newRider);

		return riderId;
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		Rider riderToRemove = getRiderFromId(riderId);
		Team team = getTeamFromId(riderToRemove.getTeamId());
		team.removeTeamRider(riderToRemove);
	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		getRiderFromId(riderId);
		Stage stage = getStageFromId(stageId);
		registerRiderResultsCheck(stageId, riderId, checkpoints);
		stage.addStageResults(riderId, checkpoints);

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		getRiderFromId(riderId);
		Stage stage = getStageFromId(stageId);
		return stage.getStageResults(riderId);
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		getRiderFromId(riderId);
		Stage desiredStage = getStageFromId(stageId);
		LocalTime riderAdjustedElapsedTime = desiredStage.getAdjustedElapsedTimeOfRider(riderId);
		return riderAdjustedElapsedTime;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		getRiderFromId(riderId);
		Stage desiredStage = getStageFromId(stageId);
		desiredStage.deleteRiderResultsFromStage(riderId);
	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		Stage desiredStage = getStageFromId(stageId);
		return desiredStage.getRanksOfRiders();
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		Stage desiredStage = getStageFromId(stageId);
		return desiredStage.getRidersAdjustedTimesRanked();
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		Stage desiredStage = getStageFromId(stageId);
		int[] riderPoints = new int[getRidersRankInStage(stageId).length];
		int listSize = riderPoints.length;
		StageType stageType = desiredStage.getStageType();
		
		for (int i = 0; i < listSize; i++) {
			if (i < 15) {
				switch (stageType){
					case FLAT -> {
						int[] fPoints = new int[]{50, 30, 20, 18, 16, 14, 12, 10, 8, 7, 6, 5, 4, 3, 2};
						riderPoints[i] = fPoints[i];
						break;
					}
					case TT -> {
						int[] TTPoints = new int[]{20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
						riderPoints[i] = TTPoints[i];
					}
					case MEDIUM_MOUNTAIN -> {
						int[] mPoints = new int[]{30, 25, 22, 19, 17, 15, 13, 11, 9, 7, 6, 5, 4, 3, 2};
						riderPoints[i] = mPoints[i];
						break;
					}
					case HIGH_MOUNTAIN -> {
						int[] hPoints = new int[]{20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
						riderPoints[i] = hPoints[i];
						break;
					}
			    }
		    }
		    else{
			riderPoints[i] = 0;
		    }
		}
		return riderPoints;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		Stage desiredStage = getStageFromId(stageId);
		int[] riderPoints = new int[getRidersRankInStage(stageId).length];
		int listSize = riderPoints.length;
		Segment[] segments = desiredStage.getStageSegments();
		
		for (int j = 0; j < segments.length; j++) {
			SegmentType type = segments[j].getSegmentType();
			for (int i = 0; i < listSize; i++) {
				if (i < 1 && type == SegmentType.C4 || i < 1 && type == SegmentType.C3 || i < 4 && type == SegmentType.C2 ||
				i < 5 && type == SegmentType.C1 || i < 7 && type == SegmentType.HC || i < 14 && type == SegmentType.SPRINT) {
					switch (type){
						case C4 -> {
							int[] C4Points = new int[]{1};
							riderPoints[i] += C4Points[i];
							break;
						}
						case C3 -> {
							int[] C3Points = new int[]{2, 1};
							riderPoints[i] += C3Points[i];
							break;
						}
						case C2 -> {
							int[] C2Points = new int[]{5,3,2,1};
							riderPoints[i] += C2Points[i];
							break;
						}
						case C1 -> {
							int[] C1Points = new int[]{10,8,6,4,2,1};
								riderPoints[i] += C1Points[i];
							break;
						}
						case HC -> {
							int[] HCPoints = new int[]{20,15,12,10,8,6,4,2};
							riderPoints[i] += HCPoints[i];
							break;
						}
						case SPRINT -> {
							int[] SprintPoints = new int[]{20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
							riderPoints[i] += SprintPoints[i];
							break;
						}
					}
				}
				else{
				riderPoints[i] = 0;
				}
		    }
		}
		return riderPoints;
	}

	@Override
	public void eraseCyclingPortal() {
		//Clears objects
		this.teams.clear();
		this.races.clear();

		//Clears internal counters
		this.teamIdCounter = 0;
	    this.raceIdCounter = 0;
	    this.stageIdCounter = 0;
	    this.riderIdCounter = 0;
	    this.segmentIdCounter = 0;

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		//Creation of a new file output stream
		FileOutputStream foStream = new FileOutputStream(filename);
		//Creation of a new buffered output stream
		BufferedOutputStream boStream = new BufferedOutputStream(foStream);
		//Creation of a new object output stream
		ObjectOutputStream ooStream = new ObjectOutputStream(boStream);
		//This will write the instance of the cycling portal object to the object output stream
		ooStream.writeObject(this);
		//This will close the object output stream
		ooStream.close();

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		//Initialise new file input stream
		FileInputStream fIStream = new FileInputStream(filename);
		//Initialise new buffered input stream
		BufferedInputStream bIStream = new BufferedInputStream(fIStream);
		//Initialise new object input stream
		ObjectInputStream oIStream = new ObjectInputStream(bIStream);

		CyclingPortal loadedCyclingPortal = (CyclingPortal)oIStream.readObject();

		//Initialise objects
		this.teams = loadedCyclingPortal.teams;
		this.races = loadedCyclingPortal.races;

		//Initialise internal counters
		this.teamIdCounter = loadedCyclingPortal.teamIdCounter;
	    this.raceIdCounter = loadedCyclingPortal.raceIdCounter;
	    this.stageIdCounter = loadedCyclingPortal.stageIdCounter;
	    this.riderIdCounter = loadedCyclingPortal.riderIdCounter;
	    this.segmentIdCounter = loadedCyclingPortal.segmentIdCounter;

		//This will close the object input stream
		oIStream.close();

	}

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
			for (Stage stage : race.getRaceStages()) {
				if (stage.getStageName().equals(name)) {
					nameTaken = true;
				}
			}
		}

		
		if (name == null || name.length() == 0) {
			throw new InvalidNameException("Can not have a empty name.");
		}
		if (name.length() > 30) {
			throw new InvalidNameException("Cannot be more than 30 Characters. The name must be shortened.");
		}
		if (nameTaken) {
			throw new IllegalNameException("The name " + name + " is already taken.");
		}
		if (name.contains(" ")) {
			throw new InvalidNameException("The name can not have spaces.");
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
			throw new IDNotRecognisedException("Team ID " + id + " does not match with a team.");
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
	 * This will retrive a rider based off their id.
	 * 
	 * @param id
	 * @return The rider based off their id.
	 * @throws IDNotRecognisedException
	 */
	public Rider getRiderFromId(int id) throws IDNotRecognisedException {
		for (Team team : teams.values()) {
			for (Rider rider : team.getTeamRiders()) {
				if (rider.getRiderId() == id) {
					return rider;
				}
			}
		}
		throw new IDNotRecognisedException("The Rider ID " + id + " does not match with a rider.");
	}

	/**
	 * This will retrive a race based off their id.
	 * 
	 * @param id
	 * @return The race based off their id.
	 * @throws IDNotRecognisedException
	 */
	public Race getRaceFromId(int id) throws IDNotRecognisedException {
		if (!races.containsKey(id)) {
			throw new IDNotRecognisedException("The Race ID " + id + " does not match with a race.");
		}
		return races.get(id);
	}

	/**
	 * This will retrive a stage based off their id.
	 * 
	 * @param id
	 * @return The stage based off their id.
	 * @throws IDNotRecognisedException
	 */
	public Stage getStageFromId(int id) throws IDNotRecognisedException {
		for (Race race : races.values()) {
			for (Stage stage : race.getRaceStages()) {
				if (stage.getStageId() == id) {
					return stage;
				}
			}
		}
		throw new IDNotRecognisedException("The Stage ID " + id + " does not match with a stage.");
	}

	/**
	 * The reason for this method is to examine if the segment to be created follows
	 * all the necessary requirements. It will see if the stage's boundaries are not
	 * gone over by the segment, and that the stage it will be joining is not waiting for
	 * results or a TT.
	 * 
	 * @param stageId
	 * @param location
	 * @param type
	 * @param length
	 * @throws InvalidLocationException
	 * @throws InvalidStageTypeException
	 * @throws IDNotRecognisedException
	 * @throws InvalidStageStateException
	 */
	private void checkSegmentDetails(int stageId, Double location, SegmentType type, Double length) throws InvalidLocationException, InvalidStageTypeException, IDNotRecognisedException, InvalidStageStateException {
		Stage stage = getStageFromId(stageId);

		if (location > stage.getStageLength() || location-length < 0) {
			throw new InvalidLocationException("The location is out of bounds of the stage length.");
		}
		
		if (stage.getStageType() == StageType.TT) {
			throw new InvalidStageTypeException("A segment can not be added to a Time Trial");
		}

		stage.isStageNotWaitingForResultsCheck();
	}

	/**
	 * The purpose of this method is to
	 * retrive a segemnt based off their id.
	 * 
	 * @param segmentId
	 * @return A segment of type Segment.
	 * @throws IDNotRecognisedException
	 */
	private Segment getSegmentFromId(int segmentId) throws IDNotRecognisedException {
		for (Race race : races.values()) {
			for (Stage stage : race.getRaceStages()) {
				for (Segment segment : stage.getStageSegments()) {
					if (segment.getSegmentId() == segmentId) {
						return segment;
					}
				}
			}
		}
		throw new IDNotRecognisedException("The Segment ID " + segmentId + " was not found.");
	}

	/**
	 * The purpose of this method is to check that when registering rider results,
	 * that they follow the correct parameters.
	 * 
	 * @param stageId
	 * @param riderId
	 * @param checkpoints
	 * @throws IDNotRecognisedException
	 * @throws DuplicatedResultException
	 * @throws InvalidStageStateException
	 * @throws InvalidCheckpointsException
	 */
	private void registerRiderResultsCheck(int stageId, int riderId, LocalTime[] checkpoints) throws IDNotRecognisedException, DuplicatedResultException, InvalidStageStateException, InvalidCheckpointsException {
		Stage stage = getStageFromId(stageId);
		if (stage.getStageSegments().length+2 != checkpoints.length) {
			throw new InvalidCheckpointsException("The amount of checkpoints are incorrect.");
		}

		if (stage.getStageResults(riderId).length != 0) {
			throw new DuplicatedResultException("The rider has already a result for the stage.");
		}
		stage.isStageWaitingForResultsCheck();
	}

}
