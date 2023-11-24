// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.blog;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.Event;

public interface BlogEvent extends Event, AptosEvent, HasStatus {

    interface SqlBlogEvent extends BlogEvent {
        BlogEventId getBlogEventId();

        boolean getEventReadOnly();

        void setEventReadOnly(boolean readOnly);
    }

    interface BlogCreated extends BlogEvent {
        String getName();

        void setName(String value);

        Boolean getIsEmergency();

        void setIsEmergency(Boolean value);

    }

    interface ArticleAddedToBlog extends BlogEvent {
        BigInteger getArticleId();

        void setArticleId(BigInteger value);

    }

    interface ArticleRemovedFromBlog extends BlogEvent {
        BigInteger getArticleId();

        void setArticleId(BigInteger value);

    }

    interface DonationReceived extends BlogEvent {
        BigInteger getAmount();

        void setAmount(BigInteger value);

    }

    interface VaultWithdrawn extends BlogEvent {
        BigInteger getAmount();

        void setAmount(BigInteger value);

    }

    interface BlogUpdated extends BlogEvent {
        String getName();

        void setName(String value);

        BigInteger[] getArticles();

        void setArticles(BigInteger[] value);

        Boolean getIsEmergency();

        void setIsEmergency(Boolean value);

    }

    interface BlogDeleted extends BlogEvent {
    }

    String getAccountAddress();

    //void setAccountAddress(String accountAddress);

    BigInteger getVersion();
    
    //void setVersion(BigInteger version);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    String getCommandId();

    void setCommandId(String commandId);


}

