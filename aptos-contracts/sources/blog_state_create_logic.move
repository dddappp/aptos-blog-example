module aptos_blog_demo::blog_state_create_logic {
    use aptos_blog_demo::blog_state;
    use aptos_blog_demo::blog_state_created;

    friend aptos_blog_demo::blog_state_aggregate;

    public(friend) fun verify(
        account: &signer,
        is_emergency: bool,
        articles: vector<u128>,
    ): blog_state::BlogStateCreated {
        let _ = account;
        blog_state::new_blog_state_created(
            is_emergency,
            articles,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        blog_state_created: &blog_state::BlogStateCreated,
    ): blog_state::BlogState {
        let is_emergency = blog_state_created::is_emergency(blog_state_created);
        let articles = blog_state_created::articles(blog_state_created);
        blog_state::new_blog_state(
            is_emergency,
            articles,
        )
    }

}
