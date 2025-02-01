module aptos_blog_demo::blog_add_article_logic {
    use std::vector;

    use aptos_blog_demo::article_added_to_blog;
    use aptos_blog_demo::blog;

    friend aptos_blog_demo::blog_aggregate;

    public(friend) fun verify(
        article_id: address,
        blog: &blog::Blog,
    ): blog::ArticleAddedToBlog {
        blog::new_article_added_to_blog(
            blog,
            article_id,
        )
    }

    public(friend) fun mutate(
        article_added_to_blog: &blog::ArticleAddedToBlog,
        blog: blog::Blog,
    ): blog::Blog {
        let article_id = article_added_to_blog::article_id(article_added_to_blog);
        let articles = blog::borrow_mut_articles(&mut blog);
        if (!vector::contains(articles, &article_id)) {
            vector::push_back(articles, article_id);
            //blog::set_articles(&mut blog, articles);
        };
        blog
    }
}
