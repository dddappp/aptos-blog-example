// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain;

import java.io.Serializable;
import org.test.aptosblogdemo.domain.*;

public class ObjectExtendRef implements Serializable {
    private String self;

    public String getSelf()
    {
        return this.self;
    }

    public void setSelf(String self)
    {
        this.self = self;
    }

    public ObjectExtendRef()
    {
    }

    public ObjectExtendRef(String self)
    {
        this.self = self;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        ObjectExtendRef other = (ObjectExtendRef)obj;
        return true 
            && (self == other.self || (self != null && self.equals(other.self)))
            ;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        if (this.self != null) {
            hash += 13 * this.self.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return "ObjectExtendRef{" +
                "self=" + '\'' + self + '\'' +
                '}';
    }


}

