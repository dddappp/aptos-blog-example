// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.wubuku.aptos.bean.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Article {

    private String id;

    private Long offChainVersion;

    private String title;

    private String body;

    private String owner;

    private String[] tags;

    private BigInteger version;

    private TableWithLength comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOffChainVersion() {
        return offChainVersion;
    }

    public void setOffChainVersion(Long offChainVersion) {
        this.offChainVersion = offChainVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public TableWithLength getComments() {
        return comments;
    }

    public void setComments(TableWithLength comments) {
        this.comments = comments;
    }

    private java.util.Map<String, Object> commentSeqIdGenerator;

    public java.util.Map<String, Object> getCommentSeqIdGenerator() {
        return commentSeqIdGenerator;
    }

    public void setCommentSeqIdGenerator(java.util.Map<String, Object> commentSeqIdGenerator) {
        this.commentSeqIdGenerator = commentSeqIdGenerator;
    }

    @Override
    public String toString() {
        return "Article{" +
                ", id=" + '\'' + id + '\'' +
                ", offChainVersion=" + offChainVersion +
                ", title=" + '\'' + title + '\'' +
                ", body=" + '\'' + body + '\'' +
                ", owner=" + '\'' + owner + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", version=" + version +
                ", comments=" + comments +
                '}';
    }

    public static class Tables {
        @JsonProperty("article_table")
        private Table articleTable;

        public Table getArticleTable() {
            return articleTable;
        }

        public void setArticleTable(Table articleTable) {
            this.articleTable = articleTable;
        }

        @Override
        public String toString() {
            return "Article.Tables{" +
                    "articleTable=" + articleTable +
                    '}';
        }
    }

}
