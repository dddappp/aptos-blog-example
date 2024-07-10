module aptos_blog_demo::article_delete_logic {
    use aptos_blog_demo::blog_aggregate;
    use aptos_blog_demo::article;

    friend aptos_blog_demo::article_aggregate;

    public(friend) fun verify(
        account: &signer,
        id: address,
        article: &article::Article,
    ): article::ArticleDeleted {
        let _ = account;
        article::new_article_deleted(
            id,
            article,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        article_deleted: &article::ArticleDeleted,
        id: address,
        article: article::Article,
    ): article::Article {
        let _ = article_deleted;
        blog_aggregate::remove_article(id);
        article
    }

}
