module aptos_blog_demo::article_update_logic {
    use aptos_blog_demo::article;
    use aptos_blog_demo::article_updated;
    use aptos_blog_demo::tag::Tag;
    use aptos_framework::object::Object;
    use std::string::String;
    use std::vector;

    friend aptos_blog_demo::article_aggregate;

    public(friend) fun verify(
        account: &signer,
        title: String,
        body: String,
        owner: address,
        tags: vector<Object<Tag>>,
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
            tags,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        article_updated: &article::ArticleUpdated,
        id: address,
        article: article::Article,
    ): article::Article {
        let title = article_updated::title(article_updated);
        let body = article_updated::body(article_updated);
        let owner = article_updated::owner(article_updated);
        let tags = article_updated::tags(article_updated);
        let _ = id;
        article::set_title(&mut article, title);
        article::set_body(&mut article, body);
        article::set_owner(&mut article, owner);
        let mut_tags = article::borrow_mut_tags(&mut article);
        vector::trim(mut_tags, 0);
        vector::append(mut_tags, tags);
        article
    }

}
