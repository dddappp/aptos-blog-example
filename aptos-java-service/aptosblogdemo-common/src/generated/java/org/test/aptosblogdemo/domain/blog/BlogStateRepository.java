// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.blog;

import java.util.*;
import org.dddml.support.criterion.Criterion;
import java.util.Date;
import java.math.BigInteger;
import org.test.aptosblogdemo.domain.*;

public interface BlogStateRepository {
    BlogState get(String id, boolean nullAllowed);

    void save(BlogState state);

    void merge(BlogState detached);
}

