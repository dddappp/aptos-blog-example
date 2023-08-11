package org.test.aptosblogdemo.specialization;


public interface AggregateEventListener<TA, TS> {

    void eventAppended(AggregateEvent<TA, TS> e);

}
