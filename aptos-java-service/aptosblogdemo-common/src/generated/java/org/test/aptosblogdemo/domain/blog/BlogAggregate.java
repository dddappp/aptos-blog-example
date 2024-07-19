// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.blog;

import java.util.List;
import java.util.Date;
import java.math.BigInteger;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.Event;
import org.test.aptosblogdemo.domain.Command;

public interface BlogAggregate {
    BlogState getState();

    List<Event> getChanges();

    void create(String name, Boolean isEmergency, Long offChainVersion, String commandId, String requesterId, BlogCommands.Create c);

    void addArticle(String articleId, Long offChainVersion, String commandId, String requesterId, BlogCommands.AddArticle c);

    void removeArticle(String articleId, Long offChainVersion, String commandId, String requesterId, BlogCommands.RemoveArticle c);

    void initFaVault(String metadata, Long offChainVersion, String commandId, String requesterId, BlogCommands.InitFaVault c);

    void update(String name, String[] articles, Boolean isEmergency, String faVault, Long offChainVersion, String commandId, String requesterId, BlogCommands.Update c);

    void delete(Long offChainVersion, String commandId, String requesterId, BlogCommands.Delete c);

    void throwOnInvalidStateTransition(Command c);
}

