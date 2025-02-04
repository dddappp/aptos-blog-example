// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract;

import java.math.*;
import java.util.*;
import java.util.stream.Collectors;

import com.github.wubuku.aptos.bean.AptosObject;
import com.github.wubuku.aptos.bean.Event;
import com.github.wubuku.aptos.bean.Option;
import org.test.aptosblogdemo.domain.AptosEvent;
import org.test.aptosblogdemo.domain.AptosEventGuid;
import org.test.aptosblogdemo.domain.article.AbstractArticleEvent;
import org.test.aptosblogdemo.aptos.contract.article.AddTagEvent;
import org.test.aptosblogdemo.aptos.contract.article.ArticleCreated;
import org.test.aptosblogdemo.aptos.contract.article.ArticleUpdated;
import org.test.aptosblogdemo.aptos.contract.article.ArticleDeleted;
import org.test.aptosblogdemo.aptos.contract.article.CommentAdded;
import org.test.aptosblogdemo.aptos.contract.article.CommentUpdated;
import org.test.aptosblogdemo.aptos.contract.article.CommentRemoved;
import org.test.aptosblogdemo.domain.tag.AbstractTagEvent;
import org.test.aptosblogdemo.aptos.contract.tag.TagCreated;
import org.test.aptosblogdemo.domain.blog.AbstractBlogEvent;
import org.test.aptosblogdemo.aptos.contract.blog.BlogCreated;
import org.test.aptosblogdemo.aptos.contract.blog.ArticleAddedToBlog;
import org.test.aptosblogdemo.aptos.contract.blog.ArticleRemovedFromBlog;
import org.test.aptosblogdemo.aptos.contract.blog.DonationReceived;
import org.test.aptosblogdemo.aptos.contract.blog.VaultWithdrawn;
import org.test.aptosblogdemo.aptos.contract.blog.InitFaVaultEvent;
import org.test.aptosblogdemo.aptos.contract.blog.FaDonationReceived;
import org.test.aptosblogdemo.aptos.contract.blog.FaVaultWithdrawn;
import org.test.aptosblogdemo.aptos.contract.blog.BlogUpdated;
import org.test.aptosblogdemo.aptos.contract.blog.BlogDeleted;

/**
 * Utils that convert beans in the contract package to domain beans.
 */
public class DomainBeanUtils {
    private DomainBeanUtils() {
    }

    public static org.test.aptosblogdemo.domain.Coin toCoin(Coin contractCoin) {
        if (contractCoin == null) {
            return null;
        }
        org.test.aptosblogdemo.domain.Coin coin = new org.test.aptosblogdemo.domain.Coin();
        coin.setValue(contractCoin.getValue());
        return coin;
    }

    public static org.test.aptosblogdemo.domain.FAMetadata toFAMetadata(FAMetadata contractFAMetadata) {
        if (contractFAMetadata == null) {
            return null;
        }
        org.test.aptosblogdemo.domain.FAMetadata faMetadata = new org.test.aptosblogdemo.domain.FAMetadata();
        faMetadata.setName(contractFAMetadata.getName());
        faMetadata.setSymbol(contractFAMetadata.getSymbol());
        faMetadata.setDecimals(contractFAMetadata.getDecimals());
        faMetadata.setIconUri(contractFAMetadata.getIconUri());
        faMetadata.setProjectUri(contractFAMetadata.getProjectUri());
        return faMetadata;
    }

    public static org.test.aptosblogdemo.domain.FungibleStore toFungibleStore(FungibleStore contractFungibleStore) {
        if (contractFungibleStore == null) {
            return null;
        }
        org.test.aptosblogdemo.domain.FungibleStore fungibleStore = new org.test.aptosblogdemo.domain.FungibleStore();
        fungibleStore.setMetadata(contractFungibleStore.getMetadata().getInner());
        fungibleStore.setBalance(contractFungibleStore.getBalance());
        fungibleStore.setFrozen(contractFungibleStore.getFrozen());
        return fungibleStore;
    }

    public static org.test.aptosblogdemo.domain.ObjectDeleteRef toObjectDeleteRef(ObjectDeleteRef contractObjectDeleteRef) {
        if (contractObjectDeleteRef == null) {
            return null;
        }
        org.test.aptosblogdemo.domain.ObjectDeleteRef objectDeleteRef = new org.test.aptosblogdemo.domain.ObjectDeleteRef();
        objectDeleteRef.setSelf(contractObjectDeleteRef.getSelf());
        return objectDeleteRef;
    }

    public static org.test.aptosblogdemo.domain.ObjectExtendRef toObjectExtendRef(ObjectExtendRef contractObjectExtendRef) {
        if (contractObjectExtendRef == null) {
            return null;
        }
        org.test.aptosblogdemo.domain.ObjectExtendRef objectExtendRef = new org.test.aptosblogdemo.domain.ObjectExtendRef();
        objectExtendRef.setSelf(contractObjectExtendRef.getSelf());
        return objectExtendRef;
    }

    public static org.test.aptosblogdemo.domain.ObjectTransferRef toObjectTransferRef(ObjectTransferRef contractObjectTransferRef) {
        if (contractObjectTransferRef == null) {
            return null;
        }
        org.test.aptosblogdemo.domain.ObjectTransferRef objectTransferRef = new org.test.aptosblogdemo.domain.ObjectTransferRef();
        objectTransferRef.setSelf(contractObjectTransferRef.getSelf());
        return objectTransferRef;
    }


    public static AbstractArticleEvent.AddTagEvent toAddTagEvent(Event<AddTagEvent> eventEnvelope) {
        AddTagEvent contractEvent = eventEnvelope.getData();

        AbstractArticleEvent.AddTagEvent addTagEvent = new AbstractArticleEvent.AddTagEvent();
        addTagEvent.setId(contractEvent.getId());
        addTagEvent.setTag(contractEvent.getTag().getInner());
        addTagEvent.setVersion(contractEvent.getVersion());

        setAptosEventProperties(addTagEvent, eventEnvelope);

        return addTagEvent;
    }

    public static AbstractArticleEvent.ArticleCreated toArticleCreated(Event<ArticleCreated> eventEnvelope) {
        ArticleCreated contractEvent = eventEnvelope.getData();

        AbstractArticleEvent.ArticleCreated articleCreated = new AbstractArticleEvent.ArticleCreated();
        articleCreated.setId(contractEvent.getId().getVec().get(0));
        articleCreated.setTitle(contractEvent.getTitle());
        articleCreated.setBody(contractEvent.getBody());
        articleCreated.setOwner(contractEvent.getOwner());
        articleCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(articleCreated, eventEnvelope);

        return articleCreated;
    }

    public static AbstractArticleEvent.ArticleUpdated toArticleUpdated(Event<ArticleUpdated> eventEnvelope) {
        ArticleUpdated contractEvent = eventEnvelope.getData();

        AbstractArticleEvent.ArticleUpdated articleUpdated = new AbstractArticleEvent.ArticleUpdated();
        articleUpdated.setId(contractEvent.getId());
        articleUpdated.setTitle(contractEvent.getTitle());
        articleUpdated.setBody(contractEvent.getBody());
        articleUpdated.setOwner(contractEvent.getOwner());
        articleUpdated.setTags(java.util.Arrays.stream(contractEvent.getTags()).map(com.github.wubuku.aptos.bean.AptosObject::getInner).toArray(String[]::new));
        articleUpdated.setVersion(contractEvent.getVersion());

        setAptosEventProperties(articleUpdated, eventEnvelope);

        return articleUpdated;
    }

    public static AbstractArticleEvent.ArticleDeleted toArticleDeleted(Event<ArticleDeleted> eventEnvelope) {
        ArticleDeleted contractEvent = eventEnvelope.getData();

        AbstractArticleEvent.ArticleDeleted articleDeleted = new AbstractArticleEvent.ArticleDeleted();
        articleDeleted.setId(contractEvent.getId());
        articleDeleted.setVersion(contractEvent.getVersion());

        setAptosEventProperties(articleDeleted, eventEnvelope);

        return articleDeleted;
    }

