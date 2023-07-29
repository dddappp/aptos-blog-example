module aptos_blog_demo::blog_scripts {
    use std::signer;
    use aptos_framework::aptos_coin::AptosCoin;
    use aptos_framework::coin;

    use aptos_blog_demo::blog_aggregate;

    public entry fun donate(
        account: &signer,
        amount: u64,
    ) {
        let a = coin::withdraw<AptosCoin>(account, amount);
        blog_aggregate::donate(account, a);
    }

    public entry fun withdraw(
        account: &signer,
        amount: u64,
    ) {
        let a = blog_aggregate::withdraw(account, amount);
        coin::deposit(signer::address_of(account), a);
    }
}
