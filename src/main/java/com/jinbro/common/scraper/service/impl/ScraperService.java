package com.jinbro.common.scraper.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.jinbro.common.scraper.service.Scraper;
import com.jinbro.common.scraper.vo.InputHrd;
import com.jinbro.common.scraper.vo.InputHrd2;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ScraperService implements Scraper {
  @Value("${service.LINE_CNT}")
  private int LINE_CNT;

  @Value("${service.delimiter}")
  private String delimiter;
  
  @Value("${service.outputDir}")
  private String outputDir;
  
  @Value("${service.sleepCnt}")
  private int sleepCnt;
  
  public boolean scraper(final int page, final boolean test, final String gubun) throws IOException, InterruptedException  {
    if("Hrd".equals(gubun)) {
      hrdScraper(page);
    } else if("Hrd2".equals(gubun)) {
      hrdScraper2(page);
    } else {
      log.info("Please check your target : [" + gubun + "]");
      return false;
    }
    return true;
  }
  
  private void hrdScraper(int page) throws IOException, InterruptedException  {
    FileSaveService fileSave = new FileSaveService();
    Map<String, String> params = new HashMap<>();
    params.put("parreqTy", "S");
    params.put("pageIndex", String.valueOf(page));
    params.put("searchKey", "0");
    params.put("pageRow", "10");
    
    String url = "http://www.hrd.go.kr/hrdp/gi/pgieo/PGIEO0410T.do";
    Document doc = Jsoup.connect(url)
        .header("Content-Type", "application/x-www-form-urlencoded")
        .header("Accept-Encoding", "gzip, deflate")
        .data(params).timeout(60000).post();
    
    Elements elements = doc.select("div.content tr.tac");
    for (Element e : elements) {
      InputHrd input = new InputHrd();
      input.setSeq(e.getAllElements().get(0).getElementsByTag("td").get(0).text());
      input.setEntrnm(e.getAllElements().get(0).getElementsByTag("td").get(1).text());
      input.setType(e.getAllElements().get(0).getElementsByTag("td").get(2).text());
      input.setBtpnm(e.getAllElements().get(0).getElementsByTag("td").get(3).text());
      input.setAddr(e.getAllElements().get(0).getElementsByTag("td").get(4).text());
      fileSave.saveHrdFile(input, outputDir, delimiter);
      log.info(input.toCsvForm(delimiter));
    }
    Thread.sleep(sleepCnt);
  }
  private void hrdScraper2(int page) throws IOException, InterruptedException  {
    FileSaveService fileSave = new FileSaveService();
    Map<String, String> params = new HashMap<>();
    params.put("parreqTy", "S");
    params.put("pageIndex", String.valueOf(page));
    params.put("searchKey", "0");
    params.put("pageRow", "10");
    
    String url = "http://www.hrd.go.kr/hrdp/gi/pgieo/PGIEO0470T.do";
    Document doc = Jsoup.connect(url)
        .header("Content-Type", "application/x-www-form-urlencoded")
        .header("Accept-Encoding", "gzip, deflate")
        .data(params).timeout(60000).post();
    
    Elements elements = doc.select("div.content tr.tac");
    for (Element e : elements) {
      InputHrd2 input = new InputHrd2();
      input.setSeq(e.getAllElements().get(0).getElementsByTag("td").get(0).text());
      input.setEntrnm(e.getAllElements().get(0).getElementsByTag("td").get(1).text());
      input.setType(e.getAllElements().get(0).getElementsByTag("td").get(2).text());
      input.setCenternm(e.getAllElements().get(0).getElementsByTag("td").get(3).text());
      input.setBtpnm(e.getAllElements().get(0).getElementsByTag("td").get(4).text());
      input.setTel(e.getAllElements().get(0).getElementsByTag("td").get(5).text());
      input.setAddr(e.getAllElements().get(0).getElementsByTag("td").get(6).text());
      fileSave.saveHrd2File(input, outputDir, delimiter);
      log.info(input.toCsvForm(delimiter));
    }
    Thread.sleep(sleepCnt);
  }
}