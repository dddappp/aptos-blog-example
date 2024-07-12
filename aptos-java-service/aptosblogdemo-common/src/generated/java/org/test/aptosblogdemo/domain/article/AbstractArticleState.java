// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.article;

import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.*;
import org.test.aptosblogdemo.domain.article.ArticleEvent.*;

public abstract class AbstractArticleState implements ArticleState.SqlArticleState, Saveable {

    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String body;

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String owner;

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    private BigInteger version;

    public BigInteger getVersion() {
        return this.version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
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

    public boolean isStateUnsaved() {
        return this.getOffChainVersion() == null;
    }

    private Set<CommentState> protectedComments = new HashSet<>();

    protected Set<CommentState> getProtectedComments() {
        return this.protectedComments;
    }

    protected void setProtectedComments(Set<CommentState> protectedComments) {
        this.protectedComments = protectedComments;
    }

    private EntityStateCollection<BigInteger, CommentState> comments;

    public EntityStateCollection<BigInteger, CommentState> getComments() {
        return this.comments;
    }

    public void setComments(EntityStateCollection<BigInteger, CommentState> comments) {
        this.comments = comments;
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

    public AbstractArticleState(List<Event> events) {
        initializeForReapplying();
        if (events != null && events.size() > 0) {
            this.setId(((ArticleEvent.SqlArticleEvent) events.get(0)).getArticleEventId().getId());
            for (Event e : events) {
                mutate(e);
                this.setOffChainVersion((this.getOffChainVersion() == null ? ArticleState.VERSION_NULL : this.getOffChainVersion()) + 1);
            }
        }
    }


    public AbstractArticleState() {
        initializeProperties();
    }

    protected void initializeForReapplying() {
        this.forReapplying = true;

        initializeProperties();
    }
    
    protected void initializeProperties() {
        comments = new SimpleCommentStateCollection();
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj instanceof ArticleState) {
            return Objects.equals(this.getId(), ((ArticleState)obj).getId());
        }
        return false;
    }


    public void mutate(Event e) {
        setStateReadOnly(false);
        if (false) { 
            ;
        } else if (e instanceof AbstractArticleEvent.ArticleCreated) {
            when((AbstractArticleEvent.ArticleCreated)e);
        } else if (e instanceof AbstractArticleEvent.ArticleUpdated) {
            when((AbstractArticleEvent.ArticleUpdated)e);
        } else if (e instanceof AbstractArticleEvent.ArticleDeleted) {
            when((AbstractArticleEvent.ArticleDeleted)e);
        } else if (e instanceof AbstractArticleEvent.CommentAdded) {
            when((AbstractArticleEvent.CommentAdded)e);
        } else if (e instanceof AbstractArticleEvent.CommentUpdated) {
            when((AbstractArticleEvent.CommentUpdated)e);
        } else if (e instanceof AbstractArticleEvent.CommentRemoved) {
            when((AbstractArticleEvent.CommentRemoved)e);
        } else {
            throw new UnsupportedOperationException(String.format("Unsupported event type: %1$s", e.getClass().getName()));
        }
    }

    public void merge(ArticleState s) {
        if (s == this) {
            return;
        }
        this.setTitle(s.getTitle());
        this.setBody(s.getBody());
        this.setOwner(s.getOwner());
        this.setVersion(s.getVersion());
        this.setActive(s.getActive());

        if (s.getComments() != null) {
            Iterable<CommentState> iterable;
            if (s.getComments().isLazy()) {
                iterable = s.getComments().getLoadedStates();
            } else {
                iterable = s.getComments();
            }
            if (iterable != null) {
                for (CommentState ss : iterable) {
                    CommentState thisInnerState = ((EntityStateCollection.ModifiableEntityStateCollection<BigInteger, CommentState>)this.getComments()).getOrAddDefault(ss.getCommentSeqId());
                    ((AbstractCommentState) thisInnerState).merge(ss);
                }
            }
        }
        if (s.getComments() != null) {
            if (s.getComments() instanceof EntityStateCollection.RemovalLoggedEntityStateCollection) {
                if (((EntityStateCollection.RemovalLoggedEntityStateCollection)s.getComments()).getRemovedStates() != null) {
                    for (CommentState ss : ((EntityStateCollection.RemovalLoggedEntityStateCollection<BigInteger, CommentState>)s.getComments()).getRemovedStates()) {
                        CommentState thisInnerState = ((EntityStateCollection.ModifiableEntityStateCollection<BigInteger, CommentState>)this.getComments()).getOrAddDefault(ss.getCommentSeqId());
                        ((EntityStateCollection.ModifiableEntityStateCollection)this.getComments()).removeState(thisInnerState);
                    }
                }
            } else {
                if (s.getComments().isAllLoaded()) {
                    Set<BigInteger> removedStateIds = new HashSet<>(this.getComments().stream().map(i -> i.getCommentSeqId()).collect(java.util.stream.Collectors.toList()));
                    s.getComments().forEach(i -> removedStateIds.remove(i.getCommentSeqId()));
                    for (BigInteger i : removedStateIds) {
                        CommentState thisInnerState = ((EntityStateCollection.ModifiableEntityStateCollection<BigInteger, CommentState>)this.getComments()).getOrAddDefault(i);
                        ((EntityStateCollection.ModifiableEntityStateCollection)this.getComments()).removeState(thisInnerState);
                    }
                } else {
                    throw new UnsupportedOperationException();
                }
            }
        }
    }

    public void when(AbstractArticleEvent.ArticleCreated e) {
        throwOnWrongEvent(e);

        String title = e.getTitle();
        String Title = title;
        String body = e.getBody();
        String Body = body;
        String owner = e.getOwner();
        String Owner = owner;
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

        ArticleState updatedArticleState = (ArticleState) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.article.CreateLogic",
                    "mutate",
                    new Class[]{ArticleState.class, String.class, String.class, String.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new Object[]{this, title, body, owner, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.aptosblogdemo.domain.article;
//
//public class CreateLogic {
//    public static ArticleState mutate(ArticleState articleState, String title, String body, String owner, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<ArticleState, ArticleState.MutableArticleState> mutationContext) {
//    }
//}

        if (this != updatedArticleState) { merge(updatedArticleState); } //else do nothing

    }

    public void when(AbstractArticleEvent.ArticleUpdated e) {
        throwOnWrongEvent(e);

        String title = e.getTitle();
        String Title = title;
        String body = e.getBody();
        String Body = body;
        String owner = e.getOwner();
        String Owner = owner;
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

        ArticleState updatedArticleState = (ArticleState) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.article.UpdateLogic",
                    "mutate",
                    new Class[]{ArticleState.class, String.class, String.class, String.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new Object[]{this, title, body, owner, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.aptosblogdemo.domain.article;
//
//public class UpdateLogic {
//    public static ArticleState mutate(ArticleState articleState, String title, String body, String owner, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<ArticleState, ArticleState.MutableArticleState> mutationContext) {
//    }
//}

        if (this != updatedArticleState) { merge(updatedArticleState); } //else do nothing

    }

    public void when(AbstractArticleEvent.ArticleDeleted e) {
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

        ArticleState updatedArticleState = (ArticleState) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.article.DeleteLogic",
                    "mutate",
                    new Class[]{ArticleState.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new Object[]{this, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.aptosblogdemo.domain.article;
//
//public class DeleteLogic {
//    public static ArticleState mutate(ArticleState articleState, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<ArticleState, ArticleState.MutableArticleState> mutationContext) {
//    }
//}

        if (this != updatedArticleState) { merge(updatedArticleState); } //else do nothing

    }

    public void when(AbstractArticleEvent.CommentAdded e) {
        throwOnWrongEvent(e);

        BigInteger commentSeqId = e.getCommentSeqId();
        BigInteger CommentSeqId = commentSeqId;
        String commenter = e.getCommenter();
        String Commenter = commenter;
        String body = e.getBody();
        String Body = body;
        String owner = e.getOwner();
        String Owner = owner;
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

        ArticleState updatedArticleState = (ArticleState) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.article.AddCommentLogic",
                    "mutate",
                    new Class[]{ArticleState.class, BigInteger.class, String.class, String.class, String.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new Object[]{this, commentSeqId, commenter, body, owner, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.aptosblogdemo.domain.article;
//
//public class AddCommentLogic {
//    public static ArticleState mutate(ArticleState articleState, BigInteger commentSeqId, String commenter, String body, String owner, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<ArticleState, ArticleState.MutableArticleState> mutationContext) {
//    }
//}

        if (this != updatedArticleState) { merge(updatedArticleState); } //else do nothing

    }

    public void when(AbstractArticleEvent.CommentUpdated e) {
        throwOnWrongEvent(e);

        BigInteger commentSeqId = e.getCommentSeqId();
        BigInteger CommentSeqId = commentSeqId;
        String commenter = e.getCommenter();
        String Commenter = commenter;
        String body = e.getBody();
        String Body = body;
        String owner = e.getOwner();
        String Owner = owner;
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

        ArticleState updatedArticleState = (ArticleState) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.article.UpdateCommentLogic",
                    "mutate",
                    new Class[]{ArticleState.class, BigInteger.class, String.class, String.class, String.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new Object[]{this, commentSeqId, commenter, body, owner, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.aptosblogdemo.domain.article;
//
//public class UpdateCommentLogic {
//    public static ArticleState mutate(ArticleState articleState, BigInteger commentSeqId, String commenter, String body, String owner, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<ArticleState, ArticleState.MutableArticleState> mutationContext) {
//    }
//}

        if (this != updatedArticleState) { merge(updatedArticleState); } //else do nothing

    }

    public void when(AbstractArticleEvent.CommentRemoved e) {
        throwOnWrongEvent(e);

        BigInteger commentSeqId = e.getCommentSeqId();
        BigInteger CommentSeqId = commentSeqId;
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

        ArticleState updatedArticleState = (ArticleState) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosblogdemo.domain.article.RemoveCommentLogic",
                    "mutate",
                    new Class[]{ArticleState.class, BigInteger.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new Object[]{this, commentSeqId, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.aptosblogdemo.domain.article;
//
//public class RemoveCommentLogic {
//    public static ArticleState mutate(ArticleState articleState, BigInteger commentSeqId, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<ArticleState, ArticleState.MutableArticleState> mutationContext) {
//    }
//}

        if (this != updatedArticleState) { merge(updatedArticleState); } //else do nothing

    }

    public void save() {
        if (comments instanceof Saveable) {
            ((Saveable)comments).save();
        }
    }

    protected void throwOnWrongEvent(ArticleEvent event) {
        String stateEntityId = this.getId(); // Aggregate Id
        String eventEntityId = ((ArticleEvent.SqlArticleEvent)event).getArticleEventId().getId(); // EntityBase.Aggregate.GetEventIdPropertyIdName();
        if (!stateEntityId.equals(eventEntityId)) {
            throw DomainError.named("mutateWrongEntity", "Entity Id %1$s in state but entity id %2$s in event", stateEntityId, eventEntityId);
        }


        Long stateVersion = this.getOffChainVersion();

    }


    public static class SimpleArticleState extends AbstractArticleState {

        public SimpleArticleState() {
        }

        public SimpleArticleState(List<Event> events) {
            super(events);
        }

        public static SimpleArticleState newForReapplying() {
            SimpleArticleState s = new SimpleArticleState();
            s.initializeForReapplying();
            return s;
        }

    }


    class SimpleCommentStateCollection implements EntityStateCollection.ModifiableEntityStateCollection<BigInteger, CommentState>, Collection<CommentState> {

        @Override
        public CommentState get(BigInteger commentSeqId) {
            return protectedComments.stream().filter(
                            e -> e.getCommentSeqId().equals(commentSeqId))
                    .findFirst().orElse(null);
        }

        @Override
        public boolean isLazy() {
            return false;
        }

        @Override
        public boolean isAllLoaded() {
            return true;
        }

        @Override
        public Collection<CommentState> getLoadedStates() {
            return protectedComments;
        }

        @Override
        public CommentState getOrAddDefault(BigInteger commentSeqId) {
            CommentState s = get(commentSeqId);
            if (s == null) {
                ArticleCommentId globalId = new ArticleCommentId(getId(), commentSeqId);
                AbstractCommentState state = new AbstractCommentState.SimpleCommentState();
                state.setArticleCommentId(globalId);
                add(state);
                s = state;
            }
            return s;
        }

        @Override
        public int size() {
            return protectedComments.size();
        }

        @Override
        public boolean isEmpty() {
            return protectedComments.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return protectedComments.contains(o);
        }

        @Override
        public Iterator<CommentState> iterator() {
            return protectedComments.iterator();
        }

        @Override
        public java.util.stream.Stream<CommentState> stream() {
            return protectedComments.stream();
        }

        @Override
        public Object[] toArray() {
            return protectedComments.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return protectedComments.toArray(a);
        }

        @Override
        public boolean add(CommentState s) {
            if (s instanceof AbstractCommentState) {
                AbstractCommentState state = (AbstractCommentState) s;
                state.setProtectedArticleState(AbstractArticleState.this);
            }
            return protectedComments.add(s);
        }

        @Override
        public boolean remove(Object o) {
            if (o instanceof AbstractCommentState) {
                AbstractCommentState s = (AbstractCommentState) o;
                s.setProtectedArticleState(null);
            }
            return protectedComments.remove(o);
        }

        @Override
        public boolean removeState(CommentState s) {
            return remove(s);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return protectedComments.contains(c);
        }

        @Override
        public boolean addAll(Collection<? extends CommentState> c) {
            return protectedComments.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return protectedComments.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return protectedComments.retainAll(c);
        }

        @Override
        public void clear() {
            protectedComments.clear();
        }
    }


}