    public static AbstractArticleEvent.CommentAdded toCommentAdded(Event<CommentAdded> eventEnvelope) {
        CommentAdded contractEvent = eventEnvelope.getData();

        AbstractArticleEvent.CommentAdded commentAdded = new AbstractArticleEvent.CommentAdded();
        commentAdded.setId(contractEvent.getId());
        commentAdded.setCommentSeqId(contractEvent.getCommentSeqId());
        commentAdded.setCommenter(contractEvent.getCommenter());
        commentAdded.setBody(contractEvent.getBody());
        commentAdded.setOwner(contractEvent.getOwner());
        commentAdded.setVersion(contractEvent.getVersion());

        setAptosEventProperties(commentAdded, eventEnvelope);

        return commentAdded;
    }

    public static AbstractArticleEvent.CommentUpdated toCommentUpdated(Event<CommentUpdated> eventEnvelope) {
        CommentUpdated contractEvent = eventEnvelope.getData();

        AbstractArticleEvent.CommentUpdated commentUpdated = new AbstractArticleEvent.CommentUpdated();
        commentUpdated.setId(contractEvent.getId());
        commentUpdated.setCommentSeqId(contractEvent.getCommentSeqId());
        commentUpdated.setCommenter(contractEvent.getCommenter());
        commentUpdated.setBody(contractEvent.getBody());
        commentUpdated.setOwner(contractEvent.getOwner());
        commentUpdated.setVersion(contractEvent.getVersion());

        setAptosEventProperties(commentUpdated, eventEnvelope);

        return commentUpdated;
    }

    public static AbstractArticleEvent.CommentRemoved toCommentRemoved(Event<CommentRemoved> eventEnvelope) {
        CommentRemoved contractEvent = eventEnvelope.getData();

        AbstractArticleEvent.CommentRemoved commentRemoved = new AbstractArticleEvent.CommentRemoved();
        commentRemoved.setId(contractEvent.getId());
        commentRemoved.setCommentSeqId(contractEvent.getCommentSeqId());
        commentRemoved.setVersion(contractEvent.getVersion());

        setAptosEventProperties(commentRemoved, eventEnvelope);

        return commentRemoved;
    }

    public static AbstractTagEvent.TagCreated toTagCreated(Event<TagCreated> eventEnvelope) {
        TagCreated contractEvent = eventEnvelope.getData();

        AbstractTagEvent.TagCreated tagCreated = new AbstractTagEvent.TagCreated();
        tagCreated.setTagId(contractEvent.getTagId().getVec().get(0));
        tagCreated.setName(contractEvent.getName());
        tagCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(tagCreated, eventEnvelope);

        return tagCreated;
    }

    public static AbstractBlogEvent.BlogCreated toBlogCreated(Event<BlogCreated> eventEnvelope) {
        BlogCreated contractEvent = eventEnvelope.getData();

        AbstractBlogEvent.BlogCreated blogCreated = new AbstractBlogEvent.BlogCreated();
        blogCreated.setAccountAddress(contractEvent.getAccountAddress());
        blogCreated.setName(contractEvent.getName());
        blogCreated.setIsEmergency(contractEvent.getIsEmergency());
        blogCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(blogCreated, eventEnvelope);

        return blogCreated;
    }

    public static AbstractBlogEvent.ArticleAddedToBlog toArticleAddedToBlog(Event<ArticleAddedToBlog> eventEnvelope) {
        ArticleAddedToBlog contractEvent = eventEnvelope.getData();

        AbstractBlogEvent.ArticleAddedToBlog articleAddedToBlog = new AbstractBlogEvent.ArticleAddedToBlog();
        articleAddedToBlog.setAccountAddress(contractEvent.getAccountAddress());
        articleAddedToBlog.setArticleId(contractEvent.getArticleId());
        articleAddedToBlog.setVersion(contractEvent.getVersion());

        setAptosEventProperties(articleAddedToBlog, eventEnvelope);

        return articleAddedToBlog;
    }

