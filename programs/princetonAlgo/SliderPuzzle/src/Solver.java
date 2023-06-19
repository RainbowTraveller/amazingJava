import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    private Board initial;
    private boolean isGoal = false;

    private Iterator<Board> iterator = null;

    private boolean isSolvableCalled = false;

    private static class SearchNode implements Comparable<SearchNode> {
        SearchNode prev;
        int moves;
        Board board;

        public SearchNode(Board curr, SearchNode prev, int moves) {
            this.prev = prev;
            this.moves = moves;
            this.board = curr;
        }

        public int getPriority() {
            return board.manhattan() + getMoves();
        }

        public SearchNode getPrev() {
            return prev;
        }

        public int getMoves() {
            return moves;
        }

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
    public boolean isSolvable() {
        isSolvableCalled = true;
        MinPQ<SearchNode> minPQInit = new MinPQ<>();
        MinPQ<SearchNode> minPQTwin = new MinPQ<>();

        minPQInit.insert(new SearchNode(initial, null, 0));
        minPQTwin.insert(new SearchNode(initial.twin(), null, 0));

        while ((!minPQInit.isEmpty()) || (!minPQTwin.isEmpty())) {
            SearchNode currNode = minPQInit.delMin();
            SearchNode currTwinNode = minPQTwin.delMin();

            Board currBoard = currNode.board;
//            System.out.println(currBoard);
            Board currTwinBoard = currTwinNode.board;

            if (currBoard.isGoal() || currTwinBoard.isGoal()) {
                if (currBoard.isGoal()) {
                    isGoal = true;
                }
                if (currTwinBoard.isGoal()) {
                    isGoal = false;
                }
                break;
            }

            for (Board board : currBoard.neighbors()) {
                if (currNode.getPrev() == null || !board.equals(currNode.getPrev().board)) {
//                    System.out.println(" Manhattan : " + board.manhattan());
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
        iterator = null;
        if (isSolvable()) {
            if (iterator == null) {
                iterator = this.solution().iterator();
            }
            while (iterator.hasNext()) {
                iterator.next();
                moves++;
            }

        }
        return moves - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        Stack<Board> path = new Stack<>();
        MinPQ<SearchNode> boardMinPQ = new MinPQ<>();
//        List<Board> path = new LinkedList<>();
        boardMinPQ.insert(new SearchNode(initial, null, 0));
        while (!boardMinPQ.isEmpty()) {
            SearchNode currNode = boardMinPQ.delMin();
            Board currBoard = currNode.board;
//            path.add(currBoard);
            if (currBoard.isGoal()) {
                isGoal = true;
                while(currBoard != null) {
                    path.push(currBoard);
                    currNode = currNode.prev;
                    currBoard = currNode == null ? null  : currNode.board;
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
