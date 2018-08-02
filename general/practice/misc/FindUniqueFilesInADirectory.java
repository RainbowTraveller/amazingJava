/**
Input is path
output : List of Set : Set should contain the path to files which have content
*/

          root

     a    c

   b      d

   Map<Checksum, List<FilePaths>>

ArrayList<String> listDir(String rootPath);

boolean isDir(String path);


boolean isDirPresent(String path) {

    if(path != null) {
      ArrayList<String> allPath = listDir(path);
      for(String aPath : allPaths) {
        if(isDir(aPath) {
          return true;
        }
      }
      return false;
    }
    return true;
}

public List<Set<String>> findSameFiles(String path) {

    if(path != null) {
        Map<String, List<String>> sameFilesMap = new HashMap<String, List<String>>();
        findSameFilesHelper(path, sameFilesMap);
        //Convert map to List<<Set>>
        List<Set<String>> sameFiles = new ArrayList<Set<String>>();
        for(Object key : sameFilesMap) {
          List<String> files = sameFilesMap.get(key);
          Set<String> sameFileSet = new HashSet<String>(files);
          sameFiles.add(sameFileSet);
        }
        return sameFiles;
    }
    return null;
}

public Map<String, List<String>> findSameFilesHelper(String path,  Map<String, List<String>> fileMap) {

        ArrayList<String> allDirs = listDir(path);
        for(String dir : allDirs) {
          if(isDir(dir)) {
            findSameFilesHelper(dir, fileMap);
          } else {

            processFile(dir, Map<String, List<String>> fileMap);

          }
        }
}

public void processFile(String file, Map<String, List<String>> fileMap) {

    if(file != null) {
      String checksum = getCheckSum(file);
      if(fileMap.containsKey(checksum)) {
        List<String> filePaths = fileMap.get(checksum);
        filePaths.add(file);
      } else {
        List<String> filePaths = new ArrayList<String>();
        filePaths.add(file);
        fileMap.set(checksum, filePaths);
      }
}


