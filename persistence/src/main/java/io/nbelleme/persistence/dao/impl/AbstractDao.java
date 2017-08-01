package io.nbelleme.persistence.dao.impl;

import io.nbelleme.persistence.dao.api.AbstractDaoAPI;
import io.nbelleme.persistence.dpo.AbstractDPO;
import io.nbelleme.persistence.exceptions.DaoException;
import org.mongojack.JacksonDBCollection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("deprecation")
public abstract class AbstractDao<DTO, DPO extends AbstractDPO<DTO>> implements AbstractDaoAPI<DTO> {
  private static final String DATABASE_NAME = "hvsz";
  private static final String DATABASE_URL = "http://127.0.0.1:27017";
  private Class<DPO> dpoClass;
  private final JacksonDBCollection<DPO, String> collection;


  /**
   * Constructor.
   *
   * @param collectionName collection of the dao
   */
  @SuppressWarnings("unchecked")
  public AbstractDao(String collectionName) {
    Objects.requireNonNull(collectionName);

//    DBCollection dbCollection = new MongoClient(DATABASE_URL)
//        .getDB(DATABASE_NAME)
//        .getCollection(collectionName);
//    dpoClass = (Class<DPO>) ParameterizedType.class.cast(getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//    collection =
    collection = null;

  }


  @Override
  public DTO save(DTO dto) {
    Objects.requireNonNull(dto);

    try {
      Constructor<DPO> constructor = dpoClass.getConstructor();
      DPO dpo = constructor.newInstance();
      collection.save(dpo);

    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      throw new DaoException(e.getMessage(), e);
    }

    return null;


  }


  @Override
  public Optional<DTO> get() {
    return null;
  }

  @Override
  public DTO update(DTO dto) {
    return null;
  }

  @Override
  public DTO delete(DTO dto) {
    return null;
  }
}
