module aptos_blog_demo::article_update_logic {
    use aptos_blog_demo::article;
    use aptos_blog_demo::article_updated;
    use std::string::String;

    friend aptos_blog_demo::article_aggregate;

    public(friend) fun verify(
        account: &signer,
        title: String,
        body: String,
        owner: address,
        id: address,
        article: &article::Article,
    ): article::ArticleUpdated {
        let _ = account;
        article::new_article_updated(
            id,
            article,
            title,
            body,
            owner,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        article_updated: &article::ArticleUpdated,
        _id: address,
        article: article::Article,
    ): article::Article {
        let title = article_updated::title(article_updated);
        let body = article_updated::body(article_updated);
        let owner = article_updated::owner(article_updated);
        article::set_title(&mut article, title);
        article::set_body(&mut article, body);
        article::set_owner(&mut article, owner);
        article
    }

}
