/**
Input : path
output : List of Lists : internal list should contain the path to files which have same content
Following funcions are given:

ArrayList<String> listDir(String rootPath)
boolean isDir(String path);
*/
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

public class FindUniqueFilesInADirectory {
	public static void main( String [] args ) {
		String path = args[0];
		List<List<String>> sameFilePathGroups = getSameFilePaths(path);
	}

	private static boolean isDir(String path) {
		return true;
	}

	private static ArrayList<String> listDir(String rootPath) {
		return new ArrayList<String>();
	}

	private static String getCheckSum( String path ) {
		return new String();
	}
	/**
	 * Modification of depth first search
	 */
	public static List<List<String>> getSameFilePaths( String path ) {
		if( path != null ) {
			Stack<String> pathStack = new Stack<String>();
			pathStack.push( path );
			Map<String, List<String>> sameFileTracker = new HashMap<String, List<String>>();
			while(!pathStack.isEmpty()) {
				String currentPath = pathStack.pop();
				if( isDir( currentPath ) ) {
					ArrayList<String> currSubPaths = listDir( currentPath );
					for( String eachPath : currSubPaths ) {
						pathStack.push( eachPath );
					}
				} else {
					processFile( currentPath, sameFileTracker );
				}
			}
			List<List<String>> desiredList = new ArrayList<List<String>>();
			for(String checkSum : sameFileTracker.keySet()){
				List<String> sameFilesPath = sameFileTracker.get( checkSum );
				desiredList.add(sameFilesPath);
			}
			return desiredList;
		}
		return null;
	}

	/**
	 *	Calculate the checksum of the file and track against the checksum as
	 *	as key. The value will be list of paths with same checksum
	 */
	private static void processFile( String filePath, Map<String, List<String>> sameFileTracker ) {
		String currCheckSum = getCheckSum(filePath);
		List<String> sameFilePaths = new ArrayList<String>();
		if( sameFileTracker.containsKey(currCheckSum) ) {
			sameFilePaths = sameFileTracker.get( currCheckSum );
		}
		sameFilePaths.add(filePath);
		sameFileTracker.put(currCheckSum, sameFilePaths);
	}
}
