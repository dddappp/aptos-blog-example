module aptos_blog_demo::blog_create_logic {
    use aptos_blog_demo::blog;
    use aptos_blog_demo::blog_created;
    use std::string::String;
    use std::vector;
    use aptos_blog_demo::genesis_account;

    friend aptos_blog_demo::blog_aggregate;

    public(friend) fun verify(
        account: &signer,
        name: String,
        is_emergency: bool,
    ): blog::BlogCreated {
        genesis_account::assert_genesis_account(account);
        blog::new_blog_created(
            name,
            is_emergency,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        blog_created: &blog::BlogCreated,
    ): blog::Blog {
        let name = blog_created::name(blog_created);
        let is_emergency = blog_created::is_emergency(blog_created);
        blog::new_blog(
            name,
            vector::empty(),
            is_emergency,
        )
    }

}
