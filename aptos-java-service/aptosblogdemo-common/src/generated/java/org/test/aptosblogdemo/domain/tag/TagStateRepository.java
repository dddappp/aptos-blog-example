// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.tag;

import java.util.*;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosblogdemo.domain.*;

public interface TagStateRepository {
    TagState get(String id, boolean nullAllowed);

    void save(TagState state);

    void merge(TagState detached);
}

