package org.kodmanyagha.infonal.data.driver.mongodb;

import java.security.InvalidParameterException;

import junit.framework.TestCase;

public class MongoDBDriverConnectionStringPatternTest extends TestCase {

  public void testParseParams1() {
    String correctConnectionString = "mongodb://127.0.0.1/dbname/collectionname";
    MongoDBDriverConnectionStringPattern connectionStringPattern =
        MongoDBDriverConnectionStringPattern.PATTERN_ONE;

    try {
      connectionStringPattern.parseForPatternOne(null);
      fail("null parameter can not be accept");
    } catch (InvalidParameterException ex) {
    }

    try {
      connectionStringPattern.parseForPatternOne("aergaergaerg");
      fail("wrong parameter can not be accept");
    } catch (InvalidParameterException ex) {
    }

    MongoDBDriverParams params =
        connectionStringPattern.parseForPatternOne(correctConnectionString);
    assertEquals(params.getDbHost(), "127.0.0.1");
    assertEquals(params.getDbName(), "dbname");
    assertEquals(params.getDbCollectionName(), "collectionname");
  }

  public void testParseParams2() {
    String correctConnectionString = "mongodb://127.0.0.1/dbname";
    MongoDBDriverConnectionStringPattern connectionStringPattern =
        MongoDBDriverConnectionStringPattern.PATTERN_TWO;

    try {
      connectionStringPattern.parseForPatternTwo(null);
      fail("null parameter can not be accept");
    } catch (InvalidParameterException ex) {
    }

    try {
      connectionStringPattern.parseForPatternTwo("aergaergaerg");
      fail("wrong parameter can not be accept");
    } catch (InvalidParameterException ex) {
    }

    MongoDBDriverParams params =
        connectionStringPattern.parseForPatternTwo(correctConnectionString);
    assertEquals(params.getDbHost(), "127.0.0.1");
    assertEquals(params.getDbName(), "dbname");
    assertEquals(params.getDbCollectionName(), null);
  }

}
