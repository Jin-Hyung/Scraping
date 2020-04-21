package com.jinbro.common.scraper.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class InputHrd2 {

  private String seq;
  private String entrnm;
  private String type;
  private String centernm;
  private String btpnm;
  private String tel;
  private String addr;
  
  public String toCsvForm(String delimiter) {
    List<String> t = new ArrayList<>(
        Arrays.asList(seq, entrnm, type, centernm, btpnm, tel, addr));
    return StringUtils.join(t, delimiter);
  }
}