// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_blog_demo::fa_vault_withdrawn {

    use aptos_blog_demo::blog::{Self, FaVaultWithdrawn};

    public fun amount(fa_vault_withdrawn: &FaVaultWithdrawn): u64 {
        blog::fa_vault_withdrawn_amount(fa_vault_withdrawn)
    }

}
