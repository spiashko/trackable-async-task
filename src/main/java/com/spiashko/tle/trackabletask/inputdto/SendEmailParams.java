package com.spiashko.tle.trackabletask.inputdto;

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
public class SendEmailParams extends TrackableTaskTypeParams {

    @NotNull
    private String content;

}
