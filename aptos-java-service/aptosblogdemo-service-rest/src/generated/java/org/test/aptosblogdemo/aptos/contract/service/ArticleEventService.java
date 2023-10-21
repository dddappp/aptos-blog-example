// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.service;

import com.github.wubuku.aptos.bean.Event;
import com.github.wubuku.aptos.utils.NodeApiClient;

import org.test.aptosblogdemo.domain.article.AbstractArticleEvent;
import org.test.aptosblogdemo.aptos.contract.ContractConstants;
import org.test.aptosblogdemo.aptos.contract.DomainBeanUtils;
import org.test.aptosblogdemo.aptos.contract.AptosAccount;

import org.test.aptosblogdemo.aptos.contract.article.ArticleCreated;
import org.test.aptosblogdemo.aptos.contract.article.ArticleUpdated;
import org.test.aptosblogdemo.aptos.contract.article.ArticleDeleted;
import org.test.aptosblogdemo.aptos.contract.article.CommentAdded;
import org.test.aptosblogdemo.aptos.contract.article.CommentUpdated;
import org.test.aptosblogdemo.aptos.contract.article.CommentRemoved;
import org.test.aptosblogdemo.aptos.contract.repository.ArticleEventRepository;
import org.test.aptosblogdemo.aptos.contract.repository.AptosAccountRepository;
import org.test.aptosblogdemo.aptos.contract.repository.CommentTableItemAddedRepository;
import org.test.aptosblogdemo.aptos.contract.CommentTableItemAdded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.*;
import java.util.*;


@Service
public class ArticleEventService {

    public static final java.util.Set<String> DELETION_COMMAND_EVENTS = new java.util.HashSet<>(java.util.Arrays.asList("ArticleDeleted"));

    public static boolean isDeletionCommand(String eventType) {
        return DELETION_COMMAND_EVENTS.contains(eventType);
    }

