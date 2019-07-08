import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Collections;
import java.util.Comparator;

/*
 * Given a list of candidates find out higest voted candidate
 * Find string occurring max. no. of times in given array of Strings
 */
public class ElectionCandidates {

	public static void main(String [] args) {
		String [] candidates = { "Alex", "Tim", "Joe", "Tim", "Alex", "Alex", "Tim", "Joe", "Garry",  "Tim", "Alex", "Alex", "Tim", "Joe", "Garry", "Alex"};
		System.out.println(electionWinner(candidates));
	}

	public static String electionWinner(String[] votes) {
		Map<String,Integer> voteTracker = new HashMap<String, Integer>();

		//Get frequency based on a Hash Map
		for(String candidate : votes) {
			int voteNumber = voteTracker.getOrDefault(candidate, 0);
			voteTracker.put(candidate, voteNumber + 1);
		}

		//Make Set of Map.Entry
		Set<Map.Entry<String,Integer>> votingSet = voteTracker.entrySet();
		//Make List of Map.Entry
		List<Map.Entry<String, Integer>> listOfVoting = new ArrayList<Map.Entry<String, Integer>>(votingSet);
		Collections.sort( listOfVoting,(e1, e2) -> (e2.getValue() - e1.getValue()));

		/*This works as well
		TreeSet<String> higestVotedCandidates = new TreeSet<String>();
		int preVotes = -1;
		for(Map.Entry<String, Integer> e : listOfVoting) {
			int currentVotes = e.getValue();
			if(preVotes != -1 && preVotes != currentVotes) {
				break;
			}
			preVotes = currentVotes;
			higestVotedCandidates.add(e.getKey());
		}
		return higestVotedCandidates.last();
		*/

		Map.Entry<String, Integer> entry = listOfVoting.get(0);
		return entry.getKey();
	}
}
