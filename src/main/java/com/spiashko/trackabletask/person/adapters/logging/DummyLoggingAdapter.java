package com.spiashko.trackabletask.person.adapters.logging;

import com.spiashko.trackabletask.person.appevent.PersonCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
class DummyLoggingAdapter {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void loggingLogic(PersonCreatedEvent event) {
        log.info("person created with name " + event.getPerson().getName());
    }

}
