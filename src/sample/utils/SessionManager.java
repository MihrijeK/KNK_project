package sample.utils;


import Helpers.LangEnum;
import Helpers.Staff;

import java.util.Locale;

public class SessionManager {
  private static LangEnum lang;

  public static Locale getLocale(){
    Locale locale = lang == LangEnum.AL ? new Locale("al", "AL") : new Locale("en", "EN");
    return locale;
  }

  public static void setLang(LangEnum lang) {
    SessionManager.lang = lang;
  }
}
