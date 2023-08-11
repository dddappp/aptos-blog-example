// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.service;

import com.github.wubuku.aptos.bean.Event;
import com.github.wubuku.aptos.utils.NodeApiClient;

import org.test.aptosblogdemo.domain.blog.AbstractBlogEvent;
import org.test.aptosblogdemo.aptos.contract.ContractConstants;
import org.test.aptosblogdemo.aptos.contract.DomainBeanUtils;
import org.test.aptosblogdemo.aptos.contract.AptosAccount;

import org.test.aptosblogdemo.aptos.contract.blog.BlogCreated;
import org.test.aptosblogdemo.aptos.contract.blog.ArticleAddedToBlog;
import org.test.aptosblogdemo.aptos.contract.blog.ArticleRemovedFromBlog;
import org.test.aptosblogdemo.aptos.contract.blog.DonationReceived;
import org.test.aptosblogdemo.aptos.contract.blog.VaultWithdrawn;
import org.test.aptosblogdemo.aptos.contract.blog.BlogUpdated;
import org.test.aptosblogdemo.aptos.contract.blog.BlogDeleted;
import org.test.aptosblogdemo.aptos.contract.repository.BlogEventRepository;
import org.test.aptosblogdemo.aptos.contract.repository.AptosAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.*;
import java.util.*;


@Service
public class BlogEventService {

    public static final java.util.Set<String> DELETION_COMMAND_EVENTS = new java.util.HashSet<>(java.util.Arrays.asList("BlogDeleted"));

    public static boolean isDeletionCommand(String eventType) {
        return DELETION_COMMAND_EVENTS.contains(eventType);
    }

    public static boolean isDeletionCommand(AbstractBlogEvent e) {
        if (isDeletionCommand(e.getEventClass())) {
            return true;
        }
        return false;
    }

    @Value("${aptos.contract.address}")
    private String aptosContractAddress;

    @Autowired
    private AptosAccountRepository aptosAccountRepository;

    @Autowired
    private NodeApiClient aptosNodeApiClient;

    @Autowired
    private BlogEventRepository blogEventRepository;

    @Transactional
    public void updateStatusToProcessed(AbstractBlogEvent event) {
        event.setStatus("D");
        blogEventRepository.save(event);
    }

