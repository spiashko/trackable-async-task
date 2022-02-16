package com.spiashko.trackabletask.person.adapters.wemail;

import com.spiashko.trackabletask.person.Person;
import com.spiashko.trackabletask.person.appevent.PersonCreatedEvent;
import com.spiashko.trackabletask.trackabletask.TrackableTask;
import com.spiashko.trackabletask.trackabletask.TrackableTaskManagementService;
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
                .type(SendEmailTaskProcessor.PROCESSOR_NAME)
                .params(params)
                .build();

        creationService.create(asyncTask);
    }

}
