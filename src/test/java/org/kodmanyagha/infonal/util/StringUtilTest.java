package org.kodmanyagha.infonal.util;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {

  public void testIntToStringCorrectValues() {
    assertEquals("00", StringUtil.intToString(0, 2));
    assertEquals("99", StringUtil.intToString(99, 2));
  }

  public void testIntToStringExceptions1() {
    try {
      StringUtil.intToString(999, 2);
      fail("decimal count error");
    } catch (IllegalArgumentException ex) {
      assertTrue(ex.getLocalizedMessage().contains(
          "Decimal number count can not be bigger then digit value"));
    }

    try {
      StringUtil.intToString(-1, 2);
      fail("negative number error");
    } catch (IllegalArgumentException ex) {
      assertTrue(ex.getLocalizedMessage().contains("Num value can be 0 lowest"));
    }

    try {
      StringUtil.intToString(1, -1);
      fail("negative digit error");
    } catch (IllegalArgumentException ex) {
      assertTrue(ex.getLocalizedMessage().contains("Digits must be bigger then zero"));
    }

  }
}
