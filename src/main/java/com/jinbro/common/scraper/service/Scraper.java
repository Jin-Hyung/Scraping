package com.jinbro.common.scraper.service;

import java.io.IOException;

public interface Scraper {
  /**
   * @param page
   * @param test
   * @param gubun
   * @return
   * @throws IOException
   * @throws InterruptedException
   */
  public boolean scraper(final int page, final boolean test, final String gubun) throws IOException, InterruptedException;
}
