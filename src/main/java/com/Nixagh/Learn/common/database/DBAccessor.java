package com.Nixagh.Learn.common.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBAccessor {
  private static MongoTemplate _instance = null;
  private static String _connectionString;
  private static String _databaseName;

  private DBAccessor() {
  }

  private static MongoTemplate getDBAccessor() {
    ConnectionString connectionString = new ConnectionString(_connectionString);
    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(mongoClientSettings);
    return new MongoTemplate(mongoClient, _databaseName);
  }

  public static MongoTemplate getInstance() {
    if (_instance == null) {
      _instance = getDBAccessor();
    }
    return _instance;
  }

  @Value("${spring.data.mongodb.uri}")
  private void setConnectionString(String connectionString) {
    _connectionString = connectionString;
  }

  @Value("${spring.data.mongodb.database}")
  private void setDatabaseName(String databaseName) {
    _databaseName = databaseName;
  }
}
