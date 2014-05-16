package org.kodmanyagha.infonal.data.driver.mongodb;

import java.util.List;

import org.kodmanyagha.infonal.data.driver.DBDataObject;
import org.kodmanyagha.infonal.data.driver.DBDriver;
import org.kodmanyagha.infonal.data.exception.ConnectionStringParseException;
import org.kodmanyagha.infonal.data.exception.ConnectionStringSyntaxException;

public class MysqlDBDriver extends DBDriver {

  public MysqlDBDriver(String connectionString) {
    super(connectionString);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void connect() {
    // TODO Auto-generated method stub

  }

  @Override
  public void select(List<String> fields) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<DBDataObject> get(String tableName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void refreshDriver() {
    // TODO Auto-generated method stub

  }

  @Override
  public void checkConnectionStringSyntax() throws ConnectionStringSyntaxException {
    // TODO Auto-generated method stub

  }

  @Override
  public void parseConnectionString() throws ConnectionStringParseException {
    // TODO Auto-generated method stub

  }

  @Override
  public void from(String tableName) {
    // TODO Auto-generated method stub
  }

  @Override
  public List<DBDataObject> get() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void where(DBDataObject dbDataObject) {
    // TODO Auto-generated method stub

  }

  @Override
  public void insert(String tableName, DBDataObject data) {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(String tableName, DBDataObject data) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update(String tableName, DBDataObject data) {
    // TODO Auto-generated method stub
    
  }

}