    public static boolean isDeletionCommand(AbstractArticleEvent e) {
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
    private ArticleEventRepository articleEventRepository;

    @Autowired
    private CommentTableItemAddedRepository commentTableItemAddedRepository;

    @Transactional
    public void updateStatusToProcessed(AbstractArticleEvent event) {
        event.setStatus("D");
        articleEventRepository.save(event);
    }

    @Transactional
    public void pullArticleCreatedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getArticleCreatedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<ArticleCreated>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.ARTICLE_MODULE_EVENTS,
                        ContractConstants.ARTICLE_MODULE_ARTICLE_CREATED_HANDLE_FIELD,
                        ArticleCreated.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<ArticleCreated> eventEnvelope : eventPage) {
                    saveArticleCreated(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getArticleCreatedEventNextCursor() {
        AbstractArticleEvent.ArticleCreated lastEvent = articleEventRepository.findFirstArticleCreatedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveArticleCreated(Event<ArticleCreated> eventEnvelope) {
        AbstractArticleEvent.ArticleCreated articleCreated = DomainBeanUtils.toArticleCreated(eventEnvelope);
        if (articleEventRepository.findById(articleCreated.getArticleEventId()).isPresent()) {
            return;
        }
        articleEventRepository.save(articleCreated);
    }

    @Transactional
    public void pullArticleUpdatedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getArticleUpdatedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<ArticleUpdated>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.ARTICLE_MODULE_EVENTS,
                        ContractConstants.ARTICLE_MODULE_ARTICLE_UPDATED_HANDLE_FIELD,
                        ArticleUpdated.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<ArticleUpdated> eventEnvelope : eventPage) {
                    saveArticleUpdated(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getArticleUpdatedEventNextCursor() {
        AbstractArticleEvent.ArticleUpdated lastEvent = articleEventRepository.findFirstArticleUpdatedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveArticleUpdated(Event<ArticleUpdated> eventEnvelope) {
        AbstractArticleEvent.ArticleUpdated articleUpdated = DomainBeanUtils.toArticleUpdated(eventEnvelope);
        if (articleEventRepository.findById(articleUpdated.getArticleEventId()).isPresent()) {
            return;
        }
        articleEventRepository.save(articleUpdated);
    }

    @Transactional
    public void pullArticleDeletedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getArticleDeletedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<ArticleDeleted>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.ARTICLE_MODULE_EVENTS,
                        ContractConstants.ARTICLE_MODULE_ARTICLE_DELETED_HANDLE_FIELD,
                        ArticleDeleted.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<ArticleDeleted> eventEnvelope : eventPage) {
                    saveArticleDeleted(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getArticleDeletedEventNextCursor() {
        AbstractArticleEvent.ArticleDeleted lastEvent = articleEventRepository.findFirstArticleDeletedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveArticleDeleted(Event<ArticleDeleted> eventEnvelope) {
        AbstractArticleEvent.ArticleDeleted articleDeleted = DomainBeanUtils.toArticleDeleted(eventEnvelope);
        if (articleEventRepository.findById(articleDeleted.getArticleEventId()).isPresent()) {
            return;
        }
        articleEventRepository.save(articleDeleted);
    }

    @Transactional
    public void pullCommentAddedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getCommentAddedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<CommentAdded>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.ARTICLE_MODULE_EVENTS,
                        ContractConstants.ARTICLE_MODULE_COMMENT_ADDED_HANDLE_FIELD,
                        CommentAdded.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<CommentAdded> eventEnvelope : eventPage) {
                    saveCommentAdded(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getCommentAddedEventNextCursor() {
        AbstractArticleEvent.CommentAdded lastEvent = articleEventRepository.findFirstCommentAddedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveCommentAdded(Event<CommentAdded> eventEnvelope) {
        AbstractArticleEvent.CommentAdded commentAdded = DomainBeanUtils.toCommentAdded(eventEnvelope);
        if (articleEventRepository.findById(commentAdded.getArticleEventId()).isPresent()) {
            return;
        }
        articleEventRepository.save(commentAdded);
    }

    @Transactional
    public void pullCommentUpdatedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getCommentUpdatedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<CommentUpdated>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.ARTICLE_MODULE_EVENTS,
                        ContractConstants.ARTICLE_MODULE_COMMENT_UPDATED_HANDLE_FIELD,
                        CommentUpdated.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<CommentUpdated> eventEnvelope : eventPage) {
                    saveCommentUpdated(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getCommentUpdatedEventNextCursor() {
        AbstractArticleEvent.CommentUpdated lastEvent = articleEventRepository.findFirstCommentUpdatedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveCommentUpdated(Event<CommentUpdated> eventEnvelope) {
        AbstractArticleEvent.CommentUpdated commentUpdated = DomainBeanUtils.toCommentUpdated(eventEnvelope);
        if (articleEventRepository.findById(commentUpdated.getArticleEventId()).isPresent()) {
            return;
        }
        articleEventRepository.save(commentUpdated);
    }

    @Transactional
    public void pullCommentRemovedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getCommentRemovedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<CommentRemoved>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.ARTICLE_MODULE_EVENTS,
                        ContractConstants.ARTICLE_MODULE_COMMENT_REMOVED_HANDLE_FIELD,
                        CommentRemoved.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<CommentRemoved> eventEnvelope : eventPage) {
                    saveCommentRemoved(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getCommentRemovedEventNextCursor() {
        AbstractArticleEvent.CommentRemoved lastEvent = articleEventRepository.findFirstCommentRemovedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveCommentRemoved(Event<CommentRemoved> eventEnvelope) {
        AbstractArticleEvent.CommentRemoved commentRemoved = DomainBeanUtils.toCommentRemoved(eventEnvelope);
        if (articleEventRepository.findById(commentRemoved.getArticleEventId()).isPresent()) {
            return;
        }
        articleEventRepository.save(commentRemoved);
    }

    @Transactional
    public void pullCommentTableItemAddedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getCommentTableItemAddedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<CommentTableItemAdded>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.ARTICLE_MODULE_EVENTS,
                        ContractConstants.COMMENT_TABLE_ITEM_ADDED_HANDLE_FIELD,
                        CommentTableItemAdded.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<CommentTableItemAdded> eventEnvelope : eventPage) {
                    saveCommentTableItemAdded(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getCommentTableItemAddedEventNextCursor() {
        org.test.aptosblogdemo.aptos.contract.persistence.CommentTableItemAdded lastEvent = commentTableItemAddedRepository.findFirstByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveCommentTableItemAdded(Event<CommentTableItemAdded> eventEnvelope) {
        org.test.aptosblogdemo.aptos.contract.persistence.CommentTableItemAdded commentTableItemAdded = DomainBeanUtils.toPersistenceCommentTableItemAdded(eventEnvelope);
        if (commentTableItemAddedRepository.findById(commentTableItemAdded.getArticleCommentId()).isPresent()) {
            return;
        }
        commentTableItemAddedRepository.save(commentTableItemAdded);
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }
}
