import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
For this system we would like to generate a report that lists:

- The total size of all files stored; and
- The top N collections (by file size) where N can be a user-defined value

An example input into your report generator might look like:
[
  {file: "file1.txt", size: 100},
  {file: "file2.txt", size: 200, collectionId: "collection1"},
  {file: "file3.txt", size: 200, collectionId: "collection1"},
  {file: "file4.txt", size: 300, collectionId: "collection2"},
  {file: "file5.txt", size: 10}
]

# files
[
  {file: "file1.txt", size: 100},
  {file: "file2.txt", size: 200, collectionIds: ["collection1"]},
  {file: "file3.txt", size: 200, collectionIds: ["collection1"]},
  {file: "file4.txt", size: 300, collectionIds: ["collection2", "collection3"]},
  {file: "file5.txt", size: 10}
]

*/
public class FilesAndCollections {


 public static void main(String[] args) {

     List<Map.Entry<String, Integer>> topCollections = new LinkedList<>();

     List<Storage> data = new LinkedList<>();
     data.add(new Storage("file1.txt", 100));
     List<String> collection1 = new LinkedList<>();
     collection1.add("collection1");
     List<String> collection2 = new LinkedList<>();
     collection2.add("collection2");
     collection2.add("collection3");
     data.add(new Storage("file2.txt", 200,collection1));
     data.add(new Storage("file3.txt", 200,collection1));
     data.add(new Storage("file4.txt", 300,collection2));
     data.add(new Storage("file5.txt", 10));
     int n = 5;
     int totalSize = getTotalSizeAndTopCollections(data, topCollections, n);

     System.out.println("Total Size : " + totalSize + " Top " + n  + " collections : " + topCollections);

}

    static class Storage {
        public String fileName;
        public int size;
        public List<String> collectionId;


        public Storage(String fileName, int size, List<String> collectionId) {
            this.fileName = fileName;
            this.size = size;
            this.collectionId = collectionId;
        }

        public Storage(String fileName, int size) {
            this(fileName, size, null);
        }
    }
   public static int getTotalSizeAndTopCollections(final List<Storage> data, List<Map.Entry<String, Integer>> topCollections, int size) {
       int totalSize = 0;
       Map<String, Integer> collectionSize = new HashMap<>();
       for(Storage currStorage : data) {
           totalSize += currStorage.size;
           if(currStorage.collectionId != null) {
                for(String eachCollection : currStorage.collectionId) {
                    collectionSize.put(eachCollection, collectionSize.getOrDefault(eachCollection, 0) + currStorage.size);
                }
           }
       }
       //if we have  collectionList size >> n we can implement a priority queue instead of sorting all the elements

       List<Map.Entry<String, Integer>> collectionList = new ArrayList<>(collectionSize.entrySet());
       size = size > collectionList.size() ?  collectionList.size() : size;
       Collections.sort(collectionList, (c1, c2) -> c2.getValue() - c1.getValue());
       for(int i = 0; i < size; ++i) {
           topCollections.add(collectionList.get(i));
       }
       return totalSize;

   }
}
