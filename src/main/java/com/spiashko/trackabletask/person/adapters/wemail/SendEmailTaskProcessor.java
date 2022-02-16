package com.spiashko.trackabletask.person.adapters.wemail;

import com.spiashko.trackabletask.trackabletask.processor.TrackableTaskProcessor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
class SendEmailTaskProcessor extends TrackableTaskProcessor<SendEmailParams> {

    public static final String PROCESSOR_NAME = "SEND_EMAIL";

    private final Logger dummyLogger;

    @SneakyThrows
    @Override
    public void process(SendEmailParams input) {
        dummyLogger.info("sending email using input: " + input);
        Thread.sleep(30_000L);
        dummyLogger.info("email is sent using input: " + input);
    }

    @Override
    public String getName() {
        return PROCESSOR_NAME;
    }

}
