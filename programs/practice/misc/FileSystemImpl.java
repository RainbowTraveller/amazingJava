import java.util.*;

/*
 *  Implement in memory file system
 *  with writeDate(path, data)
 *  mkDir(path)
 *  readData(path)
 *
 */
public class FileSystemImpl {
    public static void main(String[] args) {

    }
}


class FileSystem {
    Node rootDirectory;

    public FileSystem() {
        rootDirectory = new Node("/", false);
    }

    public void mkdir(String path) {
        if(path != null) {
            String[] directories = path.split("/");
            Node currDirectory = rootDirectory;
            for(String directory : directories) {
                if(currDirectory.subNodes == null ) {
                    currDirectory.subNodes = new HashMap<>();
                }

                if(!currDirectory.subNodes.containsKey(directory)) {
                    Node newDirectory = new Node(directory, false);
                    currDirectory.subNodes.put(directory, newDirectory);
                }
                currDirectory = currDirectory.subNodes.get(directory);
            }
        }
    }

    private Node getDirectory(String path) {
        Node currDirectory = null;
        if(path != null) {
            currDirectory = rootDirectory;
            String[] directories = path.split("/");
            for(String directory : directories) {
                if(currDirectory.subNodes == null || !currDirectory.subNodes.containsKey(directory)) {
                    return null;
                }
                currDirectory = currDirectory.subNodes.get(directory);
            }
        }
        return currDirectory;
    }

    public void writeFile(String path, String data) {
        String filePath = path.substring(0,path.lastIndexOf("/"));
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        Node fileDir = getDirectory(filePath);
        if(fileDir != null) {
            if(fileDir.subNodes == null) {
                fileDir.subNodes = new HashMap<>();
            }

            if(!fileDir.subNodes.containsKey(fileName)) {
                Node file = new Node(fileName, true);
                file.data = data;
                fileDir.subNodes.put(fileName, file);
            }
        }
    }

    public String readFile(String path) {
        return null;
    }
}

class Node {
    //Actual identifier
    String name;
    //file or directory
    boolean isFile;
    //Content
    String data;
    //sub directories or files
    Map<String, Node> subNodes;

    public Node(String name, boolean isFile) {
        this.name = name;
        this.isFile = isFile;
        data = null;
        subNodes = null;
    }
}


