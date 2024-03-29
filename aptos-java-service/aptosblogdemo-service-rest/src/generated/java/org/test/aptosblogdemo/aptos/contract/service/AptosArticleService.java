// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.service;

import com.github.wubuku.aptos.utils.NodeApiClient;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.domain.article.*;
import org.test.aptosblogdemo.aptos.contract.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.*;
import java.util.*;
import java.math.*;

@Service
public class AptosArticleService {

    @Autowired
    private ArticleStateRepository articleStateRepository;

    @Autowired
    private CommentTableItemAddedRepository commentTableItemAddedRepository;
    @Autowired
    private ArticleEventService articleEventService;

    private AptosArticleStateRetriever aptosArticleStateRetriever;

    @Autowired
    public AptosArticleService(
        @Value("${aptos.contract.address}")
        String aptosContractAddress,
        NodeApiClient aptosNodeApiClient,
        AptosAccountRepository aptosAccountRepository
    ) {
        this.aptosArticleStateRetriever = new AptosArticleStateRetriever(
                aptosNodeApiClient,
                aptosContractAddress,
                aptosAccountRepository,
                articleId -> {
                    ArticleState.MutableArticleState s = new AbstractArticleState.SimpleArticleState();
                    s.setArticleId(articleId);
                    return s;
                },
                (articleState, commentSeqId) -> (CommentState.MutableCommentState)
                        ((EntityStateCollection.ModifiableEntityStateCollection<BigInteger, CommentState>) articleState.getComments()).getOrAddDefault(commentSeqId),
                articleId -> {
                    articleEventService.pullCommentTableItemAddedEvents();
                    return commentTableItemAddedRepository.findByArticleCommentId_ArticleId(articleId).stream()
                            .map(i -> i.getArticleCommentId().getCommentSeqId()).collect(Collectors.toList());
                }
        );
    }

    @Transactional
    public void updateArticleState(BigInteger articleId) {
        ArticleState articleState = aptosArticleStateRetriever.retrieveArticleState(articleId);
        if (articleState == null) {
            return;
        }
        articleStateRepository.merge(articleState);
    }

    @Transactional
    public void deleteArticle(BigInteger articleId) {
        ArticleState.MutableArticleState s = (ArticleState.MutableArticleState) articleStateRepository.get(articleId, true);
        if (s != null) {
            s.setDeleted(true);
            articleStateRepository.merge(s);
        }
    }

}

