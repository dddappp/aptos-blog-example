module aptos_blog_demo::blog_donate_fa_logic {
    use aptos_framework::fungible_asset;
    use aptos_blog_demo::blog;
    use aptos_blog_demo::fa_donation_received;
    use aptos_framework::fungible_asset::FungibleAsset;
    use std::option;

    friend aptos_blog_demo::blog_aggregate;

    const EFaVaultNotInitialized: u64 = 101;

    public(friend) fun verify<T: key>(
        account: &signer,
        fa: &FungibleAsset,
        blog: &blog::Blog,
    ): blog::FaDonationReceived {
        blog::new_fa_donation_received(
            blog,
            fungible_asset::amount(fa),
        )
    }

    public(friend) fun mutate<T: key>(
        _account: &signer,
        fa_donation_received: &blog::FaDonationReceived,
        fa: FungibleAsset,
        blog: blog::Blog,
    ): blog::Blog {
        let fa_amount = fa_donation_received::fa_amount(fa_donation_received);
        let store = blog::fa_vault(&blog);
        assert!(option::is_some(&store), EFaVaultNotInitialized);
        fungible_asset::deposit(option::extract(&mut store), fa);
        blog
    }

}
