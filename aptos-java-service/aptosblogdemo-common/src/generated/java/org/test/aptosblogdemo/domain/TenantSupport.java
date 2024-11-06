// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain;

import java.util.*;

public class TenantSupport {

    public static final String SUPER_TENANT_ID = "*";

    private TenantSupport() {
    }

    private static final Map<String, String> tenantIdPropertyNameMap;

    private static final Set<String> typesWithIdStartsWithOrEndsWithTenantId;

    static {
        tenantIdPropertyNameMap = new HashMap<>();
        typesWithIdStartsWithOrEndsWithTenantId = new HashSet<>();
    }

    public static boolean hasTenantIdProperty(String typeName) {
        return tenantIdPropertyNameMap.containsKey(typeName);
    }

    public static String getTenantIdPropertyName(String typeName) {
        return tenantIdPropertyNameMap.get(typeName);
    }

    public static boolean idStartsWithOrEndsWithTenantId(String typeName) {
        return typesWithIdStartsWithOrEndsWithTenantId.contains(typeName);
    }
}