    public static AbstractBlogEvent.ArticleRemovedFromBlog toArticleRemovedFromBlog(Event<ArticleRemovedFromBlog> eventEnvelope) {
        ArticleRemovedFromBlog contractEvent = eventEnvelope.getData();

        AbstractBlogEvent.ArticleRemovedFromBlog articleRemovedFromBlog = new AbstractBlogEvent.ArticleRemovedFromBlog();
        articleRemovedFromBlog.setAccountAddress(contractEvent.getAccountAddress());
        articleRemovedFromBlog.setArticleId(contractEvent.getArticleId());
        articleRemovedFromBlog.setVersion(contractEvent.getVersion());

        setAptosEventProperties(articleRemovedFromBlog, eventEnvelope);

        return articleRemovedFromBlog;
    }

    public static AbstractBlogEvent.DonationReceived toDonationReceived(Event<DonationReceived> eventEnvelope) {
        DonationReceived contractEvent = eventEnvelope.getData();

        AbstractBlogEvent.DonationReceived donationReceived = new AbstractBlogEvent.DonationReceived();
        donationReceived.setAccountAddress(contractEvent.getAccountAddress());
        donationReceived.setAmount(contractEvent.getAmount());
        donationReceived.setVersion(contractEvent.getVersion());

        setAptosEventProperties(donationReceived, eventEnvelope);

        return donationReceived;
    }

    public static AbstractBlogEvent.VaultWithdrawn toVaultWithdrawn(Event<VaultWithdrawn> eventEnvelope) {
        VaultWithdrawn contractEvent = eventEnvelope.getData();

        AbstractBlogEvent.VaultWithdrawn vaultWithdrawn = new AbstractBlogEvent.VaultWithdrawn();
        vaultWithdrawn.setAccountAddress(contractEvent.getAccountAddress());
        vaultWithdrawn.setAmount(contractEvent.getAmount());
        vaultWithdrawn.setVersion(contractEvent.getVersion());

        setAptosEventProperties(vaultWithdrawn, eventEnvelope);

        return vaultWithdrawn;
    }

    public static AbstractBlogEvent.InitFaVaultEvent toInitFaVaultEvent(Event<InitFaVaultEvent> eventEnvelope) {
        InitFaVaultEvent contractEvent = eventEnvelope.getData();

        AbstractBlogEvent.InitFaVaultEvent initFaVaultEvent = new AbstractBlogEvent.InitFaVaultEvent();
        initFaVaultEvent.setAccountAddress(contractEvent.getAccountAddress());
        initFaVaultEvent.setMetadata(contractEvent.getMetadata());
        initFaVaultEvent.setVersion(contractEvent.getVersion());

        setAptosEventProperties(initFaVaultEvent, eventEnvelope);

        return initFaVaultEvent;
    }

    public static AbstractBlogEvent.FaDonationReceived toFaDonationReceived(Event<FaDonationReceived> eventEnvelope) {
        FaDonationReceived contractEvent = eventEnvelope.getData();

        AbstractBlogEvent.FaDonationReceived faDonationReceived = new AbstractBlogEvent.FaDonationReceived();
        faDonationReceived.setAccountAddress(contractEvent.getAccountAddress());
        faDonationReceived.setFaAmount(contractEvent.getFaAmount());
        faDonationReceived.setVersion(contractEvent.getVersion());

        setAptosEventProperties(faDonationReceived, eventEnvelope);

        return faDonationReceived;
    }

    public static AbstractBlogEvent.FaVaultWithdrawn toFaVaultWithdrawn(Event<FaVaultWithdrawn> eventEnvelope) {
        FaVaultWithdrawn contractEvent = eventEnvelope.getData();

        AbstractBlogEvent.FaVaultWithdrawn faVaultWithdrawn = new AbstractBlogEvent.FaVaultWithdrawn();
        faVaultWithdrawn.setAccountAddress(contractEvent.getAccountAddress());
        faVaultWithdrawn.setAmount(contractEvent.getAmount());
        faVaultWithdrawn.setVersion(contractEvent.getVersion());

        setAptosEventProperties(faVaultWithdrawn, eventEnvelope);

        return faVaultWithdrawn;
    }

