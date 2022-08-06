package cycling;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * A class used to create an instance of a stage.
 * 
 * @author Joshua Weaver
 */

public class Stage implements Serializable{

  //List to contain all the segments in a particular stage
  private ArrayList<Segment> stageSegments = new ArrayList<Segment>();
  //List to contain all the stage results
  private LinkedHashMap<Integer, ArrayList<LocalTime>> stageResults = new LinkedHashMap<Integer, ArrayList<LocalTime>>();

  //Instance variables
  private String stageName;
  private double stageLength;
  private String stageDescription;
  private int stageId;
  private int raceId;
  private LocalDateTime stageStartTime;
  private String stageState;
  private StageType stageType;

    /**
     * Used to get the name of the stage.
     * 
     * @return A string value of the name of this stage.
     */
    public String getStageName() {
        return this.stageName;
    }

    /**
     * Used to get the description of the stage.
     * 
     * @return A string value of the description of this stage.
     */
    public String getStageDescription() {
        return this.stageDescription;
    }

    /**
     * Used to get the length of the stage.
     * 
     * @return A double value of the length of this stage.
     */
    public double getStageLength() {
        return this.stageLength;
    }

    /**
     * Used to get the race id of the stage.
     * 
     * @return An int value of the race id of this stage.
     */
    public int getStageRaceId() {
        return this.raceId;
    }

    /**
     * Used to get the id of the stage.
     * 
     * @return An int value of the id of this stage.
     */
    public int getStageId() {
        return this.stageId;
    }

    /**
     * Used to get the start time of the stage.
     * 
     * @return A LocalDateTime value of the start time of this stage.
     */
    public LocalDateTime getStageStartTime() {
        return this.stageStartTime;
    }

    /**
     * Used to get the state of the stage.
     * 
     * @return A string value of the state of this stage.
     */
    public String getStageState() {
        return this.stageState;
    }

    /**
     * Used to get the type of the stage.
     * 
     * @return A Stagetype value of the type of this stage.
     */
    public StageType getStageType() {
        return this.stageType;
    }

    /**
     * The purpose of this method is to check if this stage state
     * is currently "waiting for results".
     * 
     * @throws InvalidStageStateException
     */
    public void isStageNotWaitingForResultsCheck() throws InvalidStageStateException {
        if (this.stageState.equals("waiting for results")) {
			throw new InvalidStageStateException("Waiting for results is the current stage state.");
		}
    }

    public void addSegmentToStage(Segment segment) {
        //Puts segments in required format
        int count = 0;
        for (Segment temp : this.stageSegments) {
            if (temp.getSegmentLocation() > segment.getSegmentLocation()) {
                break;
            }
            count++;
        }

        this.stageSegments.add(count, segment);
    }

    /**
     * The purpose of this methods is to return a list 
     * of all the segment ids in a stage.
     * 
     * @return A list of type Segment with all segments.
     */
    public int[] getSegmentIdsFromStage() {
        int[] listOfSegmentIds = new int[this.stageSegments.size()];
        for (int i = 0; i < listOfSegmentIds.length; i++) {
            listOfSegmentIds[i] = this.stageSegments.get(i).getStageId();
        }
        return listOfSegmentIds;
    }

    /**
     * The purpose of this methods is to return all the segments in 
     * a stage.
     * 
     * @return An array of type Segment.
     */
    public Segment[] getStageSegments() {
        return this.stageSegments.toArray(new Segment[0]);
    }

    /**
     * The purpose of this method is to remove a specified segment
     * from the stage.
     * 
     * @param segment
     */
    public void removeStageSegment(Segment segment) {
        this.stageSegments.remove(segment);
    }

    /**
     * The purpose of this method is to set the stage
     * state.
     * 
     * @param string
     */
    public void setStageState(String string) {
        this.stageState = string;
    }

    /**
     * The purpose of this method is to retieve the
     * stage results from a particular rider.
     * 
     * @param riderId
     * @return An array of type LocalTime holding the
     * stage results for a rider.
     */
    public LocalTime[] getStageResults(int riderId) {
        if (!this.stageResults.containsKey(riderId)) {
            return new LocalTime[0];
        }
        ArrayList<LocalTime> listOfRiderResults = this.stageResults.get(riderId);
        LocalTime[] riderResultsFromArray = new LocalTime[listOfRiderResults.size()-1];
        for (int j = 1; j < listOfRiderResults.size()-1; j++) {
            riderResultsFromArray[j-1] = listOfRiderResults.get(j);
        }
        LocalTime timePassedForRider = LocalTime.ofNanoOfDay(getElapsedTimeOfRiderFromId(riderId));
        riderResultsFromArray[listOfRiderResults.size()-2] = timePassedForRider;
        return riderResultsFromArray;
    }

    /**
     * The purpose of this method is to return the elapsed
     * time of the rider.
     * 
     * @param riderId
     * @return The long value of the elapsed time of
     * the rider.
     */
    private long getElapsedTimeOfRiderFromId(int riderId) {
        assert (this.stageResults.containsKey(riderId));
        int positionOfLastSegment = this.stageSegments.size() + 1;
        LocalTime timeOfRidersStart = this.stageResults.get(riderId).get(0);
        LocalTime riderTimeOfFinish = this.stageResults.get(riderId).get(positionOfLastSegment);
        long elapsedTimeOfRider = riderTimeOfFinish.toNanoOfDay() - timeOfRidersStart.toNanoOfDay();
        if (elapsedTimeOfRider < 0) {
            elapsedTimeOfRider += 1000000000L*60L*60L*24L;
        }
        return elapsedTimeOfRider;
    }

