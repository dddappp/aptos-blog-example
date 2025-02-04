// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract.blog;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.test.aptosblogdemo.aptos.contract.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BlogUpdated {

    private String accountAddress;

    private BigInteger version;

    private String name;

    private String[] articles;

    private Boolean isEmergency;

    private com.github.wubuku.aptos.bean.Option<com.github.wubuku.aptos.bean.AptosObject> faVault;

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getArticles() {
        return articles;
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }

    public Boolean getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(Boolean isEmergency) {
        this.isEmergency = isEmergency;
    }

    public com.github.wubuku.aptos.bean.Option<com.github.wubuku.aptos.bean.AptosObject> getFaVault() {
        return faVault;
    }

    public void setFaVault(com.github.wubuku.aptos.bean.Option<com.github.wubuku.aptos.bean.AptosObject> faVault) {
        this.faVault = faVault;
    }

    @Override
    public String toString() {
        return "BlogUpdated{" +
                "accountAddress=" + '\'' + accountAddress + '\'' +
                ", version=" + version +
                ", name=" + '\'' + name + '\'' +
                ", articles=" + Arrays.toString(articles) +
                ", isEmergency=" + isEmergency +
                ", faVault=" + faVault +
                '}';
    }

}
