// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.blog;

import java.math.*;
import java.util.*;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.*;

public interface IRemoveArticleLogic {

    BlogEvent.ArticleRemovedFromBlog verify(java.util.function.Supplier<BlogEvent.ArticleRemovedFromBlog> eventFactory, BlogState blogState, String articleId, VerificationContext verificationContext);

    BlogState mutate(BlogState blogState, String articleId, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<BlogState, BlogState.MutableBlogState> mutationContext);
}
