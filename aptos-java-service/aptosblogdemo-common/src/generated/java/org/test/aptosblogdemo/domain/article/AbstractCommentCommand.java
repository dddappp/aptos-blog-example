// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.article;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.domain.AbstractCommand;

public abstract class AbstractCommentCommand extends AbstractCommand implements CommentCommand {

    private BigInteger commentSeqId;

    public BigInteger getCommentSeqId()
    {
        return this.commentSeqId;
    }

    public void setCommentSeqId(BigInteger commentSeqId)
    {
        this.commentSeqId = commentSeqId;
    }

    private String articleId;

    public String getArticleId()
    {
        return this.articleId;
    }

    public void setArticleId(String articleId)
    {
        this.articleId = articleId;
    }


}

