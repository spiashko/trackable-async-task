package com.spiashko.tle.trackabletask.processors;

import com.spiashko.tle.trackabletask.inputdto.SendEmailParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
class SendEmailTaskProcessor extends TrackableTaskProcessor<SendEmailParams> {

    private final Logger dummyLogger;

    @Override
    public void process(SendEmailParams input) {
        dummyLogger.info("sending email using input: " + input);
    }

    @Override
    public String getName() {
        return "SEND_EMAIL";
    }

}
