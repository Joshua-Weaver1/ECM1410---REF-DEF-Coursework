package cycling;

/**
 * This class is used to create an instance
 * of an intermediate sprint, which is a variation 
 * of a segment.
 * 
 * @author Joshua Weaver
 * 
 */

public class IntermediateSprint extends Segment{

  /**
   * A constructor for a intermediate sprint object.
   * @param stageId
   * @param segmentId
   * @param location
   * @param sprint
   */
  public IntermediateSprint(int stageId, int segmentId, double location, SegmentType sprint) {
    //Method from parent class
    super(stageId, segmentId, location, sprint);
  }
}
