module aptos_blog_example::article_delete_logic {
    use aptos_blog_example::article;

    friend aptos_blog_example::article_aggregate;

    public(friend) fun verify(
        account: &signer,
        article: &article::Article,
    ): article::ArticleDeleted {
        let _ = account;
        article::new_article_deleted(
            article,
        )
    }

    public(friend) fun mutate(
        article_deleted: &article::ArticleDeleted,
        article: article::Article,
    ): article::Article {
        let article_id = article::article_id(&article);
        let _ = article_id;
        let _ = article_deleted;
        article
    }

}
