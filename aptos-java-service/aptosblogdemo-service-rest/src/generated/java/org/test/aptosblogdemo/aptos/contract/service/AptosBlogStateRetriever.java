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
import org.test.aptosblogdemo.domain.blog.*;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.aptos.contract.Blog;

import java.io.IOException;
import java.math.*;
import java.util.*;
import java.util.function.*;


public class AptosBlogStateRetriever {

    private NodeApiClient aptosNodeApiClient;

    private String aptosContractAddress;

    private AptosAccountRepository aptosAccountRepository;

    private Function<String, BlogState.MutableBlogState> blogStateFactory;


    public AptosBlogStateRetriever(NodeApiClient aptosNodeApiClient,
                                    String aptosContractAddress,
                                    AptosAccountRepository aptosAccountRepository,
                                    Function<String, BlogState.MutableBlogState> blogStateFactory
    ) {
        this.aptosNodeApiClient = aptosNodeApiClient;
        this.aptosContractAddress = aptosContractAddress;
        this.aptosAccountRepository = aptosAccountRepository;
        this.blogStateFactory = blogStateFactory;
    }

    public BlogState retrieveBlogState(String accountAddress) {
        String resourceAccountAddress = getResourceAccountAddress();
        AccountResource<Blog> accountResource;
        try {
            accountResource = aptosNodeApiClient.getAccountResource(resourceAccountAddress,
                    this.aptosContractAddress + "::" + ContractConstants.BLOG_MODULE_BLOG,
                    Blog.class,
                    null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Blog blog = accountResource.getData();
        blog.setAccountAddress(resourceAccountAddress);
        return toBlogState(blog);
    }

    private BlogState toBlogState(Blog blog) {
        BlogState.MutableBlogState blogState = blogStateFactory.apply(blog.getAccountAddress());
        blogState.setVersion(blog.getVersion());
        blogState.setName(blog.getName());
        blogState.setArticles(new HashSet<>(Arrays.asList(blog.getArticles())));
        blogState.setVault(DomainBeanUtils.toCoin(blog.getVault()));
        blogState.setIsEmergency(blog.getIsEmergency());
        blogState.setFaVault(blog.getFaVault().getVec().size() == 0 ? null : blog.getFaVault().getVec().get(0).getInner());
        return blogState;
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT)
                .map(AptosAccount::getAddress).orElse(null);
    }

}

