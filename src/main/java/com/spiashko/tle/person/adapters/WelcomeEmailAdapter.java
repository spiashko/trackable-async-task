package com.spiashko.tle.person.adapters;

import com.spiashko.tle.person.Person;
import com.spiashko.tle.person.appevent.PersonCreatedEvent;
import com.spiashko.tle.trackabletask.TrackableTask;
import com.spiashko.tle.trackabletask.TrackableTaskManagementService;
import com.spiashko.tle.trackabletask.inputdto.SendEmailParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Slf4j
@Component
class WelcomeEmailAdapter {

    private final TrackableTaskManagementService creationService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void welcomeEmail(PersonCreatedEvent event) {
        Person person = event.getPerson();

        SendEmailParams params = new SendEmailParams()
                .setContent("welcome " + person.getName());

        TrackableTask asyncTask = TrackableTask.builder()
                .type("SEND_EMAIL")
                .params(params)
                .build();

        creationService.create(asyncTask);
    }

}
