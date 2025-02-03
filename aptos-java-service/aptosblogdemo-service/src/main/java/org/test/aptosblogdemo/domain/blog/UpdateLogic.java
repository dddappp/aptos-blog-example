// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.blog;

import java.math.*;
import java.util.*;
import org.test.aptosblogdemo.domain.*;
import org.test.aptosblogdemo.specialization.*;
import org.springframework.stereotype.Component;


/**
 * Implementation of the Blog.Update command logic.
 */
@Component("(blog_Update)")
public class UpdateLogic implements IUpdateLogic {
    /**
     * Verifies the Blog.Update command by performing validation logic
     * before the state mutation. Creates and returns an event.
     * 
     * @param eventFactory The supplier that creates new BlogEvent.BlogUpdated events
     * @param blogState The current state of the Blog aggregate
     * @param name 
     * @param articles 
     * @param isEmergency 
     * @param faVault 
     * @param verificationContext The context information for the verification process
     * @return An event that will be applied to the current state to update the Blog
     */
    public BlogEvent.BlogUpdated verify(java.util.function.Supplier<BlogEvent.BlogUpdated> eventFactory, BlogState blogState, String name, String[] articles, Boolean isEmergency, String faVault, VerificationContext verificationContext) {
        BlogEvent.BlogUpdated e = eventFactory.get();
        // TODO: implement
        return e;
    }

    /**
     * Performs the state mutation operation of Blog.Update command.
     * Creates a mutable copy of the state, updates it with the new body text,
     * and returns the new state.
     * 
     * @param blogState The current immutable state of the Blog
     * @param name 
     * @param articles 
     * @param isEmergency 
     * @param faVault 
     * @param aptosEventVersion 
     * @param aptosEventSequenceNumber 
     * @param aptosEventType 
     * @param aptosEventGuid 
     * @param status 
     * @param mutationContext The context that provides functionality including creating mutable state
     * @return The new state of the Blog
     */
    public BlogState mutate(BlogState blogState, String name, String[] articles, Boolean isEmergency, String faVault, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<BlogState, BlogState.MutableBlogState> mutationContext) {
        BlogState.MutableBlogState s = mutationContext.createMutableState(blogState);
        // TODO: implement
        return s; // Return the updated state
    }
}
