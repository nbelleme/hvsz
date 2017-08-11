package io.nbelleme.hvsz.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DozerConfig {

  /**
   * Define bean Dozer mapper.
   *
   * @return bean
   */
  @Bean(name = "org.dozer.Mapper")
  public DozerBeanMapper dozerBean() {
    return new DozerBeanMapper();
  }
}
