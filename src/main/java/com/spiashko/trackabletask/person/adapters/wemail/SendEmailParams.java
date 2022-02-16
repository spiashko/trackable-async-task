package com.spiashko.trackabletask.person.adapters.wemail;

import com.spiashko.trackabletask.trackabletask.processor.TrackableTaskParams;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class SendEmailParams extends TrackableTaskParams {

    @NotNull
    private String content;

}
