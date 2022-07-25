package cycling;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A class used to create an instance of a stage.
 * 
 * @author Joshua Weaver
 */

public class Stage {

    private String stageName;
    private String stageDescription;
    private double stageLength;
    private int stageId;
    private int raceId;
    private LocalDateTime stageStartTime;
    private String stageState;
    private StageType stageType;
    private ArrayList<Segment> segments = new ArrayList<Segment>();

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
        this.stageType = type;
        this.stageState = "preparation";
    }

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

    public void addSegmentToStage(CategorizedClimb categorizedClimb) {
        // Ensures that the segments are stored in chronological order
        int sortedIndex = 0;
        for (Segment comparison : this.segments) {
            if (comparison.getSegmentLocation() > categorizedClimb.getSegmentLocation()) {
                break;
            }
            sortedIndex++;
        }

        this.segments.add(sortedIndex, categorizedClimb);
    }

    /**
     * The purpose of this methods is to return a list 
     * of all the segments in a stage.
     * 
     * @return A list of type Segment with all segments.
     */
    public int[] getSegmentIdsFromStage() {
        int[] listOfSegmentIds = new int[this.segments.size()];
        for (int i = 0; i < listOfSegmentIds.length; i++) {
            listOfSegmentIds[i] = this.segments.get(i).getStageId();
        }
        return listOfSegmentIds;
    }
    
}
