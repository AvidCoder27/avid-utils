/**
 * Everything is in degrees unless specified otherwise. CCW is positive
 */
public class Angle {
    public final static double DegreesToRadians = Math.PI / 180.0;
    public final static double RadiansToDegrees = 180.0 / Math.PI;

    private final double angle;

    /**
     * Create an Angle given an angle in degrees
     * 
     * @param angle an angle in degrees
     */
    public Angle(double angle) {
        this.angle = angle;
    }

    /**
     * Get the angle in degrees, in a specified range
     * 
     * @param minDegrees the bottom of the range of allowed angles
     * @return an angle in degrees
     */
    public double getDegrees(double minDegrees) {
        return rollover(angle, minDegrees);
    }

    /**
     * Get the angle in radians, in a specified range
     * 
     * @param minRadians the bottom of the range of allowed angles
     * @return an angle in radians
     */
    public double getRadians(double minRadians) {
        return rollover(angle, minRadians * RadiansToDegrees) * DegreesToRadians;
    }

    /**
     * Get the delta between this angle and another angle, moving from this one to
     * the other
     * 
     * @param other the angle to find the delta to
     * @return the delta between the angles with direction
     */
    public double getDelta(Angle other) {
        return getDelta(other, 0);
    }

    /**
     * Get the delta between this angle and another angle, moving from this one to
     * the other
     * 
     * @param other     the angle to find the delta to
     * @param direction the specified direction to allow movement in. 0 is either
     *                  way, -1 is only negative, +1 is only positive
     * @return the delta between the angles with direction
     */
    public double getDelta(Angle other, int direction) {
        double alpha = getDegrees(0);
        double beta = other.getDegrees(0);

        double forwards = rollover(beta - alpha, 0);
        double backwards = -rollover(alpha - beta, 0);

        if (direction > -1 && (direction > 0 || Math.abs(forwards) < Math.abs(backwards))) {
            return forwards;
        } else {
            return backwards;
        }
    }

    /**
     * Put an angle inside of a range
     * 
     * @param angle the input angle
     * @param min   the bottom of the range of allowed outputs
     * @return an equivalent angle
     */
    public static double rollover(double angle, double min) {

        return goodModulo(angle - min, 360.0) + min;
    }

    private static double goodModulo(double a, double b) {
        return a < 0 ? b + a : a % b;
    }

    public String ToString() {
        return Double.toString(getDegrees(0));
    }
}