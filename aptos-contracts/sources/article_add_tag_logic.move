module aptos_blog_demo::article_add_tag_logic {
    use std::vector;
    use aptos_framework::object::{Self, Object};
    use aptos_blog_demo::pass_object;
    use aptos_blog_demo::tag;

    use aptos_blog_demo::add_tag_event;
    use aptos_blog_demo::article;
    use aptos_blog_demo::tag::Tag;

    friend aptos_blog_demo::article_aggregate;

    const ETagNotFound: u64 = 101;

    public(friend) fun verify(
        _account: &signer,
        tag: Object<Tag>,
        id: address,
        article: &article::Article,
    ): article::AddTagEvent {
        let tag_addr = object::object_address(&tag);
        assert!(object::object_exists<Tag>(tag_addr), ETagNotFound);
        // -------------------------------------------------------------------
        // Test: get tag name here
        let tag_pass_obj = tag::get_tag(tag_addr);
        let tag_val = pass_object::borrow(&tag_pass_obj);
        std::debug::print(&tag::name(tag_val));
        tag::return_tag(tag_pass_obj);
        // -------------------------------------------------------------------
        article::new_add_tag_event(
            id,
            article,
            tag,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        add_tag_event: &article::AddTagEvent,
        id: address,
        article: article::Article,
    ): article::Article {
        let _ = id;
        let tag = add_tag_event::tag(add_tag_event);
        let tags = article::borrow_mut_tags(&mut article);
        if (!vector::contains(tags, &tag)) {
            vector::push_back(tags, tag);
        };
        article
    }
}
