package cycling;

public class Segment {

    private double segmentLocation;
    private SegmentType segmentType;
    private int stageId;
    private int segmentId;

    public Segment(int stageId, int segmentId, Double location, SegmentType type) {
        this.segmentLocation = location;
        this.segmentType = type;
        this.stageId = stageId;
        this.segmentId = segmentId;
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
     * This method is used to obtain the segment 
     * type for this segment.
     * 
     * @return The SegmentType value for segment type.
     */
    public SegmentType getSegmentType() {
        return this.segmentType;
    }

    /**
     * This method is used to obtain the stage
     * id for this segment.
     * 
     * @return The int value for stage id.
     */
    public int getStageId() {
        return this.stageId;
    }

    /**
     * This method is used to obtain the segment
     * id for this segment.
     * 
     * @return The int value for segment id.
     */
    public int getSegmentId() {
        return this.segmentId;
    }

}
