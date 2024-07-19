module aptos_blog_demo::blog_init_fa_vault_logic {
    use std::option;
    use std::signer;
    use aptos_framework::fungible_asset;
    use aptos_blog_demo::blog;
    use aptos_blog_demo::init_fa_vault_event;
    use aptos_framework::object::{Self, Object};

    friend aptos_blog_demo::blog_aggregate;

    public(friend) fun verify<T: key>(
        account: &signer,
        metadata: Object<T>,
        blog: &blog::Blog,
    ): blog::InitFaVaultEvent {
        blog::new_init_fa_vault_event(
            blog,
            object::object_address(&metadata),
        )
    }

    public(friend) fun mutate<T: key>(
        account: &signer,
        init_fa_vault_event: &blog::InitFaVaultEvent,
        blog: blog::Blog,
    ): blog::Blog {
        let metadata = init_fa_vault_event::metadata(init_fa_vault_event);
        let metadata_obj = object::address_to_object<T>(metadata);
        let constructor_ref = object::create_object(signer::address_of(account));
        let fa_store = fungible_asset::create_store(&constructor_ref, metadata_obj);
        blog::set_fa_vault(&mut blog, option::some(fa_store));
        blog
    }

}
