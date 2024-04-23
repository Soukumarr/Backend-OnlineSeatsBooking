package com.example.OnlineSeatBook.util;

import com.example.OnlineSeatBook.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {

    @Autowired
    BookingService bookingService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 1000 * 15 )
    public void reportCurrentTime() {
        log.info("The time is now {}", bookingService.updateBookingsOnExpiring());
    }
}
