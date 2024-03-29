// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.article;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.Event;
import org.test.aptosblogdemo.domain.Command;

public interface ArticleApplicationService {
    void when(ArticleCommands.Create c);

    void when(ArticleCommands.Update c);

    void when(ArticleCommands.Delete c);

    void when(ArticleCommands.AddComment c);

    void when(ArticleCommands.UpdateComment c);

    void when(ArticleCommands.RemoveComment c);

    ArticleState get(BigInteger id);

    Iterable<ArticleState> getAll(Integer firstResult, Integer maxResults);

    Iterable<ArticleState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<ArticleState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<ArticleState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

    ArticleEvent getEvent(BigInteger articleId, long version);

    ArticleState getHistoryState(BigInteger articleId, long version);

    CommentState getComment(BigInteger articleId, BigInteger commentSeqId);

    Iterable<CommentState> getComments(BigInteger articleId, Criterion filter, List<String> orders);

}

