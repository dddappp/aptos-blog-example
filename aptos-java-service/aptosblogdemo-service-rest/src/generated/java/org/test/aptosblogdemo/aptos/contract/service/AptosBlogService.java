// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.service;

import com.github.wubuku.aptos.utils.NodeApiClient;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.domain.blog.*;
import org.test.aptosblogdemo.aptos.contract.repository.*;
import org.test.aptosblogdemo.aptos.contract.event.OnChainStateRetrieved;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.ApplicationEventPublisher;

import java.util.stream.*;
import java.util.*;
import java.math.*;

@Service
public class AptosBlogService {

    @Autowired
    private BlogStateRepository blogStateRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    private AptosBlogStateRetriever aptosBlogStateRetriever;

    @Autowired
    public AptosBlogService(
        @Value("${aptos.contract.address}")
        String aptosContractAddress,
        NodeApiClient aptosNodeApiClient,
        AptosAccountRepository aptosAccountRepository
    ) {
        this.aptosBlogStateRetriever = new AptosBlogStateRetriever(
                aptosNodeApiClient,
                aptosContractAddress,
                aptosAccountRepository,
                accountAddress -> {
                    BlogState.MutableBlogState s = new AbstractBlogState.SimpleBlogState();
                    s.setAccountAddress(accountAddress);
                    return s;
                }
        );
    }

    @Transactional
    public void updateBlogState(String accountAddress) {
        BlogState blogState = aptosBlogStateRetriever.retrieveBlogState(accountAddress);
        if (blogState == null) {
            return;
        }
        blogStateRepository.merge(blogState);
        applicationEventPublisher.publishEvent(new OnChainStateRetrieved<>(blogState, BlogState.class));
    }

    @Transactional
    public void deleteBlog(String accountAddress) {
        BlogState.MutableBlogState s = (BlogState.MutableBlogState) blogStateRepository.get(accountAddress, true);
        if (s != null) {
            s.setDeleted(true);
            blogStateRepository.merge(s);
        }
    }

}

