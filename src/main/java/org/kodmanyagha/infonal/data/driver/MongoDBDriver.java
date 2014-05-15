package org.kodmanyagha.infonal.data.driver;

import java.util.Arrays;
import java.util.List;

import org.kodmanyagha.infonal.data.driver.exception.ConnectionStringParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDBDriver extends DBDriver {
  private static final Logger logger = LoggerFactory.getLogger(MongoDBDriver.class);

  private MongoDBDriverConnectionStringPattern pattern;

  public MongoDBDriver() {
    this(null);
  }

  public MongoDBDriver(String connectionString) {
    logger.debug("--- connectionString: " + connectionString);
    this.connectionString = connectionString;
  }

  /******************************
   * example connection string:
   * 
   * mongodb://db/[dbname]/
   */
  @Override
  public void connect() {
    try {
      checkConnectionStringSyntax();
      parseConnectionString();
    } catch (ConnectionStringParseException | IllegalArgumentException e) {
      logger.error("Exception when setting connection string: " + e);
    }
  }

  @Override
  void checkConnectionStringSyntax() {
    if (connectionString == null)
      throw new IllegalArgumentException("connectionString can not be null");

    List<MongoDBDriverConnectionStringPattern> patterns =
        Arrays.asList(MongoDBDriverConnectionStringPattern.values());
    for (int i = 0; i < patterns.size(); i++) {
      logger.debug("--- pattern: " + patterns.get(i).toString());
    }
    // Pattern pattern = Pattern.compile();
  }

  @Override
  void parseConnectionString() throws ConnectionStringParseException {

  }
}
