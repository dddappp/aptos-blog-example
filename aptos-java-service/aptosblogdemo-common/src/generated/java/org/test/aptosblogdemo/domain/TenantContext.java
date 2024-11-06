// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain;

public class TenantContext {

    private TenantContext() {
    }

    private static ThreadLocal<String> currentTenantId = new ThreadLocal<>();

    public static String getTenantId() {
        return currentTenantId.get();
    }

    public static void setTenantId(String tenantId) {
        currentTenantId.set(tenantId);
    }

    public static void clear() {
        currentTenantId.set(null);
    }
}
