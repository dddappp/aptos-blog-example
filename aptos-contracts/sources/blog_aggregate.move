// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_blog_demo::blog_aggregate {
    use aptos_blog_demo::blog;
    use aptos_blog_demo::blog_add_article_logic;
    use aptos_blog_demo::blog_create_logic;
    use aptos_blog_demo::blog_delete_logic;
    use aptos_blog_demo::blog_donate_logic;
    use aptos_blog_demo::blog_remove_article_logic;
    use aptos_blog_demo::blog_update_logic;
    use aptos_blog_demo::blog_withdraw_logic;
    use aptos_framework::aptos_coin::AptosCoin;
    use aptos_framework::coin::Coin;
    use std::string::String;

    friend aptos_blog_demo::article_create_logic;
    friend aptos_blog_demo::article_delete_logic;

    public entry fun create(
        account: &signer,
        name: String,
        is_emergency: bool,
    ) {
        let blog_created = blog_create_logic::verify(
            account,
            name,
            is_emergency,
        );
        let blog = blog_create_logic::mutate(
            account,
            &blog_created,
        );
        blog::add_blog(blog);
        blog::emit_blog_created(blog_created);
    }

    public(friend) fun add_article(
        article_id: address,
    ) {
        let blog = blog::remove_blog();
        let article_added_to_blog = blog_add_article_logic::verify(
            article_id,
            &blog,
        );
        let updated_blog = blog_add_article_logic::mutate(
            &article_added_to_blog,
            blog,
        );
        blog::update_version_and_add(updated_blog);
        blog::emit_article_added_to_blog(article_added_to_blog);
    }

    public(friend) fun remove_article(
        article_id: address,
    ) {
        let blog = blog::remove_blog();
        let article_removed_from_blog = blog_remove_article_logic::verify(
            article_id,
            &blog,
        );
        let updated_blog = blog_remove_article_logic::mutate(
            &article_removed_from_blog,
            blog,
        );
        blog::update_version_and_add(updated_blog);
        blog::emit_article_removed_from_blog(article_removed_from_blog);
    }

    public fun donate(
        account: &signer,
        amount: Coin<AptosCoin>,
    ) {
        let blog = blog::remove_blog();
        let donation_received = blog_donate_logic::verify(
            account,
            &amount,
            &blog,
        );
        let updated_blog = blog_donate_logic::mutate(
            account,
            &donation_received,
            amount,
            blog,
        );
        blog::update_version_and_add(updated_blog);
        blog::emit_donation_received(donation_received);
    }

    public fun withdraw(
        account: &signer,
        amount: u64,
    ): Coin<AptosCoin> {
        let blog = blog::remove_blog();
        let vault_withdrawn = blog_withdraw_logic::verify(
            account,
            amount,
            &blog,
        );
        let (updated_blog, withdraw_return) = blog_withdraw_logic::mutate(
            account,
            &vault_withdrawn,
            blog,
        );
        blog::update_version_and_add(updated_blog);
        blog::emit_vault_withdrawn(vault_withdrawn);
        withdraw_return
    }

    public entry fun update(
        account: &signer,
        name: String,
        articles: vector<address>,
        is_emergency: bool,
    ) {
        let blog = blog::remove_blog();
        let blog_updated = blog_update_logic::verify(
            account,
            name,
            articles,
            is_emergency,
            &blog,
        );
        let updated_blog = blog_update_logic::mutate(
            account,
            &blog_updated,
            blog,
        );
        blog::update_version_and_add(updated_blog);
        blog::emit_blog_updated(blog_updated);
    }

    public entry fun delete(
        account: &signer,
    ) {
        let blog = blog::remove_blog();
        let blog_deleted = blog_delete_logic::verify(
            account,
            &blog,
        );
        let updated_blog = blog_delete_logic::mutate(
            account,
            &blog_deleted,
            blog,
        );
        blog::drop_blog(updated_blog);
        blog::emit_blog_deleted(blog_deleted);
    }

}
