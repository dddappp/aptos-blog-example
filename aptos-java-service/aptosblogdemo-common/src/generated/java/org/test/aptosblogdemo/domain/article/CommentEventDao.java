// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.article;

import java.util.Date;
import java.math.BigInteger;
import org.test.aptosblogdemo.domain.*;

public interface CommentEventDao {
    void save(CommentEvent e);

    Iterable<CommentEvent> findByArticleEventId(ArticleEventId articleEventId);

}

