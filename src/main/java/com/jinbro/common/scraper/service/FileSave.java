package com.jinbro.common.scraper.service;

import com.jinbro.common.scraper.vo.InputHrd;
import com.jinbro.common.scraper.vo.InputHrd2;

public interface FileSave {

  void saveHrdFile(InputHrd input, String outputDir, String delimiter);

  void saveHrd2File(InputHrd2 input, String outputDir, String delimiter);

}
