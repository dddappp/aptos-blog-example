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
import org.test.aptosblogdemo.specialization.Event;

public interface CommentState
{
    Long VERSION_ZERO = 0L;

    Long VERSION_NULL = VERSION_ZERO - 1;

    BigInteger getCommentSeqId();

    String getCommenter();

    String getBody();

    String getOwner();

    Long getOffChainVersion();

    String getCreatedBy();

    Date getCreatedAt();

    String getUpdatedBy();

    Date getUpdatedAt();

    Boolean getActive();

    Boolean getDeleted();

    String getArticleId();

    interface MutableCommentState extends CommentState {
        void setCommentSeqId(BigInteger commentSeqId);

        void setCommenter(String commenter);

        void setBody(String body);

        void setOwner(String owner);

        void setOffChainVersion(Long offChainVersion);

        void setCreatedBy(String createdBy);

        void setCreatedAt(Date createdAt);

        void setUpdatedBy(String updatedBy);

        void setUpdatedAt(Date updatedAt);

        void setActive(Boolean active);

        void setDeleted(Boolean deleted);

        void setArticleId(String articleId);


        void mutate(Event e);

        //void when(CommentEvent.CommentStateCreated e);

        //void when(CommentEvent.CommentStateMergePatched e);

        //void when(CommentEvent.CommentStateRemoved e);
    }

    interface SqlCommentState extends MutableCommentState {
        ArticleCommentId getArticleCommentId();

        void setArticleCommentId(ArticleCommentId articleCommentId);


        boolean isStateUnsaved();

        boolean getForReapplying();
    }
}

