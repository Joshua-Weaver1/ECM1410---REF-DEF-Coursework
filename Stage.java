package cycling;

import java.time.LocalDateTime;

public class Stage {

    private String stageName;
    private String stageDescription;
    private double stageLength;
    private int raceId;
    private int stageId;
    private LocalDateTime stageStartTime;
    private String stageState;
    private StageType stageType;

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
        this.raceId = raceId;
        this.stageId = stageId;
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
    
}
