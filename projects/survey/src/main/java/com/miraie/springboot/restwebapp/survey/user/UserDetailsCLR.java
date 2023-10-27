package com.miraie.springboot.restwebapp.survey.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsCLR implements CommandLineRunner {
  private Logger logger = LoggerFactory.getLogger(getClass());

  private UserDetailsRepository repository;

  public UserDetailsCLR(UserDetailsRepository repository) {
    super();
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {

    // Default implementation allows saving by id
    repository.save(new UserDetails("Milo", "Chef"));
    repository.save(new UserDetails("Peter", "YZ"));
    repository.save(new UserDetails("Shyam", "Radya"));

    //    List<UserDetails> userDetails = repository.findAll();
    List<UserDetails> userDetails = repository.findByRole("Chef");
    userDetails.forEach(user -> logger.info(user.toString()));
  }
}
