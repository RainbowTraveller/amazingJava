public class SearchString {
	//Find if a String is part of another string (case sensitive)
	public static void main(String[] args) {
		String host			= args[0];
		String candidate	= args[1];
		System.out.println(candidate + " is part of " + host + " : " + SearchString.isExactMatch(host, candidate));
	}

	static boolean isExactMatch(String base, String input) {

		int lBase = base.length();
		int linput = input.length();

		for(int i = 0; i < lBase; ++i) {
			if(base.charAt(i) == input.charAt(0)) {
				int startIndex = i + 1;
				int trackingIndex = 1;
				while(trackingIndex < linput && startIndex < lBase && base.charAt(startIndex) == input.charAt(trackingIndex) ) {
					startIndex++;
					trackingIndex++;
					if(trackingIndex == linput) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
