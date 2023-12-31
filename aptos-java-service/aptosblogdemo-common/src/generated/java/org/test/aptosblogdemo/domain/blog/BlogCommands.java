// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.domain.blog;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosblogdemo.domain.*;

public class BlogCommands {
    private BlogCommands() {
    }

    public static class Create extends AbstractBlogCommand implements BlogCommand {

        public String getCommandType() {
            return "Create";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Account Address
         */
        private String accountAddress;

        public String getAccountAddress() {
            return this.accountAddress;
        }

        public void setAccountAddress(String accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Name
         */
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * Is Emergency
         */
        private Boolean isEmergency;

        public Boolean getIsEmergency() {
            return this.isEmergency;
        }

        public void setIsEmergency(Boolean isEmergency) {
            this.isEmergency = isEmergency;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class AddArticle extends AbstractBlogCommand implements BlogCommand {

        public String getCommandType() {
            return "AddArticle";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Account Address
         */
        private String accountAddress;

        public String getAccountAddress() {
            return this.accountAddress;
        }

        public void setAccountAddress(String accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Article Id
         */
        private BigInteger articleId;

        public BigInteger getArticleId() {
            return this.articleId;
        }

        public void setArticleId(BigInteger articleId) {
            this.articleId = articleId;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class RemoveArticle extends AbstractBlogCommand implements BlogCommand {

        public String getCommandType() {
            return "RemoveArticle";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Account Address
         */
        private String accountAddress;

        public String getAccountAddress() {
            return this.accountAddress;
        }

        public void setAccountAddress(String accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Article Id
         */
        private BigInteger articleId;

        public BigInteger getArticleId() {
            return this.articleId;
        }

        public void setArticleId(BigInteger articleId) {
            this.articleId = articleId;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class Donate extends AbstractBlogCommand implements BlogCommand {

        public String getCommandType() {
            return "Donate";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Account Address
         */
        private String accountAddress;

        public String getAccountAddress() {
            return this.accountAddress;
        }

        public void setAccountAddress(String accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class Withdraw extends AbstractBlogCommand implements BlogCommand {

        public String getCommandType() {
            return "Withdraw";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Account Address
         */
        private String accountAddress;

        public String getAccountAddress() {
            return this.accountAddress;
        }

        public void setAccountAddress(String accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Amount
         */
        private BigInteger amount;

        public BigInteger getAmount() {
            return this.amount;
        }

        public void setAmount(BigInteger amount) {
            this.amount = amount;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class Update extends AbstractBlogCommand implements BlogCommand {

        public String getCommandType() {
            return "Update";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Account Address
         */
        private String accountAddress;

        public String getAccountAddress() {
            return this.accountAddress;
        }

        public void setAccountAddress(String accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Name
         */
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * Articles
         */
        private BigInteger[] articles;

        public BigInteger[] getArticles() {
            return this.articles;
        }

        public void setArticles(BigInteger[] articles) {
            this.articles = articles;
        }

        /**
         * Is Emergency
         */
        private Boolean isEmergency;

        public Boolean getIsEmergency() {
            return this.isEmergency;
        }

        public void setIsEmergency(Boolean isEmergency) {
            this.isEmergency = isEmergency;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class Delete extends AbstractBlogCommand implements BlogCommand {

        public String getCommandType() {
            return "Delete";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Account Address
         */
        private String accountAddress;

        public String getAccountAddress() {
            return this.accountAddress;
        }

        public void setAccountAddress(String accountAddress) {
            this.accountAddress = accountAddress;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

}

