package org.kodmanyagha.infonal.data.driver;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.kodmanyagha.infonal.data.exception.ConnectionStringParseException;
import org.kodmanyagha.infonal.data.exception.ConnectionStringSyntaxException;
import org.kodmanyagha.infonal.data.exception.DBConnectionException;
import org.kodmanyagha.infonal.data.exception.DBDriverProcessException;

public abstract class DBDriver {
  protected String connectionString;
  protected boolean connected = false;

  public DBDriver(String connectionString) {
    this.connectionString = connectionString;
  }

  public void setConnectionString(String connectionString) {
    this.connectionString = connectionString;
  }

  public boolean isConnected() {
    return connected;
  }

  public void select(String[] fields) {
    select(Arrays.asList(fields));
  }

  /***********************************************************************************
   ******************************** ABSTRACT METHODS *********************************
   ***********************************************************************************/

  /***************************
   * 
   * @throws ConnectionStringSyntaxException
   */
  abstract public void checkConnectionStringSyntax() throws ConnectionStringSyntaxException;

  /********************************************
   * Connect to backend database or a data source.
   * 
   * @throws DBConnectionException
   * @throws UnknownHostException
   */
  abstract public void connect() throws DBConnectionException;

  /*********************************************************************************************
   * Select the fields of a table. You must call this method bedore get(tableName) method
   * 
   * @param fields
   */
  abstract public void select(List<String> fields);

  /************************************************
   * Get all data of a table
   * 
   * @param tableName
   * @return
   */
  abstract public List<DBDataObject> get(String tableName) throws DBDriverProcessException;

  /**********************************************
   * 
   * @param tableName
   * @return
   */
  abstract public void from(String tableName);

  /*****************************************************
   * Get data from a table. You have to call dbDriver.from(tableName) method before call this.
   * 
   * @return
   */
  abstract public List<DBDataObject> get() throws DBDriverProcessException;

  /********************************************************
   * Clear last process results and settings.
   */
  abstract public void refreshDriver();

  /*****************************************************
   * Example usage:
   * dbDriver.select(Arrays.asList({"*", "id", "username", "firstname"}));
   * 
   * DBDataObject dbDataObject = new DBDataObject();
   * dbDataObject.getValues.put("firstname", "emir%");
   * 
   * dbDriver.where(dbDataObject);
   * dbDriver.from("userstable");
   * 
   * List<DBDataObject> dataObjects = dbDriver.get();
   * 
   * @param dbDataObject
   */
  abstract public void where(DBDataObject dbDataObject);

  /**********************************************************
   * 
   * 
   * @param tableName
   * @param data
   */
  abstract public void insert(String tableName, DBDataObject data) throws DBDriverProcessException;

  /**********************************************************
   * 
   * 
   * @param tableName
   * @param data
   * @throws DBDriverProcessException 
   */
  abstract public void update(String tableName, DBDataObject data) throws DBDriverProcessException;

  /*********************************************************
   * 
   * @param tableName
   * @param data
   */
  abstract public void delete(String tableName, DBDataObject data) throws DBDriverProcessException;
}
