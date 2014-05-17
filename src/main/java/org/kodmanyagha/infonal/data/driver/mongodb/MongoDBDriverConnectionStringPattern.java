package org.kodmanyagha.infonal.data.driver.mongodb;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public enum MongoDBDriverConnectionStringPattern {
  // mongodb://127.0.0.1/dbname/collectionname
  PATTERN_ONE("^(mongodb://)([a-zA-Z0-9.]+)/([a-zA-Z]+)/([a-zA-Z]+)"),
  // mongodb://hostname/dbname
  PATTERN_TWO("^(mongodb://)([a-zA-Z0-9.]+)/([a-zA-Z]+)"),
  // mongodb://username@127.0.0.1/password/dbname
  PATTERN_THREE("^(mongodb://)([a-zA-Z]+[a-zA-Z0-9.]+@[a-zA-Z0-9.]+)/([a-zA-Z0-9_]+)/([a-zA-Z]+)");

  private final String pattern;

  MongoDBDriverConnectionStringPattern(String strPattern) {
    this.pattern = strPattern;
  }

  public String getPattern() {
    return pattern;
  }

  public void checkConnectionString(String connectionString) throws InvalidParameterException {
    if (connectionString == null)
      throw new InvalidParameterException("connection string can not be null");
    if (connectionString.trim().length() == 0)
      throw new InvalidParameterException("connection string can not be zero length");
  }

  public MongoDBDriverParams parseForPatternOne(String connectionString)
      throws InvalidParameterException {
    checkConnectionString(connectionString);

    if (connectionString.matches(pattern)) {
      List<String> pieces = Arrays.asList(connectionString.split("/"));

      MongoDBDriverParams params = new MongoDBDriverParams();
      params.setDbCollectionName(pieces.get(4));
      params.setDbName(pieces.get(3));
      params.setDbHost(pieces.get(2));

      return params;
    }
    throw new InvalidParameterException("Connection string does not match with pattern");
  }

  public MongoDBDriverParams parseForPatternTwo(String connectionString) {
    checkConnectionString(connectionString);

    if (connectionString.matches(pattern)) {
      List<String> pieces = Arrays.asList(connectionString.split("/"));

      MongoDBDriverParams params = new MongoDBDriverParams();
      params.setDbName(pieces.get(3));
      params.setDbHost(pieces.get(2));

      return params;
    }
    throw new InvalidParameterException("Connection string does not match with pattern");

  }

  public MongoDBDriverParams parseForPatternThree(String connectionString) {
    // TODO Auto-generated method stub
    return null;
  }
}
