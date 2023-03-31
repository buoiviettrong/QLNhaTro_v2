package com.Nixagh.Learn.common.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DBAccessor {
  private static MongoTemplate _instance = null;
  private static MongoClient client() {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://19t1021145:FYLpZPIO7kJs5bQC@learnproject.xnjtw4l.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    return MongoClients.create(mongoClientSettings);
  }
  private static MongoTemplate getDBAccessor() { return new MongoTemplate(client(), "LearnProject"); }
  private DBAccessor() {};
  public static MongoTemplate getInstance() {
    if(_instance == null) {
      _instance = getDBAccessor();
    }
    return _instance;
  }
}
