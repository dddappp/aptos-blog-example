// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain;

import java.io.Serializable;
import java.math.BigInteger;
import org.test.aptosblogdemo.domain.*;

public class Coin implements Serializable {
    private BigInteger value;

    public BigInteger getValue()
    {
        return this.value;
    }

    public void setValue(BigInteger value)
    {
        this.value = value;
    }

    public Coin()
    {
    }

    public Coin(BigInteger value)
    {
        this.value = value;
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

        Coin other = (Coin)obj;
        return true 
            && (value == other.value || (value != null && value.equals(other.value)))
            ;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        if (this.value != null) {
            hash += 13 * this.value.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "value=" + value +
                '}';
    }


}

