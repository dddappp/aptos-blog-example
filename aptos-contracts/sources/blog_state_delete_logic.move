module aptos_blog_demo::blog_state_delete_logic {
    use aptos_blog_demo::blog_state;

    //friend aptos_blog_demo::blog_state_aggregate;

    public(friend) fun verify(
        account: &signer,
        blog_state: &blog_state::BlogState,
    ): blog_state::BlogStateDeleted {
        let _ = account;
        blog_state::new_blog_state_deleted(
            blog_state,
        )
    }

    public(friend) fun mutate(
        blog_state_deleted: &blog_state::BlogStateDeleted,
        blog_state: blog_state::BlogState,
    ): blog_state::BlogState {
        let _ = blog_state_deleted;
        blog_state
    }

}
