module aptos_blog_demo::blog_state_create_logic {
    use aptos_blog_demo::blog_state;
    use aptos_blog_demo::blog_state_created;

    //friend aptos_blog_demo::blog_state_aggregate;

    public(friend) fun verify(
        account: &signer,
        is_emergency: bool,
    ): blog_state::BlogStateCreated {
        let _ = account;
        blog_state::new_blog_state_created(
            is_emergency,
        )
    }

    public(friend) fun mutate(
        blog_state_created: &blog_state::BlogStateCreated,
    ): blog_state::BlogState {
        let is_emergency = blog_state_created::is_emergency(blog_state_created);
        blog_state::new_blog_state(
            is_emergency,
        )
    }

}
