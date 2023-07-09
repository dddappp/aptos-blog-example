module aptos_blog_example::article_add_comment_logic {
    use aptos_blog_example::article;
    use aptos_blog_example::comment;
    use aptos_blog_example::comment_added;
    use std::string::String;

    friend aptos_blog_example::article_aggregate;

    public(friend) fun verify(
        account: &signer,
        comment_seq_id: u64,
        commenter: String,
        body: String,
        owner: address,
        article: &article::Article,
    ): article::CommentAdded {
        let _ = account;
        article::new_comment_added(
            article,
            comment_seq_id,
            commenter,
            body,
            owner,//std::signer::address_of(account),
        )
    }

    public(friend) fun mutate(
        comment_added: &article::CommentAdded,
        article: article::Article,
    ): article::Article {
        let comment_seq_id = comment_added::comment_seq_id(comment_added);
        let commenter = comment_added::commenter(comment_added);
        let body = comment_added::body(comment_added);
        let owner = comment_added::owner(comment_added);
        let article_id = article::article_id(&article);
        let _ = article_id;
        let comment = comment::new_comment(
            comment_seq_id,
            commenter,
            body,
            owner,
        );
        article::add_comment(&mut article, comment);
        article
    }

}
