// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.service;


import com.github.wubuku.aptos.bean.AccountResource;
import com.github.wubuku.aptos.utils.*;
import org.test.aptosblogdemo.aptos.contract.AptosAccount;
import org.test.aptosblogdemo.aptos.contract.ContractConstants;
import org.test.aptosblogdemo.aptos.contract.DomainBeanUtils;
import org.test.aptosblogdemo.aptos.contract.repository.AptosAccountRepository;
import org.test.aptosblogdemo.domain.tag.*;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.aptos.contract.Tag;

import java.io.IOException;
import java.math.*;
import java.util.*;
import java.util.function.*;


public class AptosTagStateRetriever {

    private NodeApiClient aptosNodeApiClient;

    private String aptosContractAddress;

    private AptosAccountRepository aptosAccountRepository;

    private Function<String, TagState.MutableTagState> tagStateFactory;


    public AptosTagStateRetriever(NodeApiClient aptosNodeApiClient,
                                    String aptosContractAddress,
                                    AptosAccountRepository aptosAccountRepository,
                                    Function<String, TagState.MutableTagState> tagStateFactory
    ) {
        this.aptosNodeApiClient = aptosNodeApiClient;
        this.aptosContractAddress = aptosContractAddress;
        this.aptosAccountRepository = aptosAccountRepository;
        this.tagStateFactory = tagStateFactory;
    }

    public TagState retrieveTagState(String tagId) {
        AccountResource<Tag> accountResource;
        try {
            accountResource = aptosNodeApiClient.getAccountResource(tagId,
                    this.aptosContractAddress + "::" + ContractConstants.TAG_MODULE_TAG,
                    Tag.class,
                    null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Tag tag = accountResource.getData();
        if (tag == null) {
            return null;
        }
        tag.setTagId(tagId);
        return toTagState(tag);
    }

    private TagState toTagState(Tag tag) {
        TagState.MutableTagState tagState = tagStateFactory.apply(tag.getTagId());
        tagState.setVersion(tag.getVersion());
        tagState.setName(tag.getName());
        return tagState;
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }

}
