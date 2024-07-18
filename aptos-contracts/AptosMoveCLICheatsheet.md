# Aptos Move CLI Cheatsheet

[ToC]

## Article aggregate

### AddTag method

```shell
aptos move run --function-id 'default::article_aggregate::add_tag' \
--args address:id Object<Tag>:tag \
--assume-yes
```

### Create method

```shell
aptos move run --function-id 'default::article_aggregate::create' \
--args 'string:title' 'string:body' address:owner \
--assume-yes
```

### Update method

```shell
aptos move run --function-id 'default::article_aggregate::update' \
--args address:id 'string:title' 'string:body' address:owner 'Object<Tag>:[tags_item_1,tags_item_2]' \
--assume-yes
```

### Delete method

```shell
aptos move run --function-id 'default::article_aggregate::delete' \
--args address:id \
--assume-yes
```

### AddComment method

```shell
aptos move run --function-id 'default::article_aggregate::add_comment' \
--args address:id 'string:commenter' 'string:body' address:owner \
--assume-yes
```

### UpdateComment method

```shell
aptos move run --function-id 'default::article_aggregate::update_comment' \
--args address:id u64:comment_seq_id 'string:commenter' 'string:body' address:owner \
--assume-yes
```

### RemoveComment method

```shell
aptos move run --function-id 'default::article_aggregate::remove_comment' \
--args address:id u64:comment_seq_id \
--assume-yes
```

## Tag aggregate

### Create method

```shell
aptos move run --function-id 'default::tag_aggregate::create' \
--args 'string:name' \
--assume-yes
```

## Blog singleton object

### Create method

```shell
aptos move run --function-id 'default::blog_aggregate::create' \
--args 'string:name' bool:is_emergency \
--assume-yes
```

### Update method

```shell
aptos move run --function-id 'default::blog_aggregate::update' \
--args 'string:name' 'address:[articles_item_1,articles_item_2]' bool:is_emergency \
--assume-yes
```

### Delete method

```shell
aptos move run --function-id 'default::blog_aggregate::delete' \
--assume-yes
```

