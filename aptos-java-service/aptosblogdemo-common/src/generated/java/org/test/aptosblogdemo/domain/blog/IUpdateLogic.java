// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.blog;

import java.math.*;
import java.util.*;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.*;

public interface IUpdateLogic {

    BlogEvent.BlogUpdated verify(java.util.function.Supplier<BlogEvent.BlogUpdated> eventFactory, BlogState blogState, String name, String[] articles, Boolean isEmergency, String faVault, VerificationContext verificationContext);

    BlogState mutate(BlogState blogState, String name, String[] articles, Boolean isEmergency, String faVault, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<BlogState, BlogState.MutableBlogState> mutationContext);
}
