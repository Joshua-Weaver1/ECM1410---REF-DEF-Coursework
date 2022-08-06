package cycling;

import java.io.Serializable;

/**
 * A class used to create an instance of a segment.
 * 
 * @author Joshua Weaver
 * 
 */

public class Segment implements Serializable{

    //Instance variables
    private SegmentType segmentType;
    private double segmentLocation;
    private int segmentId;
    private int stageId;

    /**
     * This method is used to obtain the segment 
     * type for this segment.
     * 
     * @return The SegmentType value for segment type.
     */
    public SegmentType getSegmentType() {
        return this.segmentType;
    }

    /**
     * This method is used to obtain the segment 
     * location for this segment.
     * 
     * @return The double value for segment location.
     */
    public double getSegmentLocation() {
        return this.segmentLocation;
    }

    /**
     * The purpose of this method is used to obtain the stage
     * id for this segment.
     * 
     * @return The int value for stage id.
     */
    public int getStageId() {
        return this.stageId;
    }

    /**
     * This purpose of this method is used to obtain the segment
     * id for this segment.
     * 
     * @return The int value for segment id.
     */
    public int getSegmentId() {
        return this.segmentId;
    }

    /**
     * A constructor for a segment.
     * 
     * @param stageId
     * @param segmentId
     * @param location
     * @param type
     */
    public Segment(int stageId, int segmentId, Double location, SegmentType type) {
        this.segmentType = type;
        this.segmentLocation = location;
        this.segmentId = segmentId;
        this.stageId = stageId;
    }

}
