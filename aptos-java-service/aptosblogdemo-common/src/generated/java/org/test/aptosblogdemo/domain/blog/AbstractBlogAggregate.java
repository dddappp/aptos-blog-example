// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.blog;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
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
        public void addArticle(BigInteger articleId, Long offChainVersion, String commandId, String requesterId, BlogCommands.AddArticle c) {
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
        public void removeArticle(BigInteger articleId, Long offChainVersion, String commandId, String requesterId, BlogCommands.RemoveArticle c) {
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
        public void update(String name, BigInteger[] articles, Boolean isEmergency, Long offChainVersion, String commandId, String requesterId, BlogCommands.Update c) {
            java.util.function.Supplier<BlogEvent.BlogUpdated> eventFactory = () -> newBlogUpdated(name, articles, isEmergency, offChainVersion, commandId, requesterId);
            BlogEvent.BlogUpdated e;
            try {
                e = verifyUpdate(eventFactory, name, articles, isEmergency, c);
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

            BlogEvent.BlogCreated e = (BlogEvent.BlogCreated) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.blog.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, BlogState.class, String.class, Boolean.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), name, isEmergency, VerificationContext.forCommand(c)}
            );

//package org.test.aptosblogdemo.domain.blog;
//
//public class CreateLogic {
//    public static BlogEvent.BlogCreated verify(java.util.function.Supplier<BlogEvent.BlogCreated> eventFactory, BlogState blogState, String name, Boolean isEmergency, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected BlogEvent.ArticleAddedToBlog verifyAddArticle(java.util.function.Supplier<BlogEvent.ArticleAddedToBlog> eventFactory, BigInteger articleId, BlogCommands.AddArticle c) {
            BigInteger ArticleId = articleId;

            BlogEvent.ArticleAddedToBlog e = (BlogEvent.ArticleAddedToBlog) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.blog.AddArticleLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, BlogState.class, BigInteger.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), articleId, VerificationContext.forCommand(c)}
            );

//package org.test.aptosblogdemo.domain.blog;
//
//public class AddArticleLogic {
//    public static BlogEvent.ArticleAddedToBlog verify(java.util.function.Supplier<BlogEvent.ArticleAddedToBlog> eventFactory, BlogState blogState, BigInteger articleId, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected BlogEvent.ArticleRemovedFromBlog verifyRemoveArticle(java.util.function.Supplier<BlogEvent.ArticleRemovedFromBlog> eventFactory, BigInteger articleId, BlogCommands.RemoveArticle c) {
            BigInteger ArticleId = articleId;

            BlogEvent.ArticleRemovedFromBlog e = (BlogEvent.ArticleRemovedFromBlog) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.blog.RemoveArticleLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, BlogState.class, BigInteger.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), articleId, VerificationContext.forCommand(c)}
            );

//package org.test.aptosblogdemo.domain.blog;
//
//public class RemoveArticleLogic {
//    public static BlogEvent.ArticleRemovedFromBlog verify(java.util.function.Supplier<BlogEvent.ArticleRemovedFromBlog> eventFactory, BlogState blogState, BigInteger articleId, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected BlogEvent.DonationReceived verifyDonate(java.util.function.Supplier<BlogEvent.DonationReceived> eventFactory, BlogCommands.Donate c) {

            BlogEvent.DonationReceived e = (BlogEvent.DonationReceived) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.blog.DonateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, BlogState.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), VerificationContext.forCommand(c)}
            );

//package org.test.aptosblogdemo.domain.blog;
//
//public class DonateLogic {
//    public static BlogEvent.DonationReceived verify(java.util.function.Supplier<BlogEvent.DonationReceived> eventFactory, BlogState blogState, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected BlogEvent.VaultWithdrawn verifyWithdraw(java.util.function.Supplier<BlogEvent.VaultWithdrawn> eventFactory, BigInteger amount, BlogCommands.Withdraw c) {
            BigInteger Amount = amount;

            BlogEvent.VaultWithdrawn e = (BlogEvent.VaultWithdrawn) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.blog.WithdrawLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, BlogState.class, BigInteger.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), amount, VerificationContext.forCommand(c)}
            );

//package org.test.aptosblogdemo.domain.blog;
//
//public class WithdrawLogic {
//    public static BlogEvent.VaultWithdrawn verify(java.util.function.Supplier<BlogEvent.VaultWithdrawn> eventFactory, BlogState blogState, BigInteger amount, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected BlogEvent.BlogUpdated verifyUpdate(java.util.function.Supplier<BlogEvent.BlogUpdated> eventFactory, String name, BigInteger[] articles, Boolean isEmergency, BlogCommands.Update c) {
            String Name = name;
            BigInteger[] Articles = articles;
            Boolean IsEmergency = isEmergency;

            BlogEvent.BlogUpdated e = (BlogEvent.BlogUpdated) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.blog.UpdateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, BlogState.class, String.class, BigInteger[].class, Boolean.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), name, articles, isEmergency, VerificationContext.forCommand(c)}
            );

//package org.test.aptosblogdemo.domain.blog;
//
//public class UpdateLogic {
//    public static BlogEvent.BlogUpdated verify(java.util.function.Supplier<BlogEvent.BlogUpdated> eventFactory, BlogState blogState, String name, BigInteger[] articles, Boolean isEmergency, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected BlogEvent.BlogDeleted verifyDelete(java.util.function.Supplier<BlogEvent.BlogDeleted> eventFactory, BlogCommands.Delete c) {

            BlogEvent.BlogDeleted e = (BlogEvent.BlogDeleted) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.blog.DeleteLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, BlogState.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), VerificationContext.forCommand(c)}
            );

//package org.test.aptosblogdemo.domain.blog;
//
//public class DeleteLogic {
//    public static BlogEvent.BlogDeleted verify(java.util.function.Supplier<BlogEvent.BlogDeleted> eventFactory, BlogState blogState, VerificationContext verificationContext) {
//    }
//}

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

        protected AbstractBlogEvent.ArticleAddedToBlog newArticleAddedToBlog(BigInteger articleId, Long offChainVersion, String commandId, String requesterId) {
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

        protected AbstractBlogEvent.ArticleRemovedFromBlog newArticleRemovedFromBlog(BigInteger articleId, Long offChainVersion, String commandId, String requesterId) {
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

        protected AbstractBlogEvent.BlogUpdated newBlogUpdated(String name, BigInteger[] articles, Boolean isEmergency, Long offChainVersion, String commandId, String requesterId) {
            BlogEventId eventId = new BlogEventId(getState().getAccountAddress(), null);
            AbstractBlogEvent.BlogUpdated e = new AbstractBlogEvent.BlogUpdated();

            e.setName(name);
            e.setArticles(articles);
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

