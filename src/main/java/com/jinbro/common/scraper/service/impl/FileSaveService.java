package com.jinbro.common.scraper.service.impl;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.jinbro.common.scraper.service.FileSave;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileSaveService implements FileSave {

  @Override
  public void saveFiles(Class<?> clas, Object input, String outputDir,
      String delimiter, String fileName) {
    File file = new File(outputDir, fileName);
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));) {
      Method method = clas.getMethod("toCsvForm", new Class[] {String.class});
      bw.write((String) method.invoke(input, delimiter));
      bw.newLine();
      bw.flush();
    } catch (IOException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      log.error("Unexpected error ocurrs", e);
    } catch (NoSuchMethodException | SecurityException e) {
      log.error("NoSuchMethodException | SecurityException error ocurrs", e);
    }
  }

}
