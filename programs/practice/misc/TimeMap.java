/*
 *     Create a timebased key-value store class TimeMap, that supports two operations.
 *
 *        1. set(string key, string value, int timestamp)
 *
 *        Stores the key and value, along with the given timestamp.
 *        2. get(string key, int timestamp)
 *
 *        Returns a value such that set(key, value, timestamp_prev) was called previously,
 *        with timestamp_prev <= timestamp.
 *        If there are multiple such values, it returns the one with the largest timestamp_prev.
 *        If there are no values, it returns the empty string ("").
 */

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

public class TimeMap {
    Map<String, TreeMap<Integer, String>> tracker;

    /** Initialize your data structure here. */
    public TimeMap() {
       tracker = new HashMap<String, TreeMap<Integer, String>>();
    }

    public static void main(String[] args) {
        TimeMap tm = new TimeMap();
        tm.set( "foo","bar",1 );
        System.out.println(tm.get( "foo",0 ));
        System.out.println(tm.get( "foo",3 ));
        tm.set( "foo","bar2",4);
        System.out.println(tm.get( "foo",4 ));
        System.out.println(tm.get( "foo",5 ));
    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> curr = tracker.getOrDefault(key, new TreeMap<Integer, String>());
        curr.put(timestamp, value);
        tracker.put(key, curr);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> curr = tracker.getOrDefault(key, new TreeMap<Integer, String>());
        //System.out.println(tracker);
        //System.out.println(curr);
        if(curr.size() == 0) {
            return "";
        } else {
            Map.Entry<Integer, String> desiredEntry = curr.	floorEntry(timestamp);
            return desiredEntry == null ?  "" : desiredEntry.getValue();
        }
    }
}

