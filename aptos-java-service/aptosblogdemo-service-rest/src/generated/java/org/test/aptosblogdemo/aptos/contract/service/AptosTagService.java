// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.service;

import com.github.wubuku.aptos.utils.NodeApiClient;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.domain.tag.*;
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
public class AptosTagService {

    @Autowired
    private TagStateRepository tagStateRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    private AptosTagStateRetriever aptosTagStateRetriever;

    @Autowired
    public AptosTagService(
        @Value("${aptos.contract.address}")
        String aptosContractAddress,
        NodeApiClient aptosNodeApiClient,
        AptosAccountRepository aptosAccountRepository
    ) {
        this.aptosTagStateRetriever = new AptosTagStateRetriever(
                aptosNodeApiClient,
                aptosContractAddress,
                aptosAccountRepository,
                tagId -> {
                    TagState.MutableTagState s = new AbstractTagState.SimpleTagState();
                    s.setTagId(tagId);
                    return s;
                }
        );
    }

    @Transactional
    public void updateTagState(String tagId) {
        TagState tagState = aptosTagStateRetriever.retrieveTagState(tagId);
        if (tagState == null) {
            return;
        }
        tagStateRepository.merge(tagState);
        applicationEventPublisher.publishEvent(new OnChainStateRetrieved<>(tagState, TagState.class));
    }

}

