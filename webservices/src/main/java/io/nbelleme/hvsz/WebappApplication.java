package io.nbelleme.hvsz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = {"io.nbelleme.hvsz", "io.nbelleme.persistence"})
public class WebappApplication {

  /**
   * Webapp application.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    SpringApplication.run(WebappApplication.class, args);
  }

  /**
   * Single threaded executor to execute the asynctask.
   *
   * @return the mono-threaded task scheduler
   */
  @Bean
  public TaskScheduler taskScheduler() {
    return new ConcurrentTaskScheduler();
  }

}
