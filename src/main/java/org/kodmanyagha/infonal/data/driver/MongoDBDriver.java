package org.kodmanyagha.infonal.data.driver;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.kodmanyagha.infonal.data.driver.exception.ConnectionStringParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDBDriver extends DBDriver {
  private static final Logger logger = LoggerFactory.getLogger(MongoDBDriver.class);

  private MongoDBDriverConnectionStringPattern connectionStringPattern;

  public MongoDBDriver() {
    this(null);
  }

  public MongoDBDriver(String connectionString) {
    logger.debug("--- connectionString: " + connectionString);
    this.connectionString = connectionString;
  }

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
      logger.debug("--- pattern: " + patterns.get(i).getPattern());
      logger.debug("--- connectionString: " + connectionString);
      if (connectionString.matches(patterns.get(i).getPattern())) {
        connectionStringPattern = patterns.get(i);
        return;
      }
    }

    throw new IllegalArgumentException(
        "Connection string does not match any pattern. Please check connection string");
  }

  @Override
  void parseConnectionString() throws ConnectionStringParseException {

  }
}
