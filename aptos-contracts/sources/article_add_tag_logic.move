module aptos_blog_demo::article_add_tag_logic {
    use aptos_blog_demo::add_tag_event;
    use aptos_blog_demo::article;
    use aptos_blog_demo::comment::{Self, Comment};
    use aptos_blog_demo::tag::Tag;
    use aptos_framework::object::Object;

    friend aptos_blog_demo::article_aggregate;

    public(friend) fun verify(
        account: &signer,
        tag: Object<Tag>,
        id: address,
        article: &article::Article,
    ): article::AddTagEvent {
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
        let tag = add_tag_event::tag(add_tag_event);
        //todo
        article
    }

}
