module aptos_blog_demo::blog_state_update_logic {
    use aptos_blog_demo::blog_state;
    use aptos_blog_demo::blog_state_updated;

    friend aptos_blog_demo::blog_state_aggregate;

    public(friend) fun verify(
        account: &signer,
        is_emergency: bool,
        articles: vector<u128>,
        blog_state: &blog_state::BlogState,
    ): blog_state::BlogStateUpdated {
        let _ = account;
        blog_state::new_blog_state_updated(
            blog_state,
            is_emergency,
            articles,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        blog_state_updated: &blog_state::BlogStateUpdated,
        blog_state: blog_state::BlogState,
    ): blog_state::BlogState {
        let is_emergency = blog_state_updated::is_emergency(blog_state_updated);
        let articles = blog_state_updated::articles(blog_state_updated);
        blog_state::set_is_emergency(&mut blog_state, is_emergency);
        blog_state::set_articles(&mut blog_state, articles);
        blog_state
    }

}
