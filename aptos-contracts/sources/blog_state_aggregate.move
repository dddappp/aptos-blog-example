// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_blog_demo::blog_state_aggregate {
    use aptos_blog_demo::blog_state;
    use aptos_blog_demo::blog_state_create_logic;
    use aptos_blog_demo::blog_state_delete_logic;
    use aptos_blog_demo::blog_state_update_logic;

    public entry fun create(
        account: &signer,
        is_emergency: bool,
    ) {
        let blog_state_created = blog_state_create_logic::verify(
            account,
            is_emergency,
        );
        let blog_state = blog_state_create_logic::mutate(
            &blog_state_created,
        );
        blog_state::add_blog_state(blog_state);
        blog_state::emit_blog_state_created(blog_state_created);
    }


    public entry fun update(
        account: &signer,
        is_emergency: bool,
    ) {
        let blog_state = blog_state::remove_blog_state();
        let blog_state_updated = blog_state_update_logic::verify(
            account,
            is_emergency,
            &blog_state,
        );
        let updated_blog_state = blog_state_update_logic::mutate(
            &blog_state_updated,
            blog_state,
        );
        blog_state::update_version_and_add(updated_blog_state);
        blog_state::emit_blog_state_updated(blog_state_updated);
    }


    public entry fun delete(
        account: &signer,
    ) {
        let blog_state = blog_state::remove_blog_state();
        let blog_state_deleted = blog_state_delete_logic::verify(
            account,
            &blog_state,
        );
        let updated_blog_state = blog_state_delete_logic::mutate(
            &blog_state_deleted,
            blog_state,
        );
        blog_state::drop_blog_state(updated_blog_state);
        blog_state::emit_blog_state_deleted(blog_state_deleted);
    }

}