// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.article;

import java.util.*;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosblogdemo.domain.*;

public interface ArticleStateRepository {
    ArticleState get(BigInteger id, boolean nullAllowed);

    void save(ArticleState state);

    void merge(ArticleState detached);
}

