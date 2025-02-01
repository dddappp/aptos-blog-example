package org.test.aptosblogdemo.aptos.contract.event;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

import java.util.Objects;

public class OnChainStateRetrieved<T> implements ResolvableTypeProvider {
    private final T state;
    private final Class<T> stateType;

    public OnChainStateRetrieved(T state, Class<T> stateType) {
        this.state = state;
        this.stateType = stateType;
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(
                getClass(),
                ResolvableType.forClass(stateType)
        );
    }

    public T getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OnChainStateRetrieved<?> that = (OnChainStateRetrieved<?>) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(state);
    }

    @Override
    public String toString() {
        return "OnChainStateRetrieved{" +
                "state=" + state +
                ", stateType=" + stateType +
                '}';
    }
}
