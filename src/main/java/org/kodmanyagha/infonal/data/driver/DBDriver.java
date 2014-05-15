package org.kodmanyagha.infonal.data.driver;

import java.util.List;

import org.kodmanyagha.infonal.data.exception.ConnectionStringParseException;
import org.kodmanyagha.infonal.data.exception.DBConnectionException;

public abstract class DBDriver {
  protected String connectionString;
  private boolean connected = false;

  public void setConnectionString(String connectionString) {
    this.connectionString = connectionString;
  }

  public boolean isConnected() {
    return connected;
  }

  public void setConnected(boolean connected) {
    this.connected = connected;
  }

  abstract public void connect() throws DBConnectionException;

  abstract protected void checkConnectionStringSyntax() throws IllegalArgumentException;

  abstract protected void parseConnectionString() throws ConnectionStringParseException;

  abstract public List<DBDataObject> getData();
}
