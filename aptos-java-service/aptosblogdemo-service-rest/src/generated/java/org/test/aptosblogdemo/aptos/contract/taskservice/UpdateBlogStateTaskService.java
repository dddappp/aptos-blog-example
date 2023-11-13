// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.taskservice;

import org.test.aptosblogdemo.aptos.contract.repository.*;
import org.test.aptosblogdemo.aptos.contract.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateBlogStateTaskService {

    @Autowired
    private AptosBlogService aptosBlogService;

    @Autowired
    private BlogEventRepository blogEventRepository;

    @Autowired
    private BlogEventService blogEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.update-blog-states.fixed-delay:5000}")
    @Transactional
    public void updateBlogStates() {
        blogEventRepository.findByStatusIsNull().forEach(e -> {
            if (BlogEventService.isDeletionCommand(e)) {
                aptosBlogService.deleteBlog(e.getAccountAddress());
            } else {
                aptosBlogService.updateBlogState(e.getAccountAddress());
            }
            blogEventService.updateStatusToProcessed(e);
        });
    }

}
