module aptos_blog_demo::blog_donate_logic {
    use aptos_blog_demo::blog;
    use aptos_blog_demo::donation_received;
    use aptos_framework::aptos_coin::AptosCoin;
    use aptos_framework::coin;
    use aptos_framework::coin::Coin;

    friend aptos_blog_demo::blog_aggregate;

    public(friend) fun verify(
        account: &signer,
        amount: &Coin<AptosCoin>,
        blog: &blog::Blog,
    ): blog::DonationReceived {
        let _ = account;
        blog::new_donation_received(
            blog,
            coin::value(amount),
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        donation_received: &blog::DonationReceived,
        amount: Coin<AptosCoin>,
        blog: blog::Blog,
    ): blog::Blog {
        let _amount_value = donation_received::amount(donation_received);
        let vault = blog::borrow_mut_vault(&mut blog);
        coin::merge(vault, amount);
        blog
    }

}
