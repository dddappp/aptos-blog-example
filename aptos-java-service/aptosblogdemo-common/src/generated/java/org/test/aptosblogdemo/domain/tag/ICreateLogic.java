// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.tag;

import java.math.*;
import java.util.*;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.*;

public interface ICreateLogic {

    TagEvent.TagCreated verify(java.util.function.Supplier<TagEvent.TagCreated> eventFactory, TagState tagState, String name, VerificationContext verificationContext);

    TagState mutate(TagState tagState, String name, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<TagState, TagState.MutableTagState> mutationContext);
}