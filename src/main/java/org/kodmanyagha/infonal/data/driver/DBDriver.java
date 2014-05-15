package org.kodmanyagha.infonal.data.driver;

import org.kodmanyagha.infonal.data.driver.exception.ConnectionStringParseException;
import org.kodmanyagha.infonal.data.driver.exception.DBConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DBDriver {
  private static final Logger logger = LoggerFactory.getLogger(DBDriver.class);

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

  abstract void checkConnectionStringSyntax() throws IllegalArgumentException;

  abstract void parseConnectionString() throws ConnectionStringParseException;
}
