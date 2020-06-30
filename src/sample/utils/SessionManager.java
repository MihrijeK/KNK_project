package sample.utils;


import Helpers.LangEnum;
import Helpers.Staff;

import java.util.Locale;

public class SessionManager {
  public static Staff user;

  public static Locale getLocale() {
    LangEnum lang = LangEnum.AL;
    Locale locale = lang == LangEnum.AL ? new Locale("al", "AL") : new Locale("en", "EN");
    return locale;
  }
}
