import java.util.List;


/**
 * A third-party API that we're using has a paginated API. It returns results in chunks. This is
 * defined below on as Client.fetchPage()
 *
 * <p>We don't think that API is very useful, and would like to implement a single function that
 * fetches n number of results from fetch page and abstracts away the pagination.
 *
 * <p>Your task will be to implement Fetcher.fetch()
 */



    public class Paginator {
    public static void main(String[] args) {
        Client mockClient = new MockClient();

        Fetcher fetcher1 = new Fetcher(mockClient);
        testCase(1, fetcher1.fetch(5), TestHelpers.makeRange(0, 5));
        testCase(2, fetcher1.fetch(2), TestHelpers.makeRange(5, 7));
        testCase(3, fetcher1.fetch(7), TestHelpers.makeRange(7, 14));
        testCase(4, fetcher1.fetch(103), TestHelpers.makeRange(14, 103));
        testCase(5, fetcher1.fetch(10), TestHelpers.makeRange(0, 0));

        Fetcher fetcher2 = new Fetcher(mockClient);
        testCase(6, fetcher2.fetch(200), TestHelpers.makeRange(0, 103));
    }

    static void testCase(int caseNum, List<Integer> actual, List<Integer> expected) {
        if (actual.containsAll(expected) && expected.containsAll(actual)) {
            System.out.println("Test " + caseNum + " passed");
        } else {
            System.out.println("Test " + caseNum + " failed - expected: " + expected);
            System.out.println("actual: " + actual);
        }
    }
}
