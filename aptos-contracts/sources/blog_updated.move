// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_blog_demo::blog_updated {

    use aptos_blog_demo::blog::{Self, BlogUpdated};
    use aptos_framework::fungible_asset::FungibleStore;
    use aptos_framework::object::Object;
    use std::option::Option;
    use std::string::String;

    public fun name(blog_updated: &BlogUpdated): String {
        blog::blog_updated_name(blog_updated)
    }

    public fun articles(blog_updated: &BlogUpdated): vector<address> {
        blog::blog_updated_articles(blog_updated)
    }

    public fun is_emergency(blog_updated: &BlogUpdated): bool {
        blog::blog_updated_is_emergency(blog_updated)
    }

    public fun fa_vault(blog_updated: &BlogUpdated): Option<Object<FungibleStore>> {
        blog::blog_updated_fa_vault(blog_updated)
    }

}
