// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.service;

import com.github.wubuku.aptos.bean.Event;
import com.github.wubuku.aptos.utils.NodeApiClient;

import org.test.aptosblogdemo.domain.tag.AbstractTagEvent;
import org.test.aptosblogdemo.aptos.contract.ContractConstants;
import org.test.aptosblogdemo.aptos.contract.DomainBeanUtils;
import org.test.aptosblogdemo.aptos.contract.AptosAccount;

import org.test.aptosblogdemo.aptos.contract.tag.TagCreated;
import org.test.aptosblogdemo.aptos.contract.repository.TagEventRepository;
import org.test.aptosblogdemo.aptos.contract.repository.AptosAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.*;
import java.util.*;


@Service
public class TagEventService {

    @Value("${aptos.contract.address}")
    private String aptosContractAddress;

    @Autowired
    private AptosAccountRepository aptosAccountRepository;

    @Autowired
    private NodeApiClient aptosNodeApiClient;

    @Autowired
    private TagEventRepository tagEventRepository;

    @Transactional
    public void updateStatusToProcessed(AbstractTagEvent event) {
        event.setStatus("D");
        tagEventRepository.save(event);
    }

    @Transactional
    public void pullTagCreatedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getTagCreatedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<TagCreated>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.TAG_MODULE_EVENTS,
                        ContractConstants.TAG_MODULE_TAG_CREATED_HANDLE_FIELD,
                        TagCreated.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<TagCreated> eventEnvelope : eventPage) {
                    saveTagCreated(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getTagCreatedEventNextCursor() {
        AbstractTagEvent.TagCreated lastEvent = tagEventRepository.findFirstTagCreatedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveTagCreated(Event<TagCreated> eventEnvelope) {
        AbstractTagEvent.TagCreated tagCreated = DomainBeanUtils.toTagCreated(eventEnvelope);
        if (tagEventRepository.findById(tagCreated.getTagEventId()).isPresent()) {
            return;
        }
        tagEventRepository.save(tagCreated);
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }
}