// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.article;

import java.util.List;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.Event;
import org.test.aptosblogdemo.domain.Command;

public interface ArticleAggregate {
    ArticleState getState();

    List<Event> getChanges();

    void addTag(String tag, Long offChainVersion, String commandId, String requesterId, ArticleCommands.AddTag c);

    void create(String title, String body, String owner, Long offChainVersion, String commandId, String requesterId, ArticleCommands.Create c);

    void update(String title, String body, String owner, String[] tags, Long offChainVersion, String commandId, String requesterId, ArticleCommands.Update c);

    void delete(Long offChainVersion, String commandId, String requesterId, ArticleCommands.Delete c);

    void addComment(String commenter, String body, String owner, Long offChainVersion, String commandId, String requesterId, ArticleCommands.AddComment c);

    void updateComment(BigInteger commentSeqId, String commenter, String body, String owner, Long offChainVersion, String commandId, String requesterId, ArticleCommands.UpdateComment c);

    void removeComment(BigInteger commentSeqId, Long offChainVersion, String commandId, String requesterId, ArticleCommands.RemoveComment c);

    void throwOnInvalidStateTransition(Command c);
}

