// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_blog_demo::tag_created {

    use aptos_blog_demo::tag::{Self, TagCreated};
    use std::option;
    use std::string::String;

    public fun tag_id(tag_created: &TagCreated): option::Option<address> {
        tag::tag_created_tag_id(tag_created)
    }

    public fun name(tag_created: &TagCreated): String {
        tag::tag_created_name(tag_created)
    }

}
