package io.nbelleme.persistence.dao.impl;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import io.nbelleme.persistence.dao.api.AbstractDaoAPI;

import java.util.Objects;

/**
 * Created by nicolas on 15/07/2017.
 */
@SuppressWarnings("deprecation")
public abstract class AbstractDao implements AbstractDaoAPI {
  private static final String DATABASE_NAME = "hvsz";
  private static final String DATABASE_URL = "http://127.0.0.1:27017";
  private DBCollection collection;

  /**
   * Constructor.
   * @param collectionName collection of the dao
   */
  public AbstractDao(String collectionName) {
    Objects.requireNonNull(collectionName);

    DBCollection dbCollection = new MongoClient(DATABASE_URL)
            .getDB(DATABASE_NAME)
            .getCollection(collectionName);
  }
}
