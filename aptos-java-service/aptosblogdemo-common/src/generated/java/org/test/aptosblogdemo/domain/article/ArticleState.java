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

public interface ArticleState extends VersionedAptosMoveObject
{
    Long VERSION_ZERO = 0L;

    Long VERSION_NULL = VERSION_ZERO - 1;

    String getId();

    String getTitle();

    String getBody();

    String getOwner();

    Long getOffChainVersion();

    String getCreatedBy();

    Date getCreatedAt();

    String getUpdatedBy();

    Date getUpdatedAt();

    Boolean getActive();

    Boolean getDeleted();

    Set<String> getTags();

    EntityStateCollection<BigInteger, CommentState> getComments();

    interface MutableArticleState extends ArticleState, VersionedAptosMoveObject.MutableVersionedAptosMoveObject {
        void setId(String id);

        void setTitle(String title);

        void setBody(String body);

        void setOwner(String owner);

        void setOffChainVersion(Long offChainVersion);

        void setCreatedBy(String createdBy);

        void setCreatedAt(Date createdAt);

        void setUpdatedBy(String updatedBy);

        void setUpdatedAt(Date updatedAt);

        void setActive(Boolean active);

        void setDeleted(Boolean deleted);

        void setTags(Set<String> tags);


        void mutate(Event e);

        //void when(ArticleEvent.ArticleStateCreated e);

        //void when(ArticleEvent.ArticleStateMergePatched e);

        //void when(ArticleEvent.ArticleStateDeleted e);
    }

    interface SqlArticleState extends MutableArticleState {

        boolean isStateUnsaved();

        boolean getForReapplying();
    }
}

