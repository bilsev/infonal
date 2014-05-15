package org.kodmanyagha.infonal.data.driver.mongodb;

public enum MongoDBDriverConnectionStringPattern {
  // mongodb://127.0.0.1/dbname/collectionname
  PATTERN_ONE("^(mongodb://)([a-zA-Z0-9.]+)/([a-zA-Z]+)/([a-zA-Z]+)"),
  // mongodb://dbname/collectionname
  PATTERN_TWO("^(mongodb://)([a-zA-Z]+)/([a-zA-Z]+)"),
  // mongodb://username@127.0.0.1/password/dbname/collectionname
  PATTERN_THREE("^(mongodb://)([a-zA-Z0-9.]+)/([a-zA-Z]+)/([a-zA-Z]+)"),
  // mongodb://127.0.0.1/username/password/dbname/collectionname
  PATTERN_FOUR("^(mongodb://)([a-zA-Z0-9.]+)/([a-zA-Z]+)/([a-zA-Z]+)");

  private final String pattern;

  MongoDBDriverConnectionStringPattern(String strPattern) {
    this.pattern = strPattern;
  }

  public String getPattern() {
    return pattern;
  }

}
