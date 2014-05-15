package org.kodmanyagha.infonal.data.driver.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.kodmanyagha.infonal.data.driver.DBDataObject;
import org.kodmanyagha.infonal.data.driver.DBDriver;
import org.kodmanyagha.infonal.data.exception.ConnectionStringParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBDriver extends DBDriver {
  private static final Logger logger = LoggerFactory.getLogger(MongoDBDriver.class);

  private MongoDBDriverConnectionStringPattern connectionStringPattern;

  private String dbHost;
  private String dbPort;
  private String dbName;
  private String dbCollectionName;
  private String dbUsername;
  private String dbPassword;

  private MongoClient mongoClient;
  private DB mongoDb;

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

    try {
      mongoClient = new MongoClient(dbHost);
      mongoDb = mongoClient.getDB(dbName);

    } catch (UnknownHostException e) {
      logger.error("Exception when trying to create Mongo Client: " + e);
    }
  }

  @Override
  public void checkConnectionStringSyntax() {
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
  protected void parseConnectionString() throws ConnectionStringParseException {
    // TODO 15May14 2138 Parse connection string in here

    dbHost = "localhost";
    dbName = "test";
    dbCollectionName = "testData";
    dbUsername = "root";
    dbPassword = "123456";
  }

  @Override
  public List<DBDataObject> getData() {
    DBCollection collection = mongoDb.getCollection(dbCollectionName);
    DBCursor cursor = collection.find();
    List<DBDataObject> dbDataObjects = new ArrayList<>();

    DBObject dbObject;

    Iterator<String> keys;
    String key;
    DBDataObject dbDataObject;
    while (cursor.hasNext()) {
      dbObject = cursor.next();

      keys = dbObject.keySet().iterator();
      dbDataObject = new DBDataObject();

      while (keys.hasNext()) {
        key = keys.next();

        dbDataObject.getValues().put(key, dbObject.get(key));
      }

      dbDataObjects.add(dbDataObject);
    }

    return dbDataObjects;
  }
}
