package com.jinbro.common.scraper.service;

public interface FileSave {

  public void saveFiles(Class<?> clas, Object input, String outputDir,
      String delimiter, String fileName);

}
