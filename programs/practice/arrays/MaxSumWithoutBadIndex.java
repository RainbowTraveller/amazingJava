class MaxSumWithoutBadIndex {

  /**
   * Finds the maximum final value (sum) reachable after numSteps iterations while ensuring the
   * running sum never equals the badIndex. * The strategy assumes the maximum final sum is achieved
   * by the minimal necessary reduction (skipping a single index j). This guarantees the highest
   * possible final sum S - j that has a valid path. * Time Complexity: O(N^2), where N is numSteps.
   *
   * @param numSteps The number of iterations (indices 1 to numSteps).
   * @param badIndex The forbidden value for any intermediate or final sum.
   * @return The maximum final sum achievable.
   */
  public long findMaxSum(int numSteps, int badIndex) {
    // Calculate the maximum possible sum S (sum of 1 to N).
    // Use long to prevent integer overflow.
    long S = (long) numSteps * (numSteps + 1) / 2;
    final long target = (long) badIndex;

    // --- 1. Check the no-skip path (Max Sum S) ---
    // If the max possible sum S is achievable without conflict, that's the answer.
    long currentSum = 0;
    boolean noSkipValid = true;
    for (int k = 1; k <= numSteps; k++) {
      currentSum += k;
      if (currentSum == target) {
        noSkipValid = false;
        break;
      }
    }
    if (noSkipValid) {
      return S;
    }

    // --- 2. Iterate through single skips j=1 to numSteps ---
    // We search for the smallest single index j to skip (minimal reduction).
    // The first j found that yields a valid path gives the maximum final sum S - j.
    for (int j = 1; j <= numSteps; j++) {
      boolean isValid = true;
      currentSum = 0;

      // Re-simulate the path, skipping ONLY index j
      for (int k = 1; k <= numSteps; k++) {
        if (k != j) {
          currentSum += k; // Option: Add the current index k
        }
        // If k == j, we skip it (add 0), minimizing the reduction.

        if (currentSum == target) {
          isValid = false;
          break;
        }
      }

      if (isValid) {
        // Found the smallest reduction j that works. Max sum is S - j.
        return S - j;
      }
    }

    // --- 3. Fallback ---
    // If no single skip works, we must skip two or more indices.
    // Since skipping every index is always a valid path (sum is always 0),
    // we can guarantee a maximum index of at least 0.
    return 0;
  }

  public static void main(String[] args) {
    MaxIndexFinder solver = new MaxIndexFinder();

    // Example 1: numSteps=3, badIndex=3. S=6. Full path hits 3.
    // Skip 1: Path 0, 2, 5. Max: 5.
    System.out.println("Max Sum for (3 steps, bad=3): " + solver.findMaxSum(3, 3));

    // Example 2: numSteps=4, badIndex=6. S=10. Full path hits 6.
    // Skip 1: Path 0, 2, 5, 9. Valid. Max: 9.
    System.out.println("Max Sum for (4 steps, bad=6): " + solver.findMaxSum(4, 6));

    // Example 3: numSteps=5, badIndex=13. S=15. No conflict on full path (1, 3, 6, 10, 15).
    // Max: 15.
    System.out.println("Max Sum for (5 steps, bad=13): " + solver.findMaxSum(5, 13));
  }
}
