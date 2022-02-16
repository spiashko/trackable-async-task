package com.spiashko.trackabletask.web.rest;

import com.spiashko.trackabletask.trackabletask.TrackableTask;
import com.spiashko.trackabletask.trackabletask.TrackableTaskSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trackable-tasks")
public class TrackableTaskRestController {

    private final TrackableTaskSearchService searchService;

    @GetMapping
    public List<TrackableTask> findAll() {
        List<TrackableTask> result = searchService.findAll();
        return result;
    }

}
