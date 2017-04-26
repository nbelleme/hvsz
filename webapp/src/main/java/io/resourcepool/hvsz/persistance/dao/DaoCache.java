package io.resourcepool.hvsz.persistance.dao;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class DaoCache {
  private Cache<String, Object> cacheStore = CacheBuilder.newBuilder()
      .maximumSize(100) // Taille Max
      .expireAfterWrite(24, TimeUnit.HOURS) // TTL
      .build();

  public void init() {
    //Creation d'un cache classique avec des String comme clefs et valeurs
    Cache<String, String> cache = CacheBuilder.newBuilder()
        .maximumSize(100) // Taille Max
        .expireAfterWrite(24, TimeUnit.HOURS) // TTL
        .build();

    //CacheBuilder.from();
    String myValue = cache.getIfPresent("myKey_1");
    if (myValue == null) {
      //Calcul de la valeur et mise en cache
      //myValue = computeValue("myKey_1");
      cache.put("myKey_1", myValue);
    }
  }
}
