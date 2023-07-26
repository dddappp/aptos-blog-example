module aptos_blog_demo::blog_delete_logic {
    use aptos_blog_demo::genesis_account;
    use aptos_blog_demo::blog;

    friend aptos_blog_demo::blog_aggregate;

    public(friend) fun verify(
        account: &signer,
        blog: &blog::Blog,
    ): blog::BlogDeleted {
        genesis_account::assert_genesis_account(account);
        blog::new_blog_deleted(
            blog,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        blog_deleted: &blog::BlogDeleted,
        blog: blog::Blog,
    ): blog::Blog {
        let _ = blog_deleted;
        blog
    }

}
