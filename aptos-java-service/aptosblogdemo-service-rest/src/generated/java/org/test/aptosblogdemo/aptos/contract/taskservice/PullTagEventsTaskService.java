// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.taskservice;

import org.test.aptosblogdemo.aptos.contract.service.TagEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class PullTagEventsTaskService {

    @Value("${aptos.contract.pull-tag-events.tag-created.limit:10}")    
    private Integer pullTagCreatedEventsLimit;

    @Autowired
    private TagEventService tagEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.pull-tag-events.tag-created.fixed-delay:5000}")
    public void pullTagCreatedEvents() {
        tagEventService.pullTagCreatedEvents(pullTagCreatedEventsLimit);
    }

}
