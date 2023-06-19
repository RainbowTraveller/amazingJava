import edu.princeton.cs.algs4.*;

import java.util.Iterator;

/*
Write a program to solve the 8-puzzle problem (and its natural generalizations) using the A* search algorithm.
The 8-puzzle is a sliding puzzle that is played on a 3-by-3 grid with 8 square tiles labeled 1 through 8, plus a blank square.
The goal is to rearrange the tiles so that they are in row-major order, using as few moves as possible.
You are permitted to slide tiles either horizontally or vertically into the blank square.
The following diagram shows a sequence of moves from an initial board (left) to the goal board (right).


0 1 3       1 0 3           1 2 3       1 2 3       1 2 3
4 2 5  ->   4 2 5   ->      4 0 5   ->  4 5 0  ->   4 5 6
7 8 6       7 8 6           7 8 6       7 8 6       7 8 0

 */
public class Solver {
    /*
    The input board to start from
     */
    private final Board initial;
    /*
    Flag indicating if goal state was reached either in actual solution method or just while checking if the board
    is solvable.
     */
    private boolean isGoal = false;

    /*
    Inner class used to track the A* algorithm states. This includes link to the previous node in the path, the
    current board and no. of moved required to reach the board
    Note : this implements Comparable interface as it needs to be the part of priority queue
     */
    private static class SearchNode implements Comparable<SearchNode> {
        /*
        Link to the previous node in the path
         */
        SearchNode prev;
        /*
        The number of moves required to reach the current state
         */
        int moves;
        /*
        The puzzle board corresponding to this search node state
         */
        Board board;

        public SearchNode(Board curr, SearchNode prev, int moves) {
            this.prev = prev;
            this.moves = moves;
            this.board = curr;
        }

        /**
         * Returns the priority of the search node. This is the crux of the A* Algorithm. It is based on the heuristic.
         * It is calculated using the manhattan distance of the board with respect to the goal baord and no. of moves
         * made to reach here from the initial board.
         *
         * @return integer value of the priority based on the manhattan and moves
         */
        public int getPriority() {
            return board.manhattan() + getMoves();
        }

        public SearchNode getPrev() {
            return prev;
        }

        public int getMoves() {
            return moves;
        }

        /**
         * Compares two search nodes based on the priority
         *
         * @param o the object to be compared.
         * @return 0, -1 or 1 as per the priority of current and parameter object
         */
        @Override
        public int compareTo(SearchNode o) {
            return this.getPriority() - o.getPriority();
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Null board input");
        }
        this.initial = initial;
    }

    // is the initial board solvable? (see below)

    /**
     * Detecting unsolvable boards. Not all initial boards can lead to the goal board by a sequence of moves
     * To detect such situations, use the fact that boards are divided into two equivalence classes with respect to
     * reachability:
     * - Those that can lead to the goal board
     * - Those that can lead to the goal board if we modify the initial board by swapping any pair of tiles
     * (the blank square is not a tile).
     * (Difficult challenge for the mathematically inclined: prove this fact.)
     * To apply the fact, run the A* algorithm on two puzzle instances—one with the initial board and one with the
     * initial board modified by swapping a pair of tiles—in lockstep (alternating back and forth between exploring
     * search nodes in each of the two game trees). Exactly one of the two will lead to the goal board.
     *
     * @return if a given board is solvable or not
     * <p>
     * Here we replicated the solution method. We use given board and its twin and try to solve the puzzle. if initial
     * board reached the solution then it is solvable and it twin board reaches the solution then initial board is
     * unsolvable.
     */
    public boolean isSolvable() {
        MinPQ<SearchNode> minPQInit = new MinPQ<>();
        MinPQ<SearchNode> minPQTwin = new MinPQ<>();

        minPQInit.insert(new SearchNode(initial, null, 0));
        minPQTwin.insert(new SearchNode(initial.twin(), null, 0));

        while ((!minPQInit.isEmpty()) || (!minPQTwin.isEmpty())) {
            SearchNode currNode = minPQInit.delMin();
            SearchNode currTwinNode = minPQTwin.delMin();

            Board currBoard = currNode.board;
            Board currTwinBoard = currTwinNode.board;

            //Check if either of the board has led to the goal state
            if (currBoard.isGoal() || currTwinBoard.isGoal()) {

                if (currBoard.isGoal()) {
                    // If the initial board reached then solvable
                    isGoal = true;
                }
                // if twin board reaches it then no way initial board can reach the solution
                if (currTwinBoard.isGoal()) {
                    isGoal = false;
                }
                break;
            }

            for (Board board : currBoard.neighbors()) {
                if (currNode.getPrev() == null || !board.equals(currNode.getPrev().board)) {
                    minPQInit.insert(new SearchNode(board, currNode, currNode.getMoves() + 1));
                }
            }

            for (Board board : currTwinBoard.neighbors()) {
                if (currTwinNode.getPrev() == null || !board.equals(currTwinNode.getPrev().board)) {
                    minPQTwin.insert(new SearchNode(board, currTwinNode, currTwinNode.getMoves() + 1));
                }
            }
        }
        return isGoal;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        int moves = 0;
        /*
            Iterator enabling listing the path from initial to goal board
        */
        Iterator<Board> iterator = null;
        // First check if the board is solvable
        if (isSolvable()) {
            iterator = this.solution().iterator();
            //Check the moves by tracing the iterator
            while (iterator.hasNext()) {
                iterator.next();
                moves++;
            }

        }
        return moves - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        // The queue is used to push the valid nodes as they are encountered
        // Later iterator dequeues them in the same order
        Queue<Board> path = new Queue<>();
        MinPQ<SearchNode> boardMinPQ = new MinPQ<>();
        boardMinPQ.insert(new SearchNode(initial, null, 0));
        while (!boardMinPQ.isEmpty()) {
            SearchNode currNode = boardMinPQ.delMin();
            Board currBoard = currNode.board;
            if (currBoard.isGoal()) {
                isGoal = true;
                // When goal is reached populate the queue by backtracking from the goal node to the initial node
                while (currBoard != null) {
                    path.enqueue(currBoard);
                    currNode = currNode.prev;
                    //Make sure to check for the null node as the parent to initial node is set to null
                    // which may brake the code
                    currBoard = currNode == null ? null : currNode.board;
                }
                break;
            }
            for (Board board : currBoard.neighbors()) {
                if (currNode.getPrev() == null || !board.equals(currNode.getPrev().board)) {
                    boardMinPQ.insert(new SearchNode(board, currNode, currNode.getMoves() + 1));
                }
            }
        }
        return isGoal ? path : null;
    }

    // test client (see below)
    public static void main(String[] args) {
        // for each command-line argument
        for (String filename : args) {

            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            Solver solver = new Solver(initial);
            //StdOut.println(filename + ": " + solver.moves());
            // print solution to standard output
            if (!solver.isSolvable())
                StdOut.println("No solution possible");
            else {
                StdOut.println("Minimum number of moves = " + solver.moves());
//                for (Board board : solver.solution())
//                    StdOut.println(board);
            }
//            System.out.println(solver.moves());
//            System.out.println(solver.isSolvable());
//            System.out.println(solver.isSolvable());
//            solver.solution();
//            System.out.println(solver.moves());
//            solver.solution();
//            System.out.println(solver.isSolvable());
//            System.out.println(solver.moves());
        }
    }

}
