module aptos_blog_example::article_create_logic {
    use aptos_blog_example::article;
    use aptos_blog_example::article_created;
    use std::string::String;

    friend aptos_blog_example::article_aggregate;

    public(friend) fun verify(
        account: &signer,
        title: String,
        body: String,
        owner: address,
    ): article::ArticleCreated {
        let _ = account;
        article::new_article_created(
            title,
            body,
            owner,
        )
    }

    public(friend) fun mutate(
        article_created: &article::ArticleCreated,
    ): article::Article {
        let title = article_created::title(article_created);
        let body = article_created::body(article_created);
        let owner = article_created::owner(article_created);
        let article = article::create_article(
            title,
            body,
            owner,
        );
        article
    }

}
