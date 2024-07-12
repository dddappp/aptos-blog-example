// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.repository;

import org.test.aptosblogdemo.domain.article.ArticleCommentId;
import org.test.aptosblogdemo.aptos.contract.persistence.CommentTableItemAdded;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;
import java.util.List;

public interface CommentTableItemAddedRepository extends JpaRepository<CommentTableItemAdded, ArticleCommentId> {

    @Transactional(readOnly = true)
    List<CommentTableItemAdded> findByOrderByAptosEventSequenceNumber(Pageable pageable);

    @Transactional(readOnly = true)
    CommentTableItemAdded findFirstByOrderByAptosEventSequenceNumber();

    @Transactional(readOnly = true)
    List<CommentTableItemAdded> findByArticleCommentId_ArticleId(String articleId);

}
