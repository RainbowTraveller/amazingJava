import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;

/*
Class representing game board for n-puzzle game.
 */
public class Board {

    private final int[][] tiles;
    private int dimension = 0;

    private int manhattan = -1;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null || tiles.length != tiles[0].length) {
            throw new IllegalArgumentException("Invalid input board : null or asymmetric");
        }
        dimension = tiles.length;
        this.tiles = new int[dimension][dimension];
        for (int row = 0; row < dimension; ++row) {
            for (int col = 0; col < dimension; ++col) {
                this.tiles[row][col] = tiles[row][col];
            }
        }
        this.manhattan = manhattan();
    }

    // string representation of this board
    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append(this.dimension);
        display.append("\n");
        for (int row = 0; row < dimension; ++row) {
            for (int col = 0; col < dimension; ++col) {
                display.append(this.tiles[row][col]);
                display.append(" ");
            }
            display.append("\n");
        }
        return display.toString();
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of tiles out of place
    public int hamming() {
        int misplaced = 0;
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                int current = tiles[i][j];
                if (current != 0 && current != dimension * i + (j + 1)) {
                    misplaced++;
                }
            }
        }
        return misplaced;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        if (manhattan < 0) {
            int misplaced = 0;
            for (int i = 0; i < dimension; ++i) {
                for (int j = 0; j < dimension; ++j) {
                    int current = tiles[i][j];
                    if (current != 0) {
                        //Finding the correct row for the given value
                        int row = current / dimension;
                        if (current % dimension == 0) {
                            row = row - 1;
                        }
                        //Finding the correct column for the given value
                        int col = current - (dimension * row) - 1;
                        int rowDiff = row - i;
                        rowDiff = rowDiff < 0 ? (-1 * rowDiff) : rowDiff;
                        int colDiff = col - j;
                        colDiff = colDiff < 0 ? (-1 * colDiff) : colDiff;
                        misplaced = misplaced + rowDiff + colDiff;
                    }
                }
            }
            this.manhattan = misplaced;
        }
        return this.manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        //As initial implementation a goal state board was created and stored as a part of the constructor.
        // It led to increasing memory usage. It also involved n^2 iteration logic. So in the modification,
        // the goal state output numbers are calculated at run time for comparison
        for (int row = 0; row < dimension; ++row) {
            for (int col = 0; col < dimension; ++col) {
                int current = this.tiles[row][col];
                if ((row == dimension - 1) && (col == dimension - 1) && (current == 0)) {
                    // We have reached the end and just check if that position contains
                    // 0
                    return true;
                } else if (current != dimension * row + (col + 1)) {
                    // if any other position does not contain the desired no. goal state is not reached
                    return false;
                }
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y == this) return true;
        if (this.getClass() == y.getClass()) {
            Board supplied = (Board) y;
            if (this.dimension() == supplied.dimension()) {
                for (int i = 0; i < dimension; ++i) {
                    for (int j = 0; j < dimension; ++j) {
                        if (tiles[i][j] != supplied.tiles[i][j]) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return getNeighbors();
    }

    private Iterable<Board> getNeighbors() {
        ArrayList<Board> neighbors = new ArrayList<>();
        //Check surrounding tiles of the empty slot
        // exchange the nonempty tile with the empty slot
        // stored the resultant board as a neighboring one
        // restored the tiles and generate subsequent boards
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                // check if the tile is empty
                if (tiles[i][j] == 0) {
                    // Check previous row
                    if (i + 1 < dimension) {
                        exchange(i, j, i + 1, j);
                        Board b1 = new Board(tiles);
                        neighbors.add(b1);
                        exchange(i, j, i + 1, j);
                    }
                    // Check next row
                    if (i - 1 >= 0) {
                        exchange(i, j, i - 1, j);
                        Board b2 = new Board(tiles);
                        neighbors.add(b2);
                        exchange(i, j, i - 1, j);
                    }
                    // Check next col
                    if (j + 1 < dimension) {
                        exchange(i, j, i, j + 1);
                        Board b3 = new Board(tiles);
                        neighbors.add(b3);
                        exchange(i, j, i, j + 1);
                    }
                    // Check previous col
                    if (j - 1 >= 0) {
                        exchange(i, j, i, j - 1);
                        Board b4 = new Board(tiles);
                        neighbors.add(b4);
                        exchange(i, j, i, j - 1);
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * Exchange the tiles at a given row and col
     *
     * @param boardTiles board containing the tiles
     * @param fromRow
     * @param fromCol
     * @param toRow
     * @param toCol
     */
    private void exchange(int[][] boardTiles, int fromRow, int fromCol, int toRow, int toCol) {
        int buff = boardTiles[fromRow][fromCol];
        boardTiles[fromRow][fromCol] = boardTiles[toRow][toCol];
        boardTiles[toRow][toCol] = buff;
    }

    private void exchange(int fromRow, int fromCol, int toRow, int toCol) {
        exchange(tiles, fromRow, fromCol, toRow, toCol);
    }

    // a board that is obtained by exchanging any pair of tiles

    /**
     * A twin board is a board which is obtained by exchanging any pair of tiles.
     * The requirement is that it should be unique for a given board. Any number of calls should return the same
     * board.
     *
     * @return a twin board
     */
    public Board twin() {
        int[][] twinTiles = new int[dimension][dimension];
        int randRowFrom = -1, randColFrom = -1, randRowTo = -1, randColTo = -1;

        for (int i = 0; i < dimension; ++i) {
            twinTiles[i] = Arrays.copyOf(tiles[i], dimension);
        }

        // Just get first two tiles which are not empty and exchange them
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                if (twinTiles[i][j] != 0 && randRowFrom < 0 && randColFrom < 0) {
                    randRowFrom = i;
                    randColFrom = j;
                } else if (twinTiles[i][j] != 0 && randRowTo < 0 && randColTo < 0) {
                    randRowTo = i;
                    randColTo = j;
                }
            }
        }
        exchange(twinTiles, randRowFrom, randColFrom, randRowTo, randColTo);
        return new Board(twinTiles);
    }


    // unit testing (not graded)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
//                tiles[i][j] = in.readInt();
                tiles[i][j] = n * i + (j + 1);
        tiles[n - 1][n - 1] = 0;
        Board initial = new Board(tiles);
//        System.out.println(initial);
//        System.out.println(initial.isGoal());
//        System.out.println(initial.twin());
//        initial.twin();
//        for (Board board : initial.neighbors()) {
//            System.out.println(board);
//            System.out.println(board.manhattan());
//        }
    }
}