package br.com.andreluisgomes.messaging.annotations.scheduled;

import br.com.andreluisgomes.messaging.annotations.endpoint.gateway.FullName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

/**
 * Created by agomes on 26/07/16.
 */
@Component
public class MySchedule {

  @Autowired
  private FullName fullName;

  @Scheduled(fixedRate = 1000)
  public void schedule() throws InterruptedException, ExecutionException, TimeoutException {
    System.out.println("Start Schedule");
    Future<String> future = fullName.print("André");

    //String printed = future.get(5000, TimeUnit.MILLISECONDS);

    //System.out.println(printed);
  }
}