    public static AbstractBlogEvent.BlogUpdated toBlogUpdated(Event<BlogUpdated> eventEnvelope) {
        BlogUpdated contractEvent = eventEnvelope.getData();

        AbstractBlogEvent.BlogUpdated blogUpdated = new AbstractBlogEvent.BlogUpdated();
        blogUpdated.setAccountAddress(contractEvent.getAccountAddress());
        blogUpdated.setName(contractEvent.getName());
        blogUpdated.setArticles(contractEvent.getArticles());
        blogUpdated.setIsEmergency(contractEvent.getIsEmergency());
        blogUpdated.setFaVault(java.util.Optional.ofNullable(extractOptionalValue(contractEvent.getFaVault())).map(value -> value.getInner()).orElse(null));
        blogUpdated.setVersion(contractEvent.getVersion());

        setAptosEventProperties(blogUpdated, eventEnvelope);

        return blogUpdated;
    }

    public static AbstractBlogEvent.BlogDeleted toBlogDeleted(Event<BlogDeleted> eventEnvelope) {
        BlogDeleted contractEvent = eventEnvelope.getData();

        AbstractBlogEvent.BlogDeleted blogDeleted = new AbstractBlogEvent.BlogDeleted();
        blogDeleted.setAccountAddress(contractEvent.getAccountAddress());
        blogDeleted.setVersion(contractEvent.getVersion());

        setAptosEventProperties(blogDeleted, eventEnvelope);

        return blogDeleted;
    }

    public static org.test.aptosblogdemo.aptos.contract.persistence.CommentTableItemAdded toPersistenceCommentTableItemAdded(Event<CommentTableItemAdded> eventEnvelope) {
        CommentTableItemAdded contractEvent = eventEnvelope.getData();
        org.test.aptosblogdemo.domain.article.ArticleCommentId id = new org.test.aptosblogdemo.domain.article.ArticleCommentId(contractEvent.getArticleId(), contractEvent.getCommentSeqId());
        org.test.aptosblogdemo.aptos.contract.persistence.CommentTableItemAdded e = new org.test.aptosblogdemo.aptos.contract.persistence.CommentTableItemAdded();
        e.setArticleCommentId(id);
        setAptosEventProperties(e, eventEnvelope);
        return e;
    }

    public static void setAptosEventProperties(AptosEvent.MutableAptosEvent domainAptosEvent, Event<?> eventEnvelope) {
        domainAptosEvent.setAptosEventGuid(toAptosEventGuid(eventEnvelope.getGuid()));
        domainAptosEvent.setAptosEventType(eventEnvelope.getType());
        domainAptosEvent.setAptosEventSequenceNumber(new BigInteger(eventEnvelope.getSequenceNumber()));
        domainAptosEvent.setAptosEventVersion(new BigInteger(eventEnvelope.getVersion()));
    }

    public static AptosEventGuid toAptosEventGuid(com.github.wubuku.aptos.bean.Event.Guid eventGuid) {
        return new AptosEventGuid(new BigInteger(eventGuid.getCreationNumber()), eventGuid.getAccountAddress());
    }

    private static <T> T extractOptionalValue(Option<T> optionView) {
        return optionView == null ? null
                : (optionView.getVec() == null || optionView.getVec().size() == 0) ? null
                : optionView.getVec().get(0);
    }

    public static List<String> extractTypeArguments(String type) {
        int i = type.indexOf("<");
        int j = type.lastIndexOf(">");
        if (i > 0 && j > i && j == type.length() - 1) {
            String typeArguments = type.substring(i + 1, j);
            String[] typeArgumentArray = typeArguments.split(",");
            return Arrays.stream(typeArgumentArray).map(String::trim).collect(java.util.stream.Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
