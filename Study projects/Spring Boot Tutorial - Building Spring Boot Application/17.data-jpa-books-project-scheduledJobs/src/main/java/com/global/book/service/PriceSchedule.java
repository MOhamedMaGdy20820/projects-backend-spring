package com.global.book.service;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceSchedule {

    Logger log = LoggerFactory.getLogger(AutherService.class);

    @Scheduled(fixedDelayString = "${price.interval}")
    // fixedRate run afterr 2 sec // fixedDelay ren after 2 sec from end the method
    @SchedulerLock(name = "bookComputePrice")
    @Async
    public void computePrice() throws InterruptedException {

        Thread.sleep(4000);

        log.info("compute price >> " + LocalDateTime.now());
    }


    @Scheduled(fixedRate = 2000)
    @SchedulerLock(name = "myscheduledTask")
    @Async
    public void computeDiscount() throws InterruptedException {

        Thread.sleep(4000);

        log.info("compute discount >> " + LocalDateTime.now());
    }

}



































