package io.nbelleme.persistence.dao.impl;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import io.nbelleme.persistence.dao.api.AbstractDaoAPI;
import io.nbelleme.persistence.dpo.AbstractDPO;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

@SuppressWarnings("deprecation")
public abstract class AbstractDao<DTO, DPO extends AbstractDPO<DTO>> implements AbstractDaoAPI<DTO> {
  private static final String DATABASE_NAME = "hvsz";
  private static final String DATABASE_URL = "http://127.0.0.1:27017";
  private DBCollection collection;

  /**
   * Constructor.
   *
   * @param collectionName collection of the dao
   */
  public AbstractDao(String collectionName) {
    Objects.requireNonNull(collectionName);

    DBCollection dbCollection = new MongoClient(DATABASE_URL)
            .getDB(DATABASE_NAME)
            .getCollection(collectionName);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void save(DTO dto) {
    Class<DPO> dpoClass = (Class<DPO>) ParameterizedType.class.cast(getClass().getAnnotatedInterfaces()).getActualTypeArguments()[1];
  }


  @Override
  public DTO get() {

    return null;
  }


}
