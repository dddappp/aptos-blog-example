// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.blog;

import java.util.*;
import java.util.Date;
import java.math.BigInteger;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.*;

public abstract class AbstractBlogAggregate extends AbstractAggregate implements BlogAggregate {
    private BlogState.MutableBlogState state;

    private List<Event> changes = new ArrayList<Event>();

    public AbstractBlogAggregate(BlogState state) {
        this.state = (BlogState.MutableBlogState)state;
    }

    public BlogState getState() {
        return this.state;
    }

    public List<Event> getChanges() {
        return this.changes;
    }

    public void throwOnInvalidStateTransition(Command c) {
        BlogCommand.throwOnInvalidStateTransition(this.state, c);
    }

    protected void apply(Event e) {
        onApplying(e);
        state.mutate(e);
        changes.add(e);
    }


    ////////////////////////

    public static class SimpleBlogAggregate extends AbstractBlogAggregate {
        public SimpleBlogAggregate(BlogState state) {
            super(state);
        }

        @Override
        public void create(String name, Boolean isEmergency, Long offChainVersion, String commandId, String requesterId, BlogCommands.Create c) {
            java.util.function.Supplier<BlogEvent.BlogCreated> eventFactory = () -> newBlogCreated(name, isEmergency, offChainVersion, commandId, requesterId);
            BlogEvent.BlogCreated e;
            try {
                e = verifyCreate(eventFactory, name, isEmergency, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void addArticle(String articleId, Long offChainVersion, String commandId, String requesterId, BlogCommands.AddArticle c) {
            java.util.function.Supplier<BlogEvent.ArticleAddedToBlog> eventFactory = () -> newArticleAddedToBlog(articleId, offChainVersion, commandId, requesterId);
            BlogEvent.ArticleAddedToBlog e;
            try {
                e = verifyAddArticle(eventFactory, articleId, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void removeArticle(String articleId, Long offChainVersion, String commandId, String requesterId, BlogCommands.RemoveArticle c) {
            java.util.function.Supplier<BlogEvent.ArticleRemovedFromBlog> eventFactory = () -> newArticleRemovedFromBlog(articleId, offChainVersion, commandId, requesterId);
            BlogEvent.ArticleRemovedFromBlog e;
            try {
                e = verifyRemoveArticle(eventFactory, articleId, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void initFaVault(String metadata, Long offChainVersion, String commandId, String requesterId, BlogCommands.InitFaVault c) {
            java.util.function.Supplier<BlogEvent.InitFaVaultEvent> eventFactory = () -> newInitFaVaultEvent(metadata, offChainVersion, commandId, requesterId);
            BlogEvent.InitFaVaultEvent e;
            try {
                e = verifyInitFaVault(eventFactory, metadata, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void update(String name, String[] articles, Boolean isEmergency, String faVault, Long offChainVersion, String commandId, String requesterId, BlogCommands.Update c) {
            java.util.function.Supplier<BlogEvent.BlogUpdated> eventFactory = () -> newBlogUpdated(name, articles, isEmergency, faVault, offChainVersion, commandId, requesterId);
            BlogEvent.BlogUpdated e;
            try {
                e = verifyUpdate(eventFactory, name, articles, isEmergency, faVault, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void delete(Long offChainVersion, String commandId, String requesterId, BlogCommands.Delete c) {
            java.util.function.Supplier<BlogEvent.BlogDeleted> eventFactory = () -> newBlogDeleted(offChainVersion, commandId, requesterId);
            BlogEvent.BlogDeleted e;
            try {
                e = verifyDelete(eventFactory, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        protected BlogEvent.BlogCreated verifyCreate(java.util.function.Supplier<BlogEvent.BlogCreated> eventFactory, String name, Boolean isEmergency, BlogCommands.Create c) {
            String Name = name;
            Boolean IsEmergency = isEmergency;

            BlogEvent.BlogCreated e = (BlogEvent.BlogCreated) ApplicationContext.current.get(ICreateLogic.class).verify(
                    eventFactory, getState(), name, isEmergency, VerificationContext.of(c));

            return e;
        }

        protected BlogEvent.ArticleAddedToBlog verifyAddArticle(java.util.function.Supplier<BlogEvent.ArticleAddedToBlog> eventFactory, String articleId, BlogCommands.AddArticle c) {
            String ArticleId = articleId;

            BlogEvent.ArticleAddedToBlog e = (BlogEvent.ArticleAddedToBlog) ApplicationContext.current.get(IAddArticleLogic.class).verify(
                    eventFactory, getState(), articleId, VerificationContext.of(c));

            return e;
        }

        protected BlogEvent.ArticleRemovedFromBlog verifyRemoveArticle(java.util.function.Supplier<BlogEvent.ArticleRemovedFromBlog> eventFactory, String articleId, BlogCommands.RemoveArticle c) {
            String ArticleId = articleId;

            BlogEvent.ArticleRemovedFromBlog e = (BlogEvent.ArticleRemovedFromBlog) ApplicationContext.current.get(IRemoveArticleLogic.class).verify(
                    eventFactory, getState(), articleId, VerificationContext.of(c));

            return e;
        }

        protected BlogEvent.DonationReceived verifyDonate(java.util.function.Supplier<BlogEvent.DonationReceived> eventFactory, BlogCommands.Donate c) {

            BlogEvent.DonationReceived e = (BlogEvent.DonationReceived) ApplicationContext.current.get(IDonateLogic.class).verify(
                    eventFactory, getState(), VerificationContext.of(c));

            return e;
        }

        protected BlogEvent.VaultWithdrawn verifyWithdraw(java.util.function.Supplier<BlogEvent.VaultWithdrawn> eventFactory, BigInteger amount, BlogCommands.Withdraw c) {
            BigInteger Amount = amount;

            BlogEvent.VaultWithdrawn e = (BlogEvent.VaultWithdrawn) ApplicationContext.current.get(IWithdrawLogic.class).verify(
                    eventFactory, getState(), amount, VerificationContext.of(c));

            return e;
        }

        protected BlogEvent.InitFaVaultEvent verifyInitFaVault(java.util.function.Supplier<BlogEvent.InitFaVaultEvent> eventFactory, String metadata, BlogCommands.InitFaVault c) {
            String Metadata = metadata;

            BlogEvent.InitFaVaultEvent e = (BlogEvent.InitFaVaultEvent) ApplicationContext.current.get(IInitFaVaultLogic.class).verify(
                    eventFactory, getState(), metadata, VerificationContext.of(c));

            return e;
        }

        protected BlogEvent.FaDonationReceived verifyDonateFa(java.util.function.Supplier<BlogEvent.FaDonationReceived> eventFactory, BlogCommands.DonateFa c) {

            BlogEvent.FaDonationReceived e = (BlogEvent.FaDonationReceived) ApplicationContext.current.get(IDonateFaLogic.class).verify(
                    eventFactory, getState(), VerificationContext.of(c));

            return e;
        }

        protected BlogEvent.FaVaultWithdrawn verifyWithdrawFa(java.util.function.Supplier<BlogEvent.FaVaultWithdrawn> eventFactory, BigInteger amount, BlogCommands.WithdrawFa c) {
            BigInteger Amount = amount;

            BlogEvent.FaVaultWithdrawn e = (BlogEvent.FaVaultWithdrawn) ApplicationContext.current.get(IWithdrawFaLogic.class).verify(
                    eventFactory, getState(), amount, VerificationContext.of(c));

            return e;
        }

        protected BlogEvent.BlogUpdated verifyUpdate(java.util.function.Supplier<BlogEvent.BlogUpdated> eventFactory, String name, String[] articles, Boolean isEmergency, String faVault, BlogCommands.Update c) {
            String Name = name;
            String[] Articles = articles;
            Boolean IsEmergency = isEmergency;
            String FaVault = faVault;

            BlogEvent.BlogUpdated e = (BlogEvent.BlogUpdated) ApplicationContext.current.get(IUpdateLogic.class).verify(
                    eventFactory, getState(), name, articles, isEmergency, faVault, VerificationContext.of(c));

            return e;
        }

        protected BlogEvent.BlogDeleted verifyDelete(java.util.function.Supplier<BlogEvent.BlogDeleted> eventFactory, BlogCommands.Delete c) {

            BlogEvent.BlogDeleted e = (BlogEvent.BlogDeleted) ApplicationContext.current.get(IDeleteLogic.class).verify(
                    eventFactory, getState(), VerificationContext.of(c));

            return e;
        }

        protected AbstractBlogEvent.BlogCreated newBlogCreated(String name, Boolean isEmergency, Long offChainVersion, String commandId, String requesterId) {
            BlogEventId eventId = new BlogEventId(getState().getAccountAddress(), null);
            AbstractBlogEvent.BlogCreated e = new AbstractBlogEvent.BlogCreated();

            e.setName(name);
            e.setIsEmergency(isEmergency);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setBlogEventId(eventId);
            return e;
        }

        protected AbstractBlogEvent.ArticleAddedToBlog newArticleAddedToBlog(String articleId, Long offChainVersion, String commandId, String requesterId) {
            BlogEventId eventId = new BlogEventId(getState().getAccountAddress(), null);
            AbstractBlogEvent.ArticleAddedToBlog e = new AbstractBlogEvent.ArticleAddedToBlog();

            e.setArticleId(articleId);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setBlogEventId(eventId);
            return e;
        }

        protected AbstractBlogEvent.ArticleRemovedFromBlog newArticleRemovedFromBlog(String articleId, Long offChainVersion, String commandId, String requesterId) {
            BlogEventId eventId = new BlogEventId(getState().getAccountAddress(), null);
            AbstractBlogEvent.ArticleRemovedFromBlog e = new AbstractBlogEvent.ArticleRemovedFromBlog();

            e.setArticleId(articleId);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setBlogEventId(eventId);
            return e;
        }

        protected AbstractBlogEvent.InitFaVaultEvent newInitFaVaultEvent(String metadata, Long offChainVersion, String commandId, String requesterId) {
            BlogEventId eventId = new BlogEventId(getState().getAccountAddress(), null);
            AbstractBlogEvent.InitFaVaultEvent e = new AbstractBlogEvent.InitFaVaultEvent();

            e.setMetadata(metadata);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setBlogEventId(eventId);
            return e;
        }

        protected AbstractBlogEvent.BlogUpdated newBlogUpdated(String name, String[] articles, Boolean isEmergency, String faVault, Long offChainVersion, String commandId, String requesterId) {
            BlogEventId eventId = new BlogEventId(getState().getAccountAddress(), null);
            AbstractBlogEvent.BlogUpdated e = new AbstractBlogEvent.BlogUpdated();

            e.setName(name);
            e.setArticles(articles);
            e.setIsEmergency(isEmergency);
            e.setFaVault(faVault);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setBlogEventId(eventId);
            return e;
        }

        protected AbstractBlogEvent.BlogDeleted newBlogDeleted(Long offChainVersion, String commandId, String requesterId) {
            BlogEventId eventId = new BlogEventId(getState().getAccountAddress(), null);
            AbstractBlogEvent.BlogDeleted e = new AbstractBlogEvent.BlogDeleted();

            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setBlogEventId(eventId);
            return e;
        }

    }

}

