// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.blog;

import java.util.*;
import java.math.*;
import java.util.Date;
import java.math.BigInteger;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.*;
import org.test.aptosblogdemo.domain.blog.BlogEvent.*;

public abstract class AbstractBlogState implements BlogState.SqlBlogState {

    private String accountAddress;

    public String getAccountAddress() {
        return this.accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Coin vault;

    public Coin getVault() {
        return this.vault;
    }

    public void setVault(Coin vault) {
        this.vault = vault;
    }

    private Boolean isEmergency;

    public Boolean getIsEmergency() {
        return this.isEmergency;
    }

    public void setIsEmergency(Boolean isEmergency) {
        this.isEmergency = isEmergency;
    }

    private String faVault;

    public String getFaVault() {
        return this.faVault;
    }

    public void setFaVault(String faVault) {
        this.faVault = faVault;
    }

    private Long offChainVersion;

    public Long getOffChainVersion() {
        return this.offChainVersion;
    }

    public void setOffChainVersion(Long offChainVersion) {
        this.offChainVersion = offChainVersion;
    }

    private String createdBy;

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private Date createdAt;

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    private String updatedBy;

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    private Date updatedAt;

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    private Boolean active;

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    private Boolean deleted;

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    private BigInteger version;

    public BigInteger getVersion() {
        return this.version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    private Set<String> articles;

    public Set<String> getArticles() {
        return this.articles;
    }

    public void setArticles(Set<String> articles) {
        this.articles = articles;
    }

    public boolean isStateUnsaved() {
        return this.getOffChainVersion() == null;
    }

    private Boolean stateReadOnly;

    public Boolean getStateReadOnly() { return this.stateReadOnly; }

    public void setStateReadOnly(Boolean readOnly) { this.stateReadOnly = readOnly; }

    private boolean forReapplying;

    public boolean getForReapplying() {
        return forReapplying;
    }

    public void setForReapplying(boolean forReapplying) {
        this.forReapplying = forReapplying;
    }

    public AbstractBlogState(List<Event> events) {
        initializeForReapplying();
        if (events != null && events.size() > 0) {
            this.setAccountAddress(((BlogEvent.SqlBlogEvent) events.get(0)).getBlogEventId().getAccountAddress());
            for (Event e : events) {
                mutate(e);
                this.setOffChainVersion((this.getOffChainVersion() == null ? BlogState.VERSION_NULL : this.getOffChainVersion()) + 1);
            }
        }
    }


    public AbstractBlogState() {
        initializeProperties();
    }

    protected void initializeForReapplying() {
        this.forReapplying = true;

        initializeProperties();
    }
    
    protected void initializeProperties() {
    }

    @Override
    public int hashCode() {
        return getAccountAddress().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj instanceof BlogState) {
            return Objects.equals(this.getAccountAddress(), ((BlogState)obj).getAccountAddress());
        }
        return false;
    }


    public void mutate(Event e) {
        setStateReadOnly(false);
        if (false) { 
            ;
        } else if (e instanceof AbstractBlogEvent.BlogCreated) {
            when((AbstractBlogEvent.BlogCreated)e);
        } else if (e instanceof AbstractBlogEvent.InitFaVaultEvent) {
            when((AbstractBlogEvent.InitFaVaultEvent)e);
        } else if (e instanceof AbstractBlogEvent.BlogUpdated) {
            when((AbstractBlogEvent.BlogUpdated)e);
        } else if (e instanceof AbstractBlogEvent.BlogDeleted) {
            when((AbstractBlogEvent.BlogDeleted)e);
        } else {
            throw new UnsupportedOperationException(String.format("Unsupported event type: %1$s", e.getClass().getName()));
        }
    }

    public void merge(BlogState s) {
        if (s == this) {
            return;
        }
        this.setName(s.getName());
        this.setArticles(s.getArticles());
        this.setVault(s.getVault());
        this.setIsEmergency(s.getIsEmergency());
        this.setFaVault(s.getFaVault());
        this.setActive(s.getActive());
        this.setVersion(s.getVersion());
    }

    public void when(AbstractBlogEvent.BlogCreated e) {
        throwOnWrongEvent(e);

        String name = e.getName();
        String Name = name;
        Boolean isEmergency = e.getIsEmergency();
        Boolean IsEmergency = isEmergency;
        BigInteger aptosEventVersion = e.getAptosEventVersion();
        BigInteger AptosEventVersion = aptosEventVersion;
        BigInteger aptosEventSequenceNumber = e.getAptosEventSequenceNumber();
        BigInteger AptosEventSequenceNumber = aptosEventSequenceNumber;
        String aptosEventType = e.getAptosEventType();
        String AptosEventType = aptosEventType;
        AptosEventGuid aptosEventGuid = e.getAptosEventGuid();
        AptosEventGuid AptosEventGuid = aptosEventGuid;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        BlogState updatedBlogState = ApplicationContext.current.get(ICreateLogic.class).mutate(
                this, name, isEmergency, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.of(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}}));


        if (this != updatedBlogState) { merge(updatedBlogState); } //else do nothing

    }

    public void when(AbstractBlogEvent.ArticleAddedToBlog e) {
        throwOnWrongEvent(e);

        String articleId = e.getArticleId();
        String ArticleId = articleId;
        BigInteger aptosEventVersion = e.getAptosEventVersion();
        BigInteger AptosEventVersion = aptosEventVersion;
        BigInteger aptosEventSequenceNumber = e.getAptosEventSequenceNumber();
        BigInteger AptosEventSequenceNumber = aptosEventSequenceNumber;
        String aptosEventType = e.getAptosEventType();
        String AptosEventType = aptosEventType;
        AptosEventGuid aptosEventGuid = e.getAptosEventGuid();
        AptosEventGuid AptosEventGuid = aptosEventGuid;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        BlogState updatedBlogState = ApplicationContext.current.get(IAddArticleLogic.class).mutate(
                this, articleId, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.of(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}}));


        if (this != updatedBlogState) { merge(updatedBlogState); } //else do nothing

    }

    public void when(AbstractBlogEvent.ArticleRemovedFromBlog e) {
        throwOnWrongEvent(e);

        String articleId = e.getArticleId();
        String ArticleId = articleId;
        BigInteger aptosEventVersion = e.getAptosEventVersion();
        BigInteger AptosEventVersion = aptosEventVersion;
        BigInteger aptosEventSequenceNumber = e.getAptosEventSequenceNumber();
        BigInteger AptosEventSequenceNumber = aptosEventSequenceNumber;
        String aptosEventType = e.getAptosEventType();
        String AptosEventType = aptosEventType;
        AptosEventGuid aptosEventGuid = e.getAptosEventGuid();
        AptosEventGuid AptosEventGuid = aptosEventGuid;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        BlogState updatedBlogState = ApplicationContext.current.get(IRemoveArticleLogic.class).mutate(
                this, articleId, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.of(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}}));


        if (this != updatedBlogState) { merge(updatedBlogState); } //else do nothing

    }

    public void when(AbstractBlogEvent.DonationReceived e) {
        throwOnWrongEvent(e);

        BigInteger amount = e.getAmount();
        BigInteger Amount = amount;
        BigInteger aptosEventVersion = e.getAptosEventVersion();
        BigInteger AptosEventVersion = aptosEventVersion;
        BigInteger aptosEventSequenceNumber = e.getAptosEventSequenceNumber();
        BigInteger AptosEventSequenceNumber = aptosEventSequenceNumber;
        String aptosEventType = e.getAptosEventType();
        String AptosEventType = aptosEventType;
        AptosEventGuid aptosEventGuid = e.getAptosEventGuid();
        AptosEventGuid AptosEventGuid = aptosEventGuid;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        BlogState updatedBlogState = ApplicationContext.current.get(IDonateLogic.class).mutate(
                this, amount, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.of(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}}));


        if (this != updatedBlogState) { merge(updatedBlogState); } //else do nothing

    }

    public void when(AbstractBlogEvent.InitFaVaultEvent e) {
        throwOnWrongEvent(e);

        String metadata = e.getMetadata();
        String Metadata = metadata;
        BigInteger aptosEventVersion = e.getAptosEventVersion();
        BigInteger AptosEventVersion = aptosEventVersion;
        BigInteger aptosEventSequenceNumber = e.getAptosEventSequenceNumber();
        BigInteger AptosEventSequenceNumber = aptosEventSequenceNumber;
        String aptosEventType = e.getAptosEventType();
        String AptosEventType = aptosEventType;
        AptosEventGuid aptosEventGuid = e.getAptosEventGuid();
        AptosEventGuid AptosEventGuid = aptosEventGuid;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        BlogState updatedBlogState = ApplicationContext.current.get(IInitFaVaultLogic.class).mutate(
                this, metadata, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.of(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}}));


        if (this != updatedBlogState) { merge(updatedBlogState); } //else do nothing

    }

    public void when(AbstractBlogEvent.FaDonationReceived e) {
        throwOnWrongEvent(e);

        BigInteger faAmount = e.getFaAmount();
        BigInteger FaAmount = faAmount;
        BigInteger aptosEventVersion = e.getAptosEventVersion();
        BigInteger AptosEventVersion = aptosEventVersion;
        BigInteger aptosEventSequenceNumber = e.getAptosEventSequenceNumber();
        BigInteger AptosEventSequenceNumber = aptosEventSequenceNumber;
        String aptosEventType = e.getAptosEventType();
        String AptosEventType = aptosEventType;
        AptosEventGuid aptosEventGuid = e.getAptosEventGuid();
        AptosEventGuid AptosEventGuid = aptosEventGuid;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        BlogState updatedBlogState = ApplicationContext.current.get(IDonateFaLogic.class).mutate(
                this, faAmount, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.of(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}}));


        if (this != updatedBlogState) { merge(updatedBlogState); } //else do nothing

    }

    public void when(AbstractBlogEvent.BlogUpdated e) {
        throwOnWrongEvent(e);

        String name = e.getName();
        String Name = name;
        String[] articles = e.getArticles();
        String[] Articles = articles;
        Boolean isEmergency = e.getIsEmergency();
        Boolean IsEmergency = isEmergency;
        String faVault = e.getFaVault();
        String FaVault = faVault;
        BigInteger aptosEventVersion = e.getAptosEventVersion();
        BigInteger AptosEventVersion = aptosEventVersion;
        BigInteger aptosEventSequenceNumber = e.getAptosEventSequenceNumber();
        BigInteger AptosEventSequenceNumber = aptosEventSequenceNumber;
        String aptosEventType = e.getAptosEventType();
        String AptosEventType = aptosEventType;
        AptosEventGuid aptosEventGuid = e.getAptosEventGuid();
        AptosEventGuid AptosEventGuid = aptosEventGuid;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        BlogState updatedBlogState = ApplicationContext.current.get(IUpdateLogic.class).mutate(
                this, name, articles, isEmergency, faVault, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.of(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}}));


        if (this != updatedBlogState) { merge(updatedBlogState); } //else do nothing

    }

    public void when(AbstractBlogEvent.BlogDeleted e) {
        throwOnWrongEvent(e);

        BigInteger aptosEventVersion = e.getAptosEventVersion();
        BigInteger AptosEventVersion = aptosEventVersion;
        BigInteger aptosEventSequenceNumber = e.getAptosEventSequenceNumber();
        BigInteger AptosEventSequenceNumber = aptosEventSequenceNumber;
        String aptosEventType = e.getAptosEventType();
        String AptosEventType = aptosEventType;
        AptosEventGuid aptosEventGuid = e.getAptosEventGuid();
        AptosEventGuid AptosEventGuid = aptosEventGuid;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        BlogState updatedBlogState = ApplicationContext.current.get(IDeleteLogic.class).mutate(
                this, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.of(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}}));


        if (this != updatedBlogState) { merge(updatedBlogState); } //else do nothing

    }

    public void save() {
    }

    protected void throwOnWrongEvent(BlogEvent event) {
        String stateEntityId = this.getAccountAddress(); // Aggregate Id
        String eventEntityId = ((BlogEvent.SqlBlogEvent)event).getBlogEventId().getAccountAddress(); // EntityBase.Aggregate.GetEventIdPropertyIdName();
        if (!stateEntityId.equals(eventEntityId)) {
            throw DomainError.named("mutateWrongEntity", "Entity Id %1$s in state but entity id %2$s in event", stateEntityId, eventEntityId);
        }


        Long stateVersion = this.getOffChainVersion();

    }


    public static class SimpleBlogState extends AbstractBlogState {

        public SimpleBlogState() {
        }

        public SimpleBlogState(List<Event> events) {
            super(events);
        }

        public static SimpleBlogState newForReapplying() {
            SimpleBlogState s = new SimpleBlogState();
            s.initializeForReapplying();
            return s;
        }

    }



}

