// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.tag;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.test.aptosblogdemo.aptos.contract.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TagCreated {

    private com.github.wubuku.aptos.bean.Option<String> tagId;

    private String name;

    public com.github.wubuku.aptos.bean.Option<String> getTagId() {
        return tagId;
    }

    public void setTagId(com.github.wubuku.aptos.bean.Option<String> tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagCreated{" +
                "tagId=" + '\'' + tagId + '\'' +
                ", name=" + '\'' + name + '\'' +
                '}';
    }

}