    /**
     * The purpose of this method is to add an inputted set of riders
     * results to the stage.
     * 
     * @param riderId
     * @param checkpoints
     */
    public void addStageResults(int riderId, LocalTime[] checkpoints) {
        ArrayList<LocalTime> arrayOfResults = new ArrayList<LocalTime>();
        for (LocalTime item : checkpoints) {
            arrayOfResults.add(item);
        }
		this.stageResults.put(riderId, arrayOfResults);
    }

    /**
     * The purpose of this method is to check if this stage state
     * is currently "waiting for results".
     * 
     * This method is the same as the isStageNotWaitingForResultsCheck()
     * but is used for the opposite reason.
     * 
     * @throws InvalidStageStateException
     */
    public void isStageWaitingForResultsCheck() throws InvalidStageStateException {
        if (!this.stageState.equals("waiting for results")) {
			throw new InvalidStageStateException("Waiting for results is the current stage state.");
		}
    }

    /**
     * The purpose of this method is to remove the results for
     * a specified rider.
     * 
     * @param riderId
     * 
     */
    public void deleteRiderResultsFromStage(int riderId) {
        this.stageResults.remove(riderId);
    }

    /**
     * The purpose of this method is to obtain the adjusted elapsed time
     * of a specified rider.
     * 
     * Note: If two riders finish inside of 1 second, then
     * they both have the quicker time.
     * 
     * @param riderId
     * @return A time of type LocalTime.
     * 
     */
    public LocalTime getAdjustedElapsedTimeOfRider(int riderId) {
        if (!this.stageResults.containsKey(riderId)) {
            return null;
        }
        
        long elapsedTimeOfRider = getElapsedTimeOfRiderFromId(riderId);
        if (this.stageType == StageType.TT) {
			return LocalTime.ofNanoOfDay(elapsedTimeOfRider);
		}

        boolean isAdjustmentMade = false;

		while ( isAdjustmentMade == false) {
            for (Integer tempComp : this.stageResults.keySet()) {
                long altRiderElapsedTime = getElapsedTimeOfRiderFromId(tempComp);
                long gap = elapsedTimeOfRider - altRiderElapsedTime;
                if (gap <= 1000000000L && gap > 0L) {
                    elapsedTimeOfRider = altRiderElapsedTime;
                    isAdjustmentMade = true;
                }
			}
        }
		return LocalTime.ofNanoOfDay(elapsedTimeOfRider);
    }

    /**
     * The purpose of this method is to return a list
     * of the ids of the riders which are organised by
     * their elapsed time.
     * 
     * @return A list of type integer sorted with ids.
     */
    public int[] getRanksOfRiders() {

        ArrayList<Long> tempList = new ArrayList<Long>();
        ArrayList<Integer> listOfSortedPositions = new ArrayList<Integer>();
        int pointer = 0;

        for (Integer temp : this.stageResults.keySet()) {
            int position = 0;
            long elapsedTimeOfRider = getElapsedTimeOfRiderFromId(temp);
            for (position = 0; position < tempList.size(); position++) {
                if (tempList.get(position) > elapsedTimeOfRider) {
                    break;
                }
            }
            tempList.add(position, elapsedTimeOfRider);
            listOfSortedPositions.add(position, pointer);
            pointer++;
        }

        int[] sortedPositions = new int[listOfSortedPositions.size()];
        for (int i = 0; i < listOfSortedPositions.size(); i++) {
            sortedPositions[i] = listOfSortedPositions.get(i).intValue();
        }

        int[] positions = sortedPositions;
        int[] ranksOfRiders = new int[this.stageResults.size()];
        int count = 0;
        for (Integer riderId : this.stageResults.keySet()) {
            ranksOfRiders[positions[count]] = riderId;
            count++;
        }

        return ranksOfRiders;
    }

    public LocalTime[] getRidersAdjustedTimesRanked() {

        ArrayList<Long> tempList = new ArrayList<Long>();
        ArrayList<Integer> listOfSortedPositions = new ArrayList<Integer>();
        int pointer = 0;

        for (Integer temp : this.stageResults.keySet()) {
            int position = 0;
            long elapsedTimeOfRider = getElapsedTimeOfRiderFromId(temp);
            for (position = 0; position < tempList.size(); position++) {
                if (tempList.get(position) > elapsedTimeOfRider) {
                    break;
                }
            }
            tempList.add(position, elapsedTimeOfRider);
            listOfSortedPositions.add(position, pointer);
            pointer++;
        }

        int[] sortedPositions = new int[listOfSortedPositions.size()];
        for (int i = 0; i < listOfSortedPositions.size(); i++) {
            sortedPositions[i] = listOfSortedPositions.get(i).intValue();
        }



        int[] positions = sortedPositions;
        LocalTime[] ridersAdjustedTimesRanked = new LocalTime[this.stageResults.size()];
        int count = 0;
        for (Integer temp : this.stageResults.keySet()) {
            ridersAdjustedTimesRanked[positions[count]] = getAdjustedElapsedTimeOfRider(temp);
            count++;
        }
        return ridersAdjustedTimesRanked;
    }

    /**
     * This is the constructor for a stage object.
     * 
     * @param raceId
     * @param stageId
     * @param stageName
     * @param description
     * @param length
     * @param startTime
     * @param type
     */
    public Stage(int raceId, int stageId, String stageName, String description, double length, LocalDateTime startTime,
            StageType type) {
        
        this.stageLength = length;
        this.stageId = stageId;
        this.raceId = raceId;
        this.stageDescription = description;
        this.stageName = stageName;
        this.stageStartTime = startTime;
        this.stageState = "prep";
        this.stageType = type;
    }
}