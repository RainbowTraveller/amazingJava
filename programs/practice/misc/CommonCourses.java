import java.io.*;
import java.util.*;

/*
You are a developer for a university. Your current project is to develop a system for students to find courses they share with friends. The university has a system for querying courses students are enrolled in, returned as a list of (ID, course) pairs.

Write a function that takes in a list of (student ID number, course name) pairs and returns, for every pair of students, a list of all courses they share.

Sample Input:
student_course_pairs = [
    ["58", "Software Design"],
    ["58", "Linear Algebra"],
    ["94", "Art History"],
    ["94", "Operating Systems"],
    ["17", "Software Design"],
    ["58", "Mechanics"],
    ["58", "Economics"],
    ["17", "Linear Algebra"],
    ["17", "Political Science"],
    ["94", "Economics"]
]

Sample Output (pseudocode, in any order)


find_pairs(student_course_pairs) =>
{
    [58, 17]: ["Software Design", "Linear Algebra"]
    [58, 94]: ["Economics"]
    [17, 94]: []
}
*/

class CommonCourses {
  public static void main(String[] args) {

    String[][] studentCoursePairs = {
        {"58", "Software Design"},
        {"58", "Linear Algebra"},
        {"94", "Art History"},
        {"94", "Operating Systems"},
        {"17", "Software Design"},
        {"58", "Mechanics"},
        {"58", "Economics"},
        {"17", "Linear Algebra"},
        {"17", "Political Science"},
        {"94", "Economics"}
    };

    System.out.println(getCoursesForStudent(studentCoursePairs));

    System.out.println(getCommonCourses(getCoursesForStudent(studentCoursePairs)));


  }



  public static Map<String, List<String>> getCoursesForStudent( String[][] studentCoursePairs) {

    Map<String, List<String>> studentCourseTracker = null;

    if(studentCoursePairs != null) {
      studentCourseTracker = new HashMap<String, List<String>>();
      //Create Map of student ids and courses
      for(int i = 0; i < studentCoursePairs.length; ++i) {
        String[] currPair = studentCoursePairs[i];
        String studentId = currPair[0];
        String course = currPair[1];
        List<String> courses = studentCourseTracker.getOrDefault(studentId, new LinkedList<String>());
        courses.add(course);
        studentCourseTracker.put(studentId, courses);
      }
    }
    return studentCourseTracker;
  }

  public static Map<List<String>, List<String>> getCommonCourses( Map<String, List<String>> studentCourseTracker) {

    Map<List<String>, List<String>> commonCourses = null;

    if(studentCourseTracker != null) {
      //Valid map for course tracker exists
      commonCourses  = new HashMap<List<String>, List<String>>();

      //First get the student ids as a list
      //as it will help us iterate over and find unique pairs
      List<String> students = new LinkedList<String>(studentCourseTracker.keySet());

      for(int i = 0; i < students.size(); ++i) {

        String currStudent = students.get(i);

        for(int j = i + 1; j < students.size(); ++j) {

          String nextStudent = students.get(j);

          //Unique pair of the students
          List<String> pair = new LinkedList<String>();
          pair.add(currStudent);
          pair.add(nextStudent);

          //Creating altogether different lists as deep copy
          List<String> currCourses = new LinkedList<String>(studentCourseTracker.get(currStudent));
          List<String> nextCourses = new LinkedList<String>(studentCourseTracker.get(nextStudent));

          //If we just create a reference to the lists from the set, it messes up
          //the answer as the one from the list also gets modified
          //Common courses from 2 lists
          currCourses.retainAll(nextCourses);
          commonCourses.put(pair, currCourses);

          //To be on safer side just set them to null
          currCourses = null;
          nextCourses = null;
        }
      }
    }
    return commonCourses;
  }
}
