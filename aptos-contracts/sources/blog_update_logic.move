module aptos_blog_demo::blog_update_logic {
    use aptos_blog_demo::blog;
    use aptos_blog_demo::blog_updated;
    use std::string::String;
    use aptos_blog_demo::genesis_account;

    friend aptos_blog_demo::blog_aggregate;

    public(friend) fun verify(
        account: &signer,
        name: String,
        articles: vector<u128>,
        is_emergency: bool,
        blog: &blog::Blog,
    ): blog::BlogUpdated {
        genesis_account::assert_genesis_account(account);
        blog::new_blog_updated(
            blog,
            name,
            articles,
            is_emergency,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        blog_updated: &blog::BlogUpdated,
        blog: blog::Blog,
    ): blog::Blog {
        let name = blog_updated::name(blog_updated);
        let articles = blog_updated::articles(blog_updated);
        let is_emergency = blog_updated::is_emergency(blog_updated);
        blog::set_name(&mut blog, name);
        blog::set_articles(&mut blog, articles);
        blog::set_is_emergency(&mut blog, is_emergency);
        blog
    }

}
