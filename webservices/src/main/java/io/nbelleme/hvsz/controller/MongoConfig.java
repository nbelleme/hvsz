package io.nbelleme.hvsz.controller;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by nbelleme on 28/07/2017.
 */
@Configuration
@EnableMongoRepositories("io.nbelleme.persistence")
public class MongoConfig extends AbstractMongoConfiguration {

  //TODO use properties

  @Override
  protected String getDatabaseName() {
    return "hvsz";
  }

  @Override
  public Mongo mongo() throws Exception {
    return new MongoClient("localhost", 27017);
  }
}
