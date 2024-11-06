// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.article;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.*;

public abstract class AbstractArticleAggregate extends AbstractAggregate implements ArticleAggregate {
    private ArticleState.MutableArticleState state;

    private List<Event> changes = new ArrayList<Event>();

    public AbstractArticleAggregate(ArticleState state) {
        this.state = (ArticleState.MutableArticleState)state;
    }

    public ArticleState getState() {
        return this.state;
    }

    public List<Event> getChanges() {
        return this.changes;
    }

    public void throwOnInvalidStateTransition(Command c) {
        ArticleCommand.throwOnInvalidStateTransition(this.state, c);
    }

    protected void apply(Event e) {
        onApplying(e);
        state.mutate(e);
        changes.add(e);
    }


    ////////////////////////

    public static class SimpleArticleAggregate extends AbstractArticleAggregate {
        public SimpleArticleAggregate(ArticleState state) {
            super(state);
        }

        @Override
        public void addTag(String tag, Long offChainVersion, String commandId, String requesterId, ArticleCommands.AddTag c) {
            java.util.function.Supplier<ArticleEvent.AddTagEvent> eventFactory = () -> newAddTagEvent(tag, offChainVersion, commandId, requesterId);
            ArticleEvent.AddTagEvent e;
            try {
                e = verifyAddTag(eventFactory, tag, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void create(String title, String body, String owner, Long offChainVersion, String commandId, String requesterId, ArticleCommands.Create c) {
            java.util.function.Supplier<ArticleEvent.ArticleCreated> eventFactory = () -> newArticleCreated(title, body, owner, offChainVersion, commandId, requesterId);
            ArticleEvent.ArticleCreated e;
            try {
                e = verifyCreate(eventFactory, title, body, owner, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void update(String title, String body, String owner, String[] tags, Long offChainVersion, String commandId, String requesterId, ArticleCommands.Update c) {
            java.util.function.Supplier<ArticleEvent.ArticleUpdated> eventFactory = () -> newArticleUpdated(title, body, owner, tags, offChainVersion, commandId, requesterId);
            ArticleEvent.ArticleUpdated e;
            try {
                e = verifyUpdate(eventFactory, title, body, owner, tags, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void delete(Long offChainVersion, String commandId, String requesterId, ArticleCommands.Delete c) {
            java.util.function.Supplier<ArticleEvent.ArticleDeleted> eventFactory = () -> newArticleDeleted(offChainVersion, commandId, requesterId);
            ArticleEvent.ArticleDeleted e;
            try {
                e = verifyDelete(eventFactory, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void addComment(String commenter, String body, String owner, Long offChainVersion, String commandId, String requesterId, ArticleCommands.AddComment c) {
            java.util.function.Supplier<ArticleEvent.CommentAdded> eventFactory = () -> newCommentAdded(commenter, body, owner, offChainVersion, commandId, requesterId);
            ArticleEvent.CommentAdded e;
            try {
                e = verifyAddComment(eventFactory, commenter, body, owner, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void updateComment(BigInteger commentSeqId, String commenter, String body, String owner, Long offChainVersion, String commandId, String requesterId, ArticleCommands.UpdateComment c) {
            java.util.function.Supplier<ArticleEvent.CommentUpdated> eventFactory = () -> newCommentUpdated(commentSeqId, commenter, body, owner, offChainVersion, commandId, requesterId);
            ArticleEvent.CommentUpdated e;
            try {
                e = verifyUpdateComment(eventFactory, commentSeqId, commenter, body, owner, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void removeComment(BigInteger commentSeqId, Long offChainVersion, String commandId, String requesterId, ArticleCommands.RemoveComment c) {
            java.util.function.Supplier<ArticleEvent.CommentRemoved> eventFactory = () -> newCommentRemoved(commentSeqId, offChainVersion, commandId, requesterId);
            ArticleEvent.CommentRemoved e;
            try {
                e = verifyRemoveComment(eventFactory, commentSeqId, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        protected ArticleEvent.AddTagEvent verifyAddTag(java.util.function.Supplier<ArticleEvent.AddTagEvent> eventFactory, String tag, ArticleCommands.AddTag c) {
            String Tag = tag;

            ArticleEvent.AddTagEvent e = (ArticleEvent.AddTagEvent) ApplicationContext.current.get(IAddTagLogic.class).verify(
                    eventFactory, getState(), tag, VerificationContext.of(c));

            return e;
        }

        protected ArticleEvent.ArticleCreated verifyCreate(java.util.function.Supplier<ArticleEvent.ArticleCreated> eventFactory, String title, String body, String owner, ArticleCommands.Create c) {
            String Title = title;
            String Body = body;
            String Owner = owner;

            ArticleEvent.ArticleCreated e = (ArticleEvent.ArticleCreated) ApplicationContext.current.get(ICreateLogic.class).verify(
                    eventFactory, getState(), title, body, owner, VerificationContext.of(c));

            return e;
        }

        protected ArticleEvent.ArticleUpdated verifyUpdate(java.util.function.Supplier<ArticleEvent.ArticleUpdated> eventFactory, String title, String body, String owner, String[] tags, ArticleCommands.Update c) {
            String Title = title;
            String Body = body;
            String Owner = owner;
            String[] Tags = tags;

            ArticleEvent.ArticleUpdated e = (ArticleEvent.ArticleUpdated) ApplicationContext.current.get(IUpdateLogic.class).verify(
                    eventFactory, getState(), title, body, owner, tags, VerificationContext.of(c));

            return e;
        }

        protected ArticleEvent.ArticleDeleted verifyDelete(java.util.function.Supplier<ArticleEvent.ArticleDeleted> eventFactory, ArticleCommands.Delete c) {

            ArticleEvent.ArticleDeleted e = (ArticleEvent.ArticleDeleted) ApplicationContext.current.get(IDeleteLogic.class).verify(
                    eventFactory, getState(), VerificationContext.of(c));

            return e;
        }

        protected ArticleEvent.CommentAdded verifyAddComment(java.util.function.Supplier<ArticleEvent.CommentAdded> eventFactory, String commenter, String body, String owner, ArticleCommands.AddComment c) {
            String Commenter = commenter;
            String Body = body;
            String Owner = owner;

            ArticleEvent.CommentAdded e = (ArticleEvent.CommentAdded) ApplicationContext.current.get(IAddCommentLogic.class).verify(
                    eventFactory, getState(), commenter, body, owner, VerificationContext.of(c));

            return e;
        }

        protected ArticleEvent.CommentUpdated verifyUpdateComment(java.util.function.Supplier<ArticleEvent.CommentUpdated> eventFactory, BigInteger commentSeqId, String commenter, String body, String owner, ArticleCommands.UpdateComment c) {
            BigInteger CommentSeqId = commentSeqId;
            String Commenter = commenter;
            String Body = body;
            String Owner = owner;

            ArticleEvent.CommentUpdated e = (ArticleEvent.CommentUpdated) ApplicationContext.current.get(IUpdateCommentLogic.class).verify(
                    eventFactory, getState(), commentSeqId, commenter, body, owner, VerificationContext.of(c));

            return e;
        }

        protected ArticleEvent.CommentRemoved verifyRemoveComment(java.util.function.Supplier<ArticleEvent.CommentRemoved> eventFactory, BigInteger commentSeqId, ArticleCommands.RemoveComment c) {
            BigInteger CommentSeqId = commentSeqId;

            ArticleEvent.CommentRemoved e = (ArticleEvent.CommentRemoved) ApplicationContext.current.get(IRemoveCommentLogic.class).verify(
                    eventFactory, getState(), commentSeqId, VerificationContext.of(c));

            return e;
        }

        protected AbstractArticleEvent.AddTagEvent newAddTagEvent(String tag, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.AddTagEvent e = new AbstractArticleEvent.AddTagEvent();

            e.setTag(tag);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.ArticleCreated newArticleCreated(String title, String body, String owner, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.ArticleCreated e = new AbstractArticleEvent.ArticleCreated();

            e.setTitle(title);
            e.setBody(body);
            e.setOwner(owner);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.ArticleUpdated newArticleUpdated(String title, String body, String owner, String[] tags, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.ArticleUpdated e = new AbstractArticleEvent.ArticleUpdated();

            e.setTitle(title);
            e.setBody(body);
            e.setOwner(owner);
            e.setTags(tags);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.ArticleDeleted newArticleDeleted(Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.ArticleDeleted e = new AbstractArticleEvent.ArticleDeleted();

            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.CommentAdded newCommentAdded(String commenter, String body, String owner, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.CommentAdded e = new AbstractArticleEvent.CommentAdded();

            e.setCommentSeqId(null);
            e.setCommenter(commenter);
            e.setBody(body);
            e.setOwner(owner);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.CommentUpdated newCommentUpdated(BigInteger commentSeqId, String commenter, String body, String owner, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.CommentUpdated e = new AbstractArticleEvent.CommentUpdated();

            e.setCommentSeqId(commentSeqId);
            e.setCommenter(commenter);
            e.setBody(body);
            e.setOwner(owner);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

        protected AbstractArticleEvent.CommentRemoved newCommentRemoved(BigInteger commentSeqId, Long offChainVersion, String commandId, String requesterId) {
            ArticleEventId eventId = new ArticleEventId(getState().getId(), null);
            AbstractArticleEvent.CommentRemoved e = new AbstractArticleEvent.CommentRemoved();

            e.setCommentSeqId(commentSeqId);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setArticleEventId(eventId);
            return e;
        }

    }

}

