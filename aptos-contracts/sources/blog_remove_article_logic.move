module aptos_blog_demo::blog_remove_article_logic {
    use std::vector;

    use aptos_blog_demo::article_removed_from_blog;
    use aptos_blog_demo::blog;

    friend aptos_blog_demo::blog_aggregate;

    public(friend) fun verify(
        article_id: address,
        blog: &blog::Blog,
    ): blog::ArticleRemovedFromBlog {
        blog::new_article_removed_from_blog(
            blog,
            article_id,
        )
    }

    public(friend) fun mutate(
        article_removed_from_blog: &blog::ArticleRemovedFromBlog,
        blog: blog::Blog,
    ): blog::Blog {
        let article_id = article_removed_from_blog::article_id(article_removed_from_blog);
        let articles = blog::articles(&blog);
        let (found, idx) = vector::index_of(&articles, &article_id);
        if (found) {
            vector::remove(&mut articles, idx);
            blog::set_articles(&mut blog, articles);
        };
        blog
    }
}
