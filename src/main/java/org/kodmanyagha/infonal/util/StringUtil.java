package org.kodmanyagha.infonal.util;

import java.text.DecimalFormat;
import java.util.Arrays;

public class StringUtil {
  public static String intToString(int num, int digits) throws IllegalArgumentException {
    if (num < 0)
      throw new IllegalArgumentException("Num value can be 0 lowest.");
    if (digits <= 0)
      throw new IllegalArgumentException("Digits must be bigger then zero.");
    if (String.valueOf(num).length() > digits)
      throw new IllegalArgumentException("Decimal number count can not be bigger then digit value.");

    // create variable length array of zeros
    char[] zeros = new char[digits];
    Arrays.fill(zeros, '0');
    // format number as String
    DecimalFormat df = new DecimalFormat(String.valueOf(zeros));

    return df.format(num);
  }
}
