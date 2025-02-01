package org.test.aptosblogdemo.aptos.contract.event;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

import java.util.Objects;

public class OnChainEventRetrieved<T> implements ResolvableTypeProvider {
    private final T event;
    private final Class<T> eventType;

    public OnChainEventRetrieved(T event, Class<T> eventType) {
        this.event = event;
        this.eventType = eventType;
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(
                getClass(),
                ResolvableType.forClass(eventType)
        );
    }

    public T getEvent() {
        return event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OnChainEventRetrieved<?> that = (OnChainEventRetrieved<?>) o;
        return Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(event);
    }

    @Override
    public String toString() {
        return "OnChainEventRetrieved{" +
                "event=" + event +
                ", eventType=" + eventType +
                '}';
    }
}
