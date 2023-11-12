package com.miraie.practice.file;

import java.util.Map;

/** Class represents a directory in a file system */
public class Directory {

  /*
    Subdirectories in a directory
    It is a map of name of the directory to the content
  */
  Map<String, Directory> dirs;
  /*
  Files inside the directory
  It is a map of the name of the file and the string content
  */
  Map<String, String> files;
}
