module aptos_blog_demo::blog_withdraw_logic {
    use aptos_blog_demo::blog;
    use aptos_blog_demo::vault_withdrawn;
    use aptos_framework::aptos_coin::AptosCoin;
    use aptos_framework::coin;
    use aptos_framework::coin::Coin;

    friend aptos_blog_demo::blog_aggregate;

    public(friend) fun verify(
        account: &signer,
        amount: u64,
        blog: &blog::Blog,
    ): blog::VaultWithdrawn {
        let _ = account;
        //assert!(@admin_addr == std::signer::address_of(account), 111);
        blog::new_vault_withdrawn(
            blog,
            amount,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        vault_withdrawn: &blog::VaultWithdrawn,
        blog: blog::Blog,
    ): (blog::Blog, Coin<AptosCoin>) {
        let amount = vault_withdrawn::amount(vault_withdrawn);
        let balance = blog::borrow_mut_vault(&mut blog);
        let w = coin::extract(balance, amount);
        (blog, w)
    }

}
