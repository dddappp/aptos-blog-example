package org.test.aptosblogdemo.specialization;

public interface VerificationContext {

    Object getCommand();

    static VerificationContext of(Object cmd) {
        return new VerificationContext() {
            @Override
            public Object getCommand() {
                return cmd;
            }
        };
    }
}
