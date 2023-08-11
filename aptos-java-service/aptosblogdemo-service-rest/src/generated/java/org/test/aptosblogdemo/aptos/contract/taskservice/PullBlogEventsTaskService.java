// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.taskservice;

import org.test.aptosblogdemo.aptos.contract.service.BlogEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PullBlogEventsTaskService {

    @Autowired
    private BlogEventService blogEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.pull-blog-events.blog-created.fixed-delay:5000}")
    public void pullBlogCreatedEvents() {
        blogEventService.pullBlogCreatedEvents();
    }

    @Scheduled(fixedDelayString = "${aptos.contract.pull-blog-events.article-added-to-blog.fixed-delay:5000}")
    public void pullArticleAddedToBlogEvents() {
        blogEventService.pullArticleAddedToBlogEvents();
    }

    @Scheduled(fixedDelayString = "${aptos.contract.pull-blog-events.article-removed-from-blog.fixed-delay:5000}")
    public void pullArticleRemovedFromBlogEvents() {
        blogEventService.pullArticleRemovedFromBlogEvents();
    }

    @Scheduled(fixedDelayString = "${aptos.contract.pull-blog-events.donation-received.fixed-delay:5000}")
    public void pullDonationReceivedEvents() {
        blogEventService.pullDonationReceivedEvents();
    }

    @Scheduled(fixedDelayString = "${aptos.contract.pull-blog-events.vault-withdrawn.fixed-delay:5000}")
    public void pullVaultWithdrawnEvents() {
        blogEventService.pullVaultWithdrawnEvents();
    }

    @Scheduled(fixedDelayString = "${aptos.contract.pull-blog-events.blog-updated.fixed-delay:5000}")
    public void pullBlogUpdatedEvents() {
        blogEventService.pullBlogUpdatedEvents();
    }

    @Scheduled(fixedDelayString = "${aptos.contract.pull-blog-events.blog-deleted.fixed-delay:5000}")
    public void pullBlogDeletedEvents() {
        blogEventService.pullBlogDeletedEvents();
    }

}