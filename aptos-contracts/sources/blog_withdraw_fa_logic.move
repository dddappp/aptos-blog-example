module aptos_blog_demo::blog_withdraw_fa_logic {
    use std::option;
    use aptos_framework::fungible_asset;
    use aptos_framework::fungible_asset::FungibleAsset;

    use aptos_blog_demo::blog;
    use aptos_blog_demo::fa_vault_withdrawn;

    const EFaVaultNotInitialized: u64 = 101;

    friend aptos_blog_demo::blog_aggregate;

    public(friend) fun verify<T: key>(
        account: &signer,
        amount: u64,
        blog: &blog::Blog,
    ): blog::FaVaultWithdrawn {
        blog::new_fa_vault_withdrawn(
            blog,
            amount,
        )
    }

    public(friend) fun mutate<T: key>(
        _account: &signer,
        fa_vault_withdrawn: &blog::FaVaultWithdrawn,
        blog: blog::Blog,
    ): (blog::Blog, FungibleAsset) {
        let amount = fa_vault_withdrawn::amount(fa_vault_withdrawn);
        let store = blog::fa_vault(&blog);
        assert!(option::is_some(&store), EFaVaultNotInitialized);
        (blog, fungible_asset::withdraw(_account, option::extract(&mut store), amount))
    }
}