    @Transactional
    public void pullBlogCreatedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getBlogCreatedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<BlogCreated>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.BLOG_MODULE_EVENTS,
                        ContractConstants.BLOG_MODULE_BLOG_CREATED_HANDLE_FIELD,
                        BlogCreated.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<BlogCreated> eventEnvelope : eventPage) {
                    eventEnvelope.getData().setAccountAddress(resourceAccountAddress);
                    saveBlogCreated(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getBlogCreatedEventNextCursor() {
        AbstractBlogEvent.BlogCreated lastEvent = blogEventRepository.findFirstBlogCreatedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveBlogCreated(Event<BlogCreated> eventEnvelope) {
        AbstractBlogEvent.BlogCreated blogCreated = DomainBeanUtils.toBlogCreated(eventEnvelope);
        if (blogEventRepository.findById(blogCreated.getBlogEventId()).isPresent()) {
            return;
        }
        blogEventRepository.save(blogCreated);
    }

    @Transactional
    public void pullArticleAddedToBlogEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getArticleAddedToBlogEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<ArticleAddedToBlog>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.BLOG_MODULE_EVENTS,
                        ContractConstants.BLOG_MODULE_ARTICLE_ADDED_TO_BLOG_HANDLE_FIELD,
                        ArticleAddedToBlog.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<ArticleAddedToBlog> eventEnvelope : eventPage) {
                    eventEnvelope.getData().setAccountAddress(resourceAccountAddress);
                    saveArticleAddedToBlog(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getArticleAddedToBlogEventNextCursor() {
        AbstractBlogEvent.ArticleAddedToBlog lastEvent = blogEventRepository.findFirstArticleAddedToBlogByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveArticleAddedToBlog(Event<ArticleAddedToBlog> eventEnvelope) {
        AbstractBlogEvent.ArticleAddedToBlog articleAddedToBlog = DomainBeanUtils.toArticleAddedToBlog(eventEnvelope);
        if (blogEventRepository.findById(articleAddedToBlog.getBlogEventId()).isPresent()) {
            return;
        }
        blogEventRepository.save(articleAddedToBlog);
    }

    @Transactional
    public void pullArticleRemovedFromBlogEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getArticleRemovedFromBlogEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<ArticleRemovedFromBlog>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.BLOG_MODULE_EVENTS,
                        ContractConstants.BLOG_MODULE_ARTICLE_REMOVED_FROM_BLOG_HANDLE_FIELD,
                        ArticleRemovedFromBlog.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<ArticleRemovedFromBlog> eventEnvelope : eventPage) {
                    eventEnvelope.getData().setAccountAddress(resourceAccountAddress);
                    saveArticleRemovedFromBlog(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getArticleRemovedFromBlogEventNextCursor() {
        AbstractBlogEvent.ArticleRemovedFromBlog lastEvent = blogEventRepository.findFirstArticleRemovedFromBlogByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveArticleRemovedFromBlog(Event<ArticleRemovedFromBlog> eventEnvelope) {
        AbstractBlogEvent.ArticleRemovedFromBlog articleRemovedFromBlog = DomainBeanUtils.toArticleRemovedFromBlog(eventEnvelope);
        if (blogEventRepository.findById(articleRemovedFromBlog.getBlogEventId()).isPresent()) {
            return;
        }
        blogEventRepository.save(articleRemovedFromBlog);
    }

    @Transactional
    public void pullDonationReceivedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getDonationReceivedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<DonationReceived>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.BLOG_MODULE_EVENTS,
                        ContractConstants.BLOG_MODULE_DONATION_RECEIVED_HANDLE_FIELD,
                        DonationReceived.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<DonationReceived> eventEnvelope : eventPage) {
                    eventEnvelope.getData().setAccountAddress(resourceAccountAddress);
                    saveDonationReceived(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getDonationReceivedEventNextCursor() {
        AbstractBlogEvent.DonationReceived lastEvent = blogEventRepository.findFirstDonationReceivedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveDonationReceived(Event<DonationReceived> eventEnvelope) {
        AbstractBlogEvent.DonationReceived donationReceived = DomainBeanUtils.toDonationReceived(eventEnvelope);
        if (blogEventRepository.findById(donationReceived.getBlogEventId()).isPresent()) {
            return;
        }
        blogEventRepository.save(donationReceived);
    }

    @Transactional
    public void pullVaultWithdrawnEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getVaultWithdrawnEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<VaultWithdrawn>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.BLOG_MODULE_EVENTS,
                        ContractConstants.BLOG_MODULE_VAULT_WITHDRAWN_HANDLE_FIELD,
                        VaultWithdrawn.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<VaultWithdrawn> eventEnvelope : eventPage) {
                    eventEnvelope.getData().setAccountAddress(resourceAccountAddress);
                    saveVaultWithdrawn(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getVaultWithdrawnEventNextCursor() {
        AbstractBlogEvent.VaultWithdrawn lastEvent = blogEventRepository.findFirstVaultWithdrawnByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveVaultWithdrawn(Event<VaultWithdrawn> eventEnvelope) {
        AbstractBlogEvent.VaultWithdrawn vaultWithdrawn = DomainBeanUtils.toVaultWithdrawn(eventEnvelope);
        if (blogEventRepository.findById(vaultWithdrawn.getBlogEventId()).isPresent()) {
            return;
        }
        blogEventRepository.save(vaultWithdrawn);
    }

    @Transactional
    public void pullBlogUpdatedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getBlogUpdatedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<BlogUpdated>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.BLOG_MODULE_EVENTS,
                        ContractConstants.BLOG_MODULE_BLOG_UPDATED_HANDLE_FIELD,
                        BlogUpdated.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<BlogUpdated> eventEnvelope : eventPage) {
                    eventEnvelope.getData().setAccountAddress(resourceAccountAddress);
                    saveBlogUpdated(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getBlogUpdatedEventNextCursor() {
        AbstractBlogEvent.BlogUpdated lastEvent = blogEventRepository.findFirstBlogUpdatedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveBlogUpdated(Event<BlogUpdated> eventEnvelope) {
        AbstractBlogEvent.BlogUpdated blogUpdated = DomainBeanUtils.toBlogUpdated(eventEnvelope);
        if (blogEventRepository.findById(blogUpdated.getBlogEventId()).isPresent()) {
            return;
        }
        blogEventRepository.save(blogUpdated);
    }

    @Transactional
    public void pullBlogDeletedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getBlogDeletedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<BlogDeleted>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.BLOG_MODULE_EVENTS,
                        ContractConstants.BLOG_MODULE_BLOG_DELETED_HANDLE_FIELD,
                        BlogDeleted.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<BlogDeleted> eventEnvelope : eventPage) {
                    eventEnvelope.getData().setAccountAddress(resourceAccountAddress);
                    saveBlogDeleted(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getBlogDeletedEventNextCursor() {
        AbstractBlogEvent.BlogDeleted lastEvent = blogEventRepository.findFirstBlogDeletedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveBlogDeleted(Event<BlogDeleted> eventEnvelope) {
        AbstractBlogEvent.BlogDeleted blogDeleted = DomainBeanUtils.toBlogDeleted(eventEnvelope);
        if (blogEventRepository.findById(blogDeleted.getBlogEventId()).isPresent()) {
            return;
        }
        blogEventRepository.save(blogDeleted);
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }
}
