package org.kodmanyagha.infonal.data.driver.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.kodmanyagha.infonal.data.driver.DBDataObject;
import org.kodmanyagha.infonal.data.driver.DBDriver;
import org.kodmanyagha.infonal.data.exception.ConnectionStringParseException;
import org.kodmanyagha.infonal.data.exception.DBDriverProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBDriver extends DBDriver {
  private static final Logger logger = LoggerFactory.getLogger(MongoDBDriver.class);

  private MongoDBDriverConnectionStringPattern connectionStringPattern;

  private MongoClient mongoClient;
  private DB mongoDb;
  private MongoDBDriverParams driverParams;
  private DBDataObject whereDataObject;

  private String fromTableName;

  public MongoDBDriver(String connectionString) {
    super(connectionString);

    try {
      checkConnectionStringSyntax();
      parseConnectionString();

      logger.debug("--- connection string correct and parsed");
    } catch (ConnectionStringParseException | IllegalArgumentException e) {
      logger.error("--- Exception when setting connection string: " + e);
    }
  }

  @Override
  public void connect() {
    logger.debug("--- drivers param: " + new Gson().toJson(driverParams));

    try {
      mongoClient = new MongoClient(driverParams.getDbHost());
      connected = true;
    } catch (UnknownHostException e) {
      logger.error("Exception when trying to create Mongo Client: " + e);
      connected = false;
    }

    if (connected)
      mongoDb = mongoClient.getDB(driverParams.getDbName());
  }

  private void parseConnectionString() throws ConnectionStringParseException {
    if (connectionStringPattern.equals(MongoDBDriverConnectionStringPattern.PATTERN_ONE))
      driverParams = connectionStringPattern.parseForPatternOne(connectionString);
    else if (connectionStringPattern.equals(MongoDBDriverConnectionStringPattern.PATTERN_TWO))
      driverParams = connectionStringPattern.parseForPatternTwo(connectionString);
    else if (connectionStringPattern.equals(MongoDBDriverConnectionStringPattern.PATTERN_THREE))
      driverParams = connectionStringPattern.parseForPatternThree(connectionString);
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
  public List<DBDataObject> get(String tableName) {
    DBCollection collection = mongoDb.getCollection(tableName);
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

  @Override
  public void select(List<String> fields) {
    // TODO Auto-generated method stub

  }

  @Override
  public void refreshDriver() {
    whereDataObject = null;
    fromTableName = null;
  }


  @Override
  public void from(String tableName) {
    this.fromTableName = tableName;
  }

  @Override
  public List<DBDataObject> get() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void where(DBDataObject dbDataObject) {
    this.whereDataObject = dbDataObject;
  }

  // TODO 16May14 2108 Write test codes for this
  @Override
  public void insert(String collectionName, DBDataObject data) {
    if (data == null)
      throw new IllegalArgumentException("Data can not be null");
    if (collectionName == null || collectionName.trim().length() == 0)
      throw new IllegalArgumentException("Collection name is null or has zero length");

    BasicDBObject basicDBObject = new BasicDBObject();
    Iterator<String> keysetIter = data.getValues().keySet().iterator();
    String key;

    while (keysetIter.hasNext()) {
      key = keysetIter.next();
      basicDBObject.append(key, data.getValues().get(key));
    }
    DBCollection collection = mongoDb.getCollection(collectionName);
    collection.insert(basicDBObject);
  }

  @Override
  public void delete(String tableName, DBDataObject data) {
    BasicDBObject basicDBObject = new BasicDBObject();
    basicDBObject.append("_id", new ObjectId(data.getValues().get("_id").toString()));

    DBCollection collection = mongoDb.getCollection(tableName);
    BulkWriteOperation builder = collection.initializeOrderedBulkOperation();
    builder.find(basicDBObject).removeOne();

    BulkWriteResult result = builder.execute();

    if (result.isAcknowledged()) {
      logger.debug("--- user deleted: " + data.getValues().get("_id"));
    } else {
      logger.debug("--- user can not deleted: " + data.getValues().get("_id"));
    }
  }

  @Override
  public void update(String tableName, DBDataObject data) throws DBDriverProcessException {
    if (whereDataObject == null)
      throw new DBDriverProcessException("You have to call where method with a correct");

    BasicDBObject searchDBObject = new BasicDBObject();
    Iterator<String> whereDataObjValuesIter = whereDataObject.getValues().keySet().iterator();
    String key;
    while (whereDataObjValuesIter.hasNext()) {
      key = whereDataObjValuesIter.next();

      searchDBObject.append(key, new ObjectId(whereDataObject.getValues().get(key).toString()));
    }

    BasicDBObject updatedDBObject = new BasicDBObject();
    updatedDBObject.append("firstname", data.getValues().get("firstname"));
    updatedDBObject.append("lastname", data.getValues().get("lastname"));
    updatedDBObject.append("phone", data.getValues().get("phone"));

    mongoDb.getCollection(tableName).update(searchDBObject, updatedDBObject);
  }
}
