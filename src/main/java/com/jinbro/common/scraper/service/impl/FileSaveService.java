package com.jinbro.common.scraper.service.impl;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import com.jinbro.common.scraper.service.FileSave;
import com.jinbro.common.scraper.vo.InputHrd;
import com.jinbro.common.scraper.vo.InputHrd2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileSaveService implements FileSave {

  @Override
  public void saveHrdFile(InputHrd input, String outputDir, String delimiter) {
    // TODO Auto-generated method stub
    File file = new File(outputDir, "hrd_out_1.txt");
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));) {
      bw.write(input.toCsvForm(delimiter));
      bw.newLine();
      bw.flush();
    } catch (Exception e) {
      log.error("Unexpected error ocurrs", e);
    }
  }
  @Override
  public void saveHrd2File(InputHrd2 input, String outputDir, String delimiter) {
    // TODO Auto-generated method stub
    File file = new File(outputDir, "hrd_out_2.txt");
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));) {
      bw.write(input.toCsvForm(delimiter));
      bw.newLine();
      bw.flush();
    } catch (Exception e) {
      log.error("Unexpected error ocurrs", e);
    }
  }
  
}