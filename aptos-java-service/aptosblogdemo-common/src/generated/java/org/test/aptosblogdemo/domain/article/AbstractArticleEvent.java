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
import org.test.aptosblogdemo.domain.AbstractEvent;

public abstract class AbstractArticleEvent extends AbstractEvent implements ArticleEvent.SqlArticleEvent, AptosEvent.MutableAptosEvent, HasStatus.MutableHasStatus {
    private ArticleEventId articleEventId = new ArticleEventId();

    public ArticleEventId getArticleEventId() {
        return this.articleEventId;
    }

    public void setArticleEventId(ArticleEventId eventId) {
        this.articleEventId = eventId;
    }
    
    public String getId() {
        return getArticleEventId().getId();
    }

    public void setId(String id) {
        getArticleEventId().setId(id);
    }

    private boolean eventReadOnly;

    public boolean getEventReadOnly() { return this.eventReadOnly; }

    public void setEventReadOnly(boolean readOnly) { this.eventReadOnly = readOnly; }

    public BigInteger getVersion() {
        return getArticleEventId().getVersion();
    }
    
    public void setVersion(BigInteger version) {
        getArticleEventId().setVersion(version);
    }

    private BigInteger aptosEventVersion;

    public BigInteger getAptosEventVersion() {
        return this.aptosEventVersion;
    }
    
    public void setAptosEventVersion(BigInteger aptosEventVersion) {
        this.aptosEventVersion = aptosEventVersion;
    }

    private BigInteger aptosEventSequenceNumber;

    public BigInteger getAptosEventSequenceNumber() {
        return this.aptosEventSequenceNumber;
    }
    
    public void setAptosEventSequenceNumber(BigInteger aptosEventSequenceNumber) {
        this.aptosEventSequenceNumber = aptosEventSequenceNumber;
    }

    private String aptosEventType;

    public String getAptosEventType() {
        return this.aptosEventType;
    }
    
    public void setAptosEventType(String aptosEventType) {
        this.aptosEventType = aptosEventType;
    }

    private AptosEventGuid aptosEventGuid;

    public AptosEventGuid getAptosEventGuid() {
        return this.aptosEventGuid;
    }
    
    public void setAptosEventGuid(AptosEventGuid aptosEventGuid) {
        this.aptosEventGuid = aptosEventGuid;
    }

    private String status;

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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


    private String commandId;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    private String commandType;

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    protected AbstractArticleEvent() {
    }

    protected AbstractArticleEvent(ArticleEventId eventId) {
        this.articleEventId = eventId;
    }

    protected CommentEventDao getCommentEventDao() {
        return (CommentEventDao)ApplicationContext.current.get("commentEventDao");
    }

    protected CommentEventId newCommentEventId(BigInteger commentSeqId)
    {
        CommentEventId eventId = new CommentEventId(this.getArticleEventId().getId(), 
            commentSeqId, 
            this.getArticleEventId().getVersion());
        return eventId;
    }

    protected void throwOnInconsistentEventIds(CommentEvent.SqlCommentEvent e)
    {
        throwOnInconsistentEventIds(this, e);
    }

    public static void throwOnInconsistentEventIds(ArticleEvent.SqlArticleEvent oe, CommentEvent.SqlCommentEvent e)
    {
        if (!oe.getArticleEventId().getId().equals(e.getCommentEventId().getArticleId()))
        { 
            throw DomainError.named("inconsistentEventIds", "Outer Id Id %1$s but inner id ArticleId %2$s", 
                oe.getArticleEventId().getId(), e.getCommentEventId().getArticleId());
        }
    }


    public abstract String getEventClass();

    public static class ArticleClobEvent extends AbstractArticleEvent {

        protected Map<String, Object> getDynamicProperties() {
            return dynamicProperties;
        }

        protected void setDynamicProperties(Map<String, Object> dynamicProperties) {
            if (dynamicProperties == null) {
                throw new IllegalArgumentException("dynamicProperties is null.");
            }
            this.dynamicProperties = dynamicProperties;
        }

        private Map<String, Object> dynamicProperties = new HashMap<>();

        protected String getDynamicPropertiesLob() {
            return ApplicationContext.current.getClobConverter().toString(getDynamicProperties());
        }

        protected void setDynamicPropertiesLob(String text) {
            getDynamicProperties().clear();
            Map<String, Object> ps = ApplicationContext.current.getClobConverter().parseLobProperties(text);
            if (ps != null) {
                for (Map.Entry<String, Object> kv : ps.entrySet()) {
                    getDynamicProperties().put(kv.getKey(), kv.getValue());
                }
            }
        }

        @Override
        public String getEventClass() {
            return "ArticleClobEvent";
        }

    }

    public static class AddTagEvent extends ArticleClobEvent implements ArticleEvent.AddTagEvent {

        @Override
        public String getEventClass() {
            return "AddTagEvent";
        }

        public String getTag() {
            Object val = getDynamicProperties().get("tag");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setTag(String value) {
            getDynamicProperties().put("tag", value);
        }

    }

    public static class ArticleCreated extends ArticleClobEvent implements ArticleEvent.ArticleCreated {

        @Override
        public String getEventClass() {
            return "ArticleCreated";
        }

        public String getTitle() {
            Object val = getDynamicProperties().get("title");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setTitle(String value) {
            getDynamicProperties().put("title", value);
        }

        public String getBody() {
            Object val = getDynamicProperties().get("body");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setBody(String value) {
            getDynamicProperties().put("body", value);
        }

        public String getOwner() {
            Object val = getDynamicProperties().get("owner");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setOwner(String value) {
            getDynamicProperties().put("owner", value);
        }

    }

    public static class ArticleUpdated extends ArticleClobEvent implements ArticleEvent.ArticleUpdated {

        @Override
        public String getEventClass() {
            return "ArticleUpdated";
        }

        public String getTitle() {
            Object val = getDynamicProperties().get("title");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setTitle(String value) {
            getDynamicProperties().put("title", value);
        }

        public String getBody() {
            Object val = getDynamicProperties().get("body");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setBody(String value) {
            getDynamicProperties().put("body", value);
        }

        public String getOwner() {
            Object val = getDynamicProperties().get("owner");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setOwner(String value) {
            getDynamicProperties().put("owner", value);
        }

        public String[] getTags() {
            Object val = getDynamicProperties().get("tags");
            if (val instanceof String[]) {
                return (String[]) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String[].class);
        }

        public void setTags(String[] value) {
            getDynamicProperties().put("tags", value);
        }

    }

    public static class ArticleDeleted extends ArticleClobEvent implements ArticleEvent.ArticleDeleted {

        @Override
        public String getEventClass() {
            return "ArticleDeleted";
        }

    }

    public static class CommentAdded extends ArticleClobEvent implements ArticleEvent.CommentAdded {

        @Override
        public String getEventClass() {
            return "CommentAdded";
        }

        public BigInteger getCommentSeqId() {
            Object val = getDynamicProperties().get("commentSeqId");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setCommentSeqId(BigInteger value) {
            getDynamicProperties().put("commentSeqId", value);
        }

        public String getCommenter() {
            Object val = getDynamicProperties().get("commenter");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setCommenter(String value) {
            getDynamicProperties().put("commenter", value);
        }

        public String getBody() {
            Object val = getDynamicProperties().get("body");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setBody(String value) {
            getDynamicProperties().put("body", value);
        }

        public String getOwner() {
            Object val = getDynamicProperties().get("owner");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setOwner(String value) {
            getDynamicProperties().put("owner", value);
        }

    }

    public static class CommentUpdated extends ArticleClobEvent implements ArticleEvent.CommentUpdated {

        @Override
        public String getEventClass() {
            return "CommentUpdated";
        }

        public BigInteger getCommentSeqId() {
            Object val = getDynamicProperties().get("commentSeqId");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setCommentSeqId(BigInteger value) {
            getDynamicProperties().put("commentSeqId", value);
        }

        public String getCommenter() {
            Object val = getDynamicProperties().get("commenter");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setCommenter(String value) {
            getDynamicProperties().put("commenter", value);
        }

        public String getBody() {
            Object val = getDynamicProperties().get("body");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setBody(String value) {
            getDynamicProperties().put("body", value);
        }

        public String getOwner() {
            Object val = getDynamicProperties().get("owner");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setOwner(String value) {
            getDynamicProperties().put("owner", value);
        }

    }

    public static class CommentRemoved extends ArticleClobEvent implements ArticleEvent.CommentRemoved {

        @Override
        public String getEventClass() {
            return "CommentRemoved";
        }

        public BigInteger getCommentSeqId() {
            Object val = getDynamicProperties().get("commentSeqId");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setCommentSeqId(BigInteger value) {
            getDynamicProperties().put("commentSeqId", value);
        }

    }


}

