module aptos_blog_demo::tag_create_logic {
    use aptos_blog_demo::tag;
    use aptos_blog_demo::tag_created;
    use std::string::String;

    friend aptos_blog_demo::tag_aggregate;

    public(friend) fun verify(
        account: &signer,
        name: String,
    ): tag::TagCreated {
        let _ = account;
        tag::new_tag_created(
            name,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        tag_created: &tag::TagCreated,
        tag_id: address,
    ): tag::Tag {
        let name = tag_created::name(tag_created);
        tag::new_tag(
            name,
        )
    }

}
