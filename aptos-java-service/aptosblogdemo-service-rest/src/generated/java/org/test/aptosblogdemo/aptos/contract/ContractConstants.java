// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosblogdemo.aptos.contract;

public class ContractConstants {
    public static final String RESOURCE_ACCOUNT_ADDRESS = "RESOURCE_ACCOUNT_ADDRESS";
    public static final String RESOURCE_ACCOUNT_MODULE_RESOURCE_ACCOUNT = "resource_account::ResourceAccount";

    public static final String CONTRACT_NAMED_ADDRESS = "aptos_blog_demo";

    public static final String ARTICLE_MODULE_ARTICLE_CREATED = "article::ArticleCreated";
    public static final String ARTICLE_MODULE_ARTICLE_CREATED_HANDLE_FIELD = "article_created_handle";

    public static final String ARTICLE_MODULE_ARTICLE_UPDATED = "article::ArticleUpdated";
    public static final String ARTICLE_MODULE_ARTICLE_UPDATED_HANDLE_FIELD = "article_updated_handle";

    public static final String ARTICLE_MODULE_ARTICLE_DELETED = "article::ArticleDeleted";
    public static final String ARTICLE_MODULE_ARTICLE_DELETED_HANDLE_FIELD = "article_deleted_handle";

    public static final String ARTICLE_MODULE_COMMENT_ADDED = "article::CommentAdded";
    public static final String ARTICLE_MODULE_COMMENT_ADDED_HANDLE_FIELD = "comment_added_handle";

    public static final String ARTICLE_MODULE_COMMENT_UPDATED = "article::CommentUpdated";
    public static final String ARTICLE_MODULE_COMMENT_UPDATED_HANDLE_FIELD = "comment_updated_handle";

    public static final String ARTICLE_MODULE_COMMENT_REMOVED = "article::CommentRemoved";
    public static final String ARTICLE_MODULE_COMMENT_REMOVED_HANDLE_FIELD = "comment_removed_handle";

    public static final String BLOG_MODULE_BLOG_CREATED = "blog::BlogCreated";
    public static final String BLOG_MODULE_BLOG_CREATED_HANDLE_FIELD = "blog_created_handle";

    public static final String BLOG_MODULE_ARTICLE_ADDED_TO_BLOG = "blog::ArticleAddedToBlog";
    public static final String BLOG_MODULE_ARTICLE_ADDED_TO_BLOG_HANDLE_FIELD = "article_added_to_blog_handle";

    public static final String BLOG_MODULE_ARTICLE_REMOVED_FROM_BLOG = "blog::ArticleRemovedFromBlog";
    public static final String BLOG_MODULE_ARTICLE_REMOVED_FROM_BLOG_HANDLE_FIELD = "article_removed_from_blog_handle";

    public static final String BLOG_MODULE_DONATION_RECEIVED = "blog::DonationReceived";
    public static final String BLOG_MODULE_DONATION_RECEIVED_HANDLE_FIELD = "donation_received_handle";

    public static final String BLOG_MODULE_VAULT_WITHDRAWN = "blog::VaultWithdrawn";
    public static final String BLOG_MODULE_VAULT_WITHDRAWN_HANDLE_FIELD = "vault_withdrawn_handle";

    public static final String BLOG_MODULE_BLOG_UPDATED = "blog::BlogUpdated";
    public static final String BLOG_MODULE_BLOG_UPDATED_HANDLE_FIELD = "blog_updated_handle";

    public static final String BLOG_MODULE_BLOG_DELETED = "blog::BlogDeleted";
    public static final String BLOG_MODULE_BLOG_DELETED_HANDLE_FIELD = "blog_deleted_handle";

    public static final String ARTICLE_MODULE_ARTICLE = "article::Article";

    public static final String ARTICLE_ID_TYPE = "AptosObjectID";

    public static final String COMMENT_MODULE_COMMENT = "comment::Comment";

    public static final String COMMENT_ID_TYPE = "u64";

    public static final String COMMENT_TABLE_ITEM_ADDED = "article::CommentTableItemAdded";

    public static final String COMMENT_TABLE_ITEM_ADDED_HANDLE_FIELD = "comment_table_item_added_handle";

    public static final String BLOG_MODULE_BLOG = "blog::Blog";

    public static final String BLOG_ID_TYPE = "address";

    public static final String ARTICLE_MODULE_TABLES = "article::Tables";

    public static final String ARTICLE_MODULE_EVENTS = "article::Events";

    public static final String BLOG_MODULE_EVENTS = "blog::Events";

    public static String toNumericalAddressType(String type, String contractAddress) {
        if (type.startsWith(CONTRACT_NAMED_ADDRESS + "::")) {
            return contractAddress + type.substring(CONTRACT_NAMED_ADDRESS.length());
        }
        return type;
    }

}
