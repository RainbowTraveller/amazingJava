package com.miraie.practice.file;

import java.util.List;

public interface FileSystem {

  public List<String> ls(String path);

  public void mkdir(String path);

  public void addContentToFile(String filePath, String content);

  public String readContentFromFile(String filePath);
}
