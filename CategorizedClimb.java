package cycling;

public class CategorizedClimb extends Segment{

    private double categorizedClimbAverageGradient;
    private double categorizedClimbLength;

    public CategorizedClimb(int stageId, int segmentId, Double length, Double location, Double averageGradient,
            SegmentType type) {
            super(stageId, segmentId, location, type);
            this.categorizedClimbAverageGradient = averageGradient;
            this.categorizedClimbLength = length;
    }

    /**
     * This method will return the average gradient of the 
     * categorized climb.
     * 
     * @return An double value of the average gradient of the 
     * categorized climb.
     */
    public double getCategorizedClimbAverageGradient() {
        return this.categorizedClimbAverageGradient;
    }

    /**
     * This method will return the length of the 
     * categorized climb.
     * 
     * @return An double value of the length of the 
     * categorized climb.
     */
    public double getCategorizedClimbLength() {
        return this.categorizedClimbLength;
    }

}
