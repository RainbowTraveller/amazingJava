import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
    Program managing Cells and SpreadSheet
    A Cell can contain the value or keys to other 2 Cells : this can be a tree as well

    While calculating the value the addition operation is expensive so we need cache to perform well.

  */
public class SpreadSheet {

    /**
      Inner class represening a Cell
      It either contains
      1. Non null value
      2. Two strings indicating key to the cells it is dependent on
      */
    private static class Cell {
        Integer value;
        String child1;
        String child2;

        public Cell(Integer value, String child1, String child2) {
            this.value = value;
            this.child1 = child1;
            this.child2 = child2;
        }
    }

    //Tracks Key and Actual Cell
    Map<String, Cell> tracker;

    //Cache to retrieve value quicker instead calculating all the time
    Map<String, Integer> valueCache;

    //A cell to it's parents mapping. This is required for calculating correct value
    // If a cell changes then we need to invalidate its cache as well as all the
    // parents
    Map<String, Set<String>> dependencies;


    public SpreadSheet() {
        tracker = new HashMap<>();
        valueCache = new HashMap<>();
        dependencies = new HashMap<>();
    }

    /**
      Method to add / update new or existing cell
      1. Add to the map : will overwrite if present
      2. remove from cache and then calculate the value again
      3. If any cell is parent i.e. dependent on this should be removed from cache as well
      4. Add dependecies from children to this cell if any
      */
    public void setCell(String key, Cell currCell) {
        tracker.put(key, currCell);
        updateCache(key);
        updateDepedencies(key, currCell);
    }

    public Integer getCellValue(String key) {
        if(!tracker.containsKey(key)) {
            return 0;
        }
        if(valueCache.containsKey(key)) {
            System.out.println("Reading from Cache");
            return valueCache.get(key);
        }
        Cell currCell = tracker.get(key);
        if(currCell.value != null) {
            return currCell.value;
        } else {
            return getCellValue(currCell.child1) + getCellValue(currCell.child2);
        }
    }

    private void updateCache(String key) {
        valueCache.remove(key);
        if(dependencies.containsKey(key)) {
            Set<String> parents = dependencies.get(key);
            for(String parent : parents) {
                updateCache(parent);
            }
        }
        valueCache.put(key,getCellValue(key));
    }
    private void updateDepedencies(String key, Cell currCell) {
        if(currCell.value == null) {
           String child1 = currCell.child1;
           Set<String> parentCells = dependencies.get(child1);
           if(parentCells != null) {
               parentCells.add(key);
               dependencies.put(child1, parentCells);
           }
           String child2 = currCell.child2;
           parentCells = dependencies.get(child2);
           if(parentCells != null) {
               parentCells.add(key);
               dependencies.put(child2, parentCells);
           }
        }
    }

    public static void main(String[] args) {
        SpreadSheet sheet = new SpreadSheet();
        sheet.setCell("A", new Cell(1, null, null));
        sheet.setCell("B", new Cell(2, null, null));
        sheet.setCell("C", new Cell(3, null, null));

        System.out.println("Simple Operations");
        if(sheet.getCellValue("A") == 1) {
            System.out.println("A passed");
        }

        if(sheet.getCellValue("B") == 2) {
            System.out.println("B passed");
        }

        if(sheet.getCellValue("C") == 3) {
            System.out.println("C passed");
        }

        sheet.setCell("D", new Cell(null, "B", "C"));
        sheet.setCell("E", new Cell(null, "B", "A"));
        sheet.setCell("F", new Cell(null, "D", "E"));

        System.out.println("Dependency Ops");
        if(sheet.getCellValue("E") == 3) {
            System.out.println("E passed");
        }

        if(sheet.getCellValue("D") == 5) {
            System.out.println("D passed");
        }

        if(sheet.getCellValue("F") == 8) {
            System.out.println("F passed");
        }
    }

}
