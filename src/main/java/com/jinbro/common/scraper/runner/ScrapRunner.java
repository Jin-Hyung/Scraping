package com.jinbro.common.scraper.runner;


import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.jinbro.common.scraper.service.impl.ScraperService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ScrapRunner implements CommandLineRunner {
  @Value("${service.stPage}")
  private int stPage;

  @Value("${service.edPage}")
  private int edPage;

  @Autowired
  private ScraperService scraperService;

  @Override
  public void run(String... args) throws Exception {
    
    if (args.length <2) {
      System.out.println("Usage : java -jar demo-scraper.jar [Scrap Method name]");
      return;
    }
    
    execute(args[1], false);
  }
  public void execute(final String gubun, final boolean test) throws IOException {
    long stTime = System.nanoTime();
    log.info("Demo Scraper Execute...");
    
    execute(stTime, gubun,test);
  }
  
  private void execute(final long stTime, final String gubun, final boolean test) {
    log.info(gubun +" Scraper Start.");
    for (int i = stPage; i <= edPage; i++) {
      log.info("<" + gubun + "> Start [" + i + "/" + edPage + "] Page");
      try {
        if (!scraperService.scraper(i, test, gubun)) break;
      } catch (IOException | InterruptedException e) {
        log.error("Scraper Error ocuurs : ", e);
      }
      log.info("<" + gubun + "> End [" + i + "/" + edPage + "] Page");

      // 테스트시 1건만 하고 나감
      if (test) break;
    }
    
    long ltime = System.nanoTime() - stTime;
    log.info("[" + gubun + "] Scraper ends. elapsed time : " + ltime / 1000000000.0 + "(s)");
    
  }
}