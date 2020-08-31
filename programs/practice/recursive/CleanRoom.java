/**
 *  This is the robot's control interface.
 *  You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
}
public class CleanRoom {
    public static void main(String[] args) {

    }

    public void cleanRoom(Robot robot) {

        clean(robot, 0, 0, 0, new HashSet());
    }

    void clean(Robot r, int x, int y, int direction, Set<String> visited)
    {
        String pos = "" + x + "," + y;
        if (visited.contains(pos)) return;

        r.clean();
        visited.add(pos);

        // there are 4 neighbors in total ..
        for (int i = 0; i < 4; ++i)
        {
            r.turnRight();
            direction = (direction + 90) % 360;

            if (r.move())
            {
                int nx = x;
                int ny = y;
                switch (direction)
                {
                    case 0:
                        ++ny;
                        break;
                    case 90:
                        ++nx;
                        break;
                    case 180:
                        --ny;
                        break;
                    case 270:
                        --nx;
                        break;
                }
                clean(r, nx, ny, direction, visited);

                r.turnRight();
                r.turnRight();
                r.move();
                r.turnRight();
                r.turnRight();
            }
        }
    }
}

