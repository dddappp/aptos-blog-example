module aptos_blog_demo::article_remove_comment_logic {
    use aptos_blog_demo::article;
    use aptos_blog_demo::comment_removed;

    friend aptos_blog_demo::article_aggregate;

    public(friend) fun verify(
        account: &signer,
        comment_seq_id: u64,
        id: address,
        article: &article::Article,
    ): article::CommentRemoved {
        let _ = account;
        let comment = article::borrow_comment(article, comment_seq_id);
        let _ = comment;
        article::new_comment_removed(
            id,
            article,
            comment_seq_id,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        comment_removed: &article::CommentRemoved,
        id: address,
        article: article::Article,
    ): article::Article {
        let comment_seq_id = comment_removed::comment_seq_id(comment_removed);
        let _ = id;
        article::remove_comment(&mut article, comment_seq_id);
        article
    }

}
