import java.util.*;

public class Boggle {
  final char[][] grid;
  boolean[][] usedMap;
  final Dictionary refer;

  public Boggle(Dictionary refer) {
    // public Boggle(char[][] grid, Dictionary refer) {
    this.refer = refer;
    this.grid = new char[][] {{'p', 'g', 'r'}, {'t', 'q', 's'}, {'m', 'b', 'e'}};
    /* p g r
     * t q s
     * m b e
     * */
    this.usedMap = new boolean[this.grid.length][this.grid[0].length];
  }

  /** Initialize the map to track the used position with false */
  private void initUsedMap() {
    for (int rows = 0; rows < usedMap.length; rows++) {
      for (int cols = 0; cols < usedMap[0].length; cols++) {
        usedMap[rows][cols] = false;
      }
    }
  }

  /** Function to look up the given word in the Dictionary */
  public boolean lookUp(String word) {
    return refer.query(word);
  }

  /** Kickstart solve */
  public void solve() {
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        System.out.println("CHAR : " + grid[row][col]);
        initUsedMap();
        StringBuffer current = new StringBuffer();
        current.append(grid[row][col]);
        find(row, col, current);
      }
    }
  }

  private void find(int row, int col, StringBuffer current) {
    if (current.length() > 3) {
      String currentWord = current.toString();
      if (lookUp(currentWord)) {
        System.out.println("Meaningful word :" + currentWord);
      }
    }
    for (int i = -1; i < 2; ++i) {
      // System.out.println(" I  : " + i);
      int newY = row + i;
      for (int j = -1; j < 2; ++j) {
        // System.out.println(" J  : " + j);
        int newX = col + j;

        if (!(i == j && j == 0)) {
          /*for(int rows = 0; rows < usedMap.length; rows++) {
          	for(int cols = 0; cols < usedMap[0].length; cols++) {
          		System.out.println(rows + " " + cols + "  " + usedMap[rows][cols]);
          	}
          }*/
          if (isValidPosition(newY, newX) && !usedMap[newY][newX]) {
            usedMap[newY][newX] = true;
            current.append(grid[newY][newX]);
            find(newY, newX, current);
            current.deleteCharAt(current.length() - 1);
            usedMap[newY][newX] = false;
          }
        }
      }
    }
  }

  private boolean isValidPosition(int row, int col) {
    // System.out.println("r : " + row + " c :" + col);
    // System.out.println(this.grid.length  + " ---------- "  + this.grid[0].length);
    return (row > -1 && col > -1 && row < this.grid.length && col < this.grid[0].length);
  }

  public static void main(String[] args) {
    final List<String> setOfStrings = new ArrayList<>();
    setOfStrings.add("pqrs");
    setOfStrings.add("pprt");
    setOfStrings.add("psst");
    setOfStrings.add("qqrs");
    setOfStrings.add("rqebtg");
    setOfStrings.add("mqsg");
    final Dictionary trie = new Dictionary();
    for (String s : setOfStrings) {
      trie.insert(s);
    }
    Boggle board = new Boggle(trie);
    board.solve();
    /*System.out.println(trie.query("psst"));
          System.out.println(trie.query("qqrs"));
          System.out.println(trie.query("pmst"));
          System.out.println(trie.query("mqsg"));
    */
  }
}

class Dictionary {
  final DictNode root;

  public Dictionary() {
    root = new DictNode();
  }

  public boolean query(String input) {
    DictNode current = root.next(input.charAt(0));
    for (int i = 1; i < input.length(); ++i) {
      if (current == null) {
        return false;
      }
      current = current.next(input.charAt(i));
    }
    return (current == null ? false : current.isTerminating);
  }

  public void insert(String input) {
    DictNode current = root;
    for (int i = 0; i < input.length(); ++i) {
      if (current.next(input.charAt(i)) == null) {
        current.nextNodes[input.charAt(i) - 'a'] = new DictNode();
      }
      current = current.next(input.charAt(i));
    }
    current.isTerminating = true;
  }
}

class DictNode {
  DictNode[] nextNodes;
  boolean isTerminating;

  public DictNode() {
    nextNodes = new DictNode[26];
    isTerminating = false;
  }

  public DictNode next(final char c) {
    if (nextNodes != null) {
      return nextNodes[c - 'a'];
    }
    return null;
  }
}
