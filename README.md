# How to Develop a Blog Example on Aptos

An example of developing a blog_example application based on the Aptos platform.

It only requires 30 or so lines of code (all of which is a description of the domain model) to be written by the developer, and then generates a blog example that emulates [RoR Getting Started](https://guides.rubyonrails.org/getting_started.html) in one click, without requiring the developer to write a single line of other code.

## Prerequisites

Currently, the dddappp low-code tool is published as a Docker image for developers to experience.

The off-chain services generated by the tool are written in Java and use the MySQL database by default.

So before getting started, you need to:

* Install [Aptos CLI](https://aptos.dev/tools/install-cli/).
* Install [Docker](https://docs.docker.com/engine/install/).
* Install `curl`.
* ~~(Optional) Install `jp` - commandline JSON processor. We can use `jp` to process Node API returned JSON results when testing contracts.~~
* Install MySQL database server. If you do not want to try to run the off-chain service, you can ignore this step.
* Install JDK and Maven. If you do not want to try to run the off-chain service, you can ignore this step. The off-chain services generated by the tool currently use Java language.

If you have already installed Docker, you can use Docker to run a MySQL database server. For example:

```shell
sudo docker run -p 3306:3306 --name mysql \
-v ~/docker/mysql/conf:/etc/mysql \
-v ~/docker/mysql/logs:/var/log/mysql \
-v ~/docker/mysql/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
-d mysql:5.7
```


## Programming

### Write DDDML Model File

In the `dddml` directory in the root of the repository, create a DDDML file like this:

```yaml
aggregates:
  Article:
    metadata:
      Preprocessors: ["MOVE_CRUD_IT"]
    id:
      name: ArticleId
      type: u128
      generator:
        class: sequence
        structName: ArticleIdGenerator
    properties:
      Title:
        type: String
        length: 200
      Body:
        type: String
        length: 2000
      Owner:
        type: address
      Comments:
        itemType: Comment
    entities:
      Comment:
        metadata:
          Preprocessors: ["MOVE_CRUD_IT"]
        id:
          name: CommentSeqId
          type: u64
        properties:
          Commenter:
            type: String
            length: 100
          Body:
            type: String
            length: 500
          Owner:
            type: address
```

> **Tip**
>
> About DDDML, here is an introductory article: ["Introducing DDDML: The Key to Low-Code Development for Decentralized Applications"](https://github.com/wubuku/Dapp-LCDP-Demo/blob/main/IntroducingDDDML.md). 


### Run dddappp Project Creation Tool

#### Update dddappp Docker Image

Since the dddappp v0.0.1 image is updated frequently, you may be required to manually delete the image and pull it again before `docker run`.

```shell
# If you have already run it, you may need to Clean Up Exited Docker Containers first
docker rm $(docker ps -aq --filter "ancestor=wubuku/dddappp-aptos:master")
# remove the image
docker image rm wubuku/dddappp-aptos:master
# pull the image
docker pull wubuku/dddappp-aptos:master
```

---

In repository root directory, run:

```shell
docker run \
-v .:/myapp \
wubuku/dddappp-aptos:master \
--dddmlDirectoryPath /myapp/dddml \
--boundedContextName Test.AptosBlogDemo \
--aptosMoveProjectDirectoryPath /myapp/aptos-contracts \
--boundedContextAptosPackageName AptosBlogDemo \
--boundedContextAptosNamedAddress aptos_blog_demo \
--boundedContextJavaPackageName org.test.aptosblogdemo \
--javaProjectsDirectoryPath /myapp/aptos-java-service \
--javaProjectNamePrefix aptosblogdemo \
--pomGroupId test.aptosblogdemo
```

The command parameters above are straightforward:

* The first line indicates mounting your local directory into the `/myapp` directory inside the container.
* `dddmlDirectoryPath` is the directory where DDDML model files are located. It should be a readable directory path in the container.
* Interpret the value of parameter `boundedContextName` as the name of your application you want to develop. When there are multiple parts in your name, separate them with dots and use PascalCase naming style for each part. Bounded-context is a term in Domain-driven design (DDD) that refers to a specific problem domain scope that contains specific business boundaries, constraints, and language. If you don't understand this concept for now, it's not a big deal.
* `aptosMoveProjectDirectoryPath` is directory path where on-chain Aptos contract code is placed. It should be a readable and writable directory path in container.
* `boundedContextAptosPackageName` is package name of on-chain Aptos contracts. It's recommended to use PascalCase naming style.
* `boundedContextAptosNamedAddress` is default named address of on-chain Aptos contracts. It's recommended to use snake_case naming style.
* `boundedContextJavaPackageName` is Java package name of off-chain service. According to Java naming conventions, it should be all lowercase and parts should be separated by dots.
* `javaProjectsDirectoryPath` is directory path where off-chain service code is placed. Off-chain service consists of multiple modules (projects). It should be a readable and writable directory path in container.
* `javaProjectNamePrefix` is name prefix of each module of off-chain service. It's recommended to use an all-lowercase name.
* `pomGroupId` is GroupId of off-chain service. We use Maven as project management tool for off-chain service. It should be all lowercase and parts should be separated by dots.

After executing above command successfully, a directory `aptos-contracts` should be added to local current directory.


### Implementing Business Logic

If CRUD is all the business logic you need, You **don't** need to write a single line of code other than the DDDML model above. You **can skip this section** and just start testing your example application.

The tool has generated some files with the suffix `_logic.move` in the directory `aptos-contracts/sources`. 

Generally, these files contain the scaffolding code of functions that implement business logic, namely the signature part of the functions. You just need to fill in the implementation part of the functions. However, in this example, the `MOVE_CRUD_IT` preprocessor already generates the full CRUD methods for us.

---

It is possible that you feel that the default generated CRUD logic does not meet your needs.

For example, you might want to the `Owner` fields of `Article` and `Comment` to control who can update/delete articles and comments. Then, you may want to add comment without passing the `Owner` argument to `entry fun add_comment` and directly use the sender account address as the owner.

This requirement can currently be satisfied as follows.

First, define a custom method in the model file like this:

```yaml
aggregates:
  Article:
  # ...
    methods:
      AddComment:
        event:
          name: CommentAdded
          properties:
            Owner:
              type: address
        parameters:
          CommentSeqId:
            type: u64
          Commenter:
            type: String
            length: 100
          Body:
            type: String
            length: 500
```

Note that the `Owner` parameter is NOT present in the method parameters above.

Then, delete `article_add_comment_logic.move`, run the dddappp tool again. (Since the dddappp tool does not overwrite the `*_logic.move` files by default, you will need to delete it manually.)

Open the regenerated `article_add_comment_logic.move` file, find the `verify` function and fill in the body with the logic you want.


## Test Example

### Compile Aptos Move contracts

#### Some preparatory work that may need to be done

It should be noted that below we assume that you will publish the Move contract to the Aptos devnet, so we skip the explanation of the modifications to some configuration files required for publishing to other networks.

We can create a new account on devnet to perform the following test.

Confirm that Aptos CLI is installed and enter the directory `aptos-contracts`, then run:

```shell
aptos init
# View Aptos Profiles:
aptos config show-profiles
# Press Enter to confirm using the default values:
aptos account fund-with-faucet --account default --amount 1000000000
aptos account list --query balance
```

It should display similar information:

```shell
{
  "Result": {
    "default": {
      "has_private_key": true,
      "public_key": "...",
      "account": "{ACCOUNT_ADDRESS}",
      "rest_url": "https://fullnode.devnet.aptoslabs.com",
      "faucet_url": "https://faucet.devnet.aptoslabs.com"
    }
  }
}
```

---

In the directory `aptos-contracts`, execute the compilation, which should now succeed:

```shell
aptos move compile --named-addresses aptos_blog_demo=default --skip-fetch-latest-git-deps
```

At this point, the coding phase of the application development is complete! Isn't it very simple?

---

Next, we will deploy and test the Demo application.

### Publish the Aptos contracts

Execute the following command in the directory `aptos-contracts` to publish the contracts to the chain:

```shell
aptos move publish --named-addresses aptos_blog_demo=default --skip-fetch-latest-git-deps --included-artifacts none --assume-yes
```

If the command is executed successfully, it should display similar information:

```shell
{
  "Result": {
    "transaction_hash": "{TRANSACTION_HASH}"
    "gas_used": 20722,
    "gas_unit_price": 100,
    "sender": "{ACCOUNT_ADDRESS}",
    "sequence_number": 0,
    "success": true,
    "timestamp_us": 1688909362156606,
    "version": 11446005,
    "vm_status": "Executed successfully"
  }
}
```

### Tip: Using this Cheatsheet

Here it is a cheatsheet on how to use the Aptos Client CLI to call on-chain contracts: [AptosMoveCLICheatsheet](./aptos-contracts/AptosMoveCLICheatsheet.md)

The parameters you need to fill in are placeholders containing their type and meaning (name). You can copy these commands, modify them as needed, and execute them directly in a terminal.

### Initialize the On-chain Contracts

We will use Aptos CLI and other command line tools (`curl`, `jq`) to test the published contracts below.

Use `aptos move run` command to submit a transaction and initialize the contract:

```shell
aptos move run --function-id 'default::aptos_blog_demo_init::initialize' --assume-yes

# If you're testing the current version in the repository, you'll also need to run this:
aptos move run --function-id 'default::blog_aggregate::create' --args 'string:MyBlog' 'bool:false' --assume-yes
#Init blog FA vault:
aptos move run --function-id 'default::blog_aggregate::init_fa_vault' \
  --type-args \
    "0x1::fungible_asset::Metadata" \
  --args \
    'address:0x601b55598d1a02edee2d85c57f1bba537d03ccdfd77192239c80b2f263e2a876' \
  --assume-yes
```

### CRUD Articles

Note that `{ACCOUNT_ADDRESS}`, `{RESOURCE_ACCOUNT_ADDRESS}` and `{ARTICLE_TABLE_HANDLE}` in the command lines below are placeholders, 
please replace them with the actual values before executing the commands.

#### Create Articles

You can create an article like this (you need to replace the placeholder `{ACCOUNT_ADDRESS}` with your actual account address):

```shell
#aptos move run --function-id 'default::tag_aggregate::create' --args 'string:Foo' --assume-yes

aptos move run --function-id 'default::article_aggregate::create' \
--args 'string:hello' 'string:world' address:{ACCOUNT_ADDRESS} \
--assume-yes

#aptos move run --function-id 'default::article_aggregate::add_tag' \
#--args address:0xc7b0297a2351ebb76e0307f31e99a03db6dab34caf50d151e7b9b6f878ea192c address:0xf82d75f8246dc8694c8adfa38a5fa9ce1a5b94a3e791ce9f153174c9b3cd9acd \
#--assume-yes
```

Then you can change the content of the first parameter (title) and the second parameter (body) after `--args`, and create a few more articles.

```shell
aptos move run --function-id 'default::article_aggregate::create' \
--args 'string:foo' 'string:bar' address:{ACCOUNT_ADDRESS} \
--assume-yes
```

Note that in the contract, we assign each article an incremental sequence number as its ID. So, the ID of the first article you create is `1` and the ID of the second article is `2`.

---

Get transaction information by hash:

```shell
curl --request GET \
  --url https://fullnode.devnet.aptoslabs.com/v1/transactions/by_hash/{TXN_HASH} \
  --header 'Accept: application/json, application/x-bcs'
```

---

Before reading the article information, we need to do some preparatory operations: get the address of the resource account, and the handle of the article table.



#### Get Resource Account Address

Our contracts use a separate resource account to hold information of articles and comments.

You can get the address of this resource account by using the following command:

```shell
curl https://fullnode.devnet.aptoslabs.com/v1/accounts/{ACCOUNT_ADDRESS}/resource/{ACCOUNT_ADDRESS}::resource_account::ResourceAccount
```

The output is similar to the following:

```json
{"type":"{ACCOUNT_ADDRESS}::resource_account::ResourceAccount","data":{"cap":{"account":"{RESOURCE_ACCOUNT_ADDRESS}"}}}
```

In the location `{RESOURCE_ACCOUNT_ADDRESS}` above, the address of the resource account will be displayed.


#### Getting Article Creation Events

Execute the following command, noting the replacement of the placeholders `{RESOURCE_ACCOUNT_ADDRESS}` and `{ACCOUNT_ADDRESS}`:

```shell
curl --request GET \
  --url 'https://fullnode.devent.aptoslabs.com/v1/accounts/{RESOURCE_ACCOUNT_ADDRESS}/events/{ACCOUNT_ADDRESS}::article::Events/article_created_handle?start=0&limit=10' \
  --header 'Accept: application/json'
```

For example:

```shell
curl --request GET \
  --url 'https://fullnode.devnet.aptoslabs.com/v1/accounts/0xd4c808929f57f1714177d79b4e738a40845b95bb97429afee39d4696828bdc8f/events/0x8bc9a5fab9a68b62117ac3aff4917eacf05dd633a766a689dd14707abeb51738::article::Events/article_created_handle?start=0&limit=10' \
  --header 'Accept: application/json'
```


#### Get Article Table Handle

> Tip:
> If you are testing the version in the current repository where the Article entity is implemented as an Aptos Object,
> the descriptions in this section do not apply.

```shell
curl 'https://fullnode.devnet.aptoslabs.com/v1/accounts/{RESOURCE_ACCOUNT_ADDRESS}/resource/{ACCOUNT_ADDRESS}::article::Tables'
```

The output is similar to the following:

```json
{"type":"{ACCOUNT_ADDRESS}::article::Tables","data":{"article_table":{"handle":"{ARTICLE_TABLE_HANDLE}"}}}
```

In the location `{ARTICLE_TABLE_HANDLE}` above, the article table handle will be displayed.

---

Now, we can read the information of articles.

#### Read Articles

> Tip:
> If you are testing the version in the current repository where the Article entity is implemented as an Aptos Object,
> the descriptions in this section do not apply.

You can read the information of an article like this, let's assume that the ID of the article to be read is `1` (note that replacing the placeholders with their actual values):

```shell
curl --request POST \
--header 'Content-Type: application/json' \
--url https://fullnode.devnet.aptoslabs.com/v1/tables/{ARTICLE_TABLE_HANDLE}/item \
--data '{"key_type":"u128","value_type":"{ACCOUNT_ADDRESS}::article::Article","key":"1"}'
```

#### Update Articles

You can update an article like this: (Note that the first argument after --args is the object ID of the article, please replace it with the actual value.)

```shell
aptos move run --function-id 'default::article_aggregate::update' \
--args u128:1 'string:foo' 'string:bar' address:{ACCOUNT_ADDRESS} \
--assume-yes

# If you're testing the current version in the repository, you need to do like this:
#aptos move run --function-id 'default::article_aggregate::update' \
#--args address:0xcf73c1a4dce2d7bbdabc7fd7da5ae8101e0a39815ee1ddd5af530c4f2eb08e16 'string:foo' 'string:bar' address:8bc9a5fab9a68b62117ac3aff4917eacf05dd633a766a689dd14707abeb51738 \
#--assume-yes

# View the updated article object information
#curl --request GET \
#  --url https://fullnode.devnet.aptoslabs.com/v1/accounts/0xcf73c1a4dce2d7bbdabc7fd7da5ae8101e0a39815ee1ddd5af530c4f2eb08e16/resources \
#  --header 'Accept: application/json, application/x-bcs'
```

Then re-query the article's information to see if it has been updated successfully:

#### Delete Articles

You can delete an article like this:

```shell
aptos move run --function-id 'default::article_aggregate::delete' \
--args u128:1 \
--assume-yes
```

Query the article again, and it should now return a message like this:

```json
{"message":"Table Item not found by Table handle({ARTICLE_TABLE_HANDLE}), Table key(\"1\") and Ledger version(15756815)","error_code":"table_item_not_found","vm_error_code":null}
```

### CRUD Comments

#### Add Comments

You can add a comment like this:

```shell
aptos move run --function-id 'default::article_aggregate::add_comment' \
--args u128:2 u64:1 'string:ATestComment1' 'string:body' address:{ACCOUNT_ADDRESS} \
--assume-yes

# If you're testing the current version in the repository, you need to do like this:
#aptos move run --function-id 'default::article_aggregate::add_comment' \
#--args address:0xb761562e360c04f1b408ab8c9168e14a81991f2fd903905dbda5a93f28a257f1 'string:ATestComment1' 'string:body' address:0x8bc9a5fab9a68b62117ac3aff4917eacf05dd633a766a689dd14707abeb51738 \
#--assume-yes
```

Add more comment like this:

```shell
aptos move run --function-id 'default::article_aggregate::add_comment' \
--args u128:2 u64:2 'string:ATestComment1' 'string:body' address:{ACCOUNT_ADDRESS} \
--assume-yes
```

#### Read Comments

##### Get Comment Table Handle

Can get the comment table handle of the second article like this:

```shell
curl --request POST \
--header 'Content-Type: application/json' \
--url https://fullnode.devnet.aptoslabs.com/v1/tables/{ARTICLE_TABLE_HANDLE}/item \
--data '{"key_type":"u128","value_type":"{ACCOUNT_ADDRESS}::article::Article","key":"2"}'
```

The output is similar to:

```json
{"article_id":"2","body":"world2","comments":{"inner":{"handle":"{COMMENT_TABLE_HANDLE}"},"length":"4"},"owner":"{ACCOUNT_ADDRESS}","title":"hello","version":"4"}
```

The comment table handle of the article appears at `{COMMENT_TABLE_HANDLE}` this location. Note down this comment table handle. The placeholder `{ARTICLE_TABLE_HANDLE}` in the following commands need to be replaced with this value.

##### Read Comment by Table Handle and Key

```shell
curl --request POST \
--header 'Content-Type: application/json' \
--url https://fullnode.devnet.aptoslabs.com/v1/tables/{COMMENT_TABLE_HANDLE}/item \
--data '{"key_type":"u64","value_type":"{ACCOUNT_ADDRESS}::comment::Comment","key":"1"}'

# For example:
#curl --request POST \
#--header 'Content-Type: application/json' \
#--url https://fullnode.devnet.aptoslabs.com/v1/tables/0xfa16370a4cce745d3e251f20a30746dfe7d46ec753b8dab088a3f250a434b4d2/item \
#--data '{"key_type":"u64","value_type":"0x8bc9a5fab9a68b62117ac3aff4917eacf05dd633a766a689dd14707abeb51738::comment::Comment","key":"1"}'
```

#### Update Comments

We can submit a transaction like this to update a comment:

```shell
aptos move run --function-id 'default::article_aggregate::update_comment' \
--args u128:2 u64:1 'string:commenter1' 'string:updated_comment' address:{ACCOUNT_ADDRESS} \
--assume-yes
```

Then we can query the comment state again to see if the comment content has been updated.

#### Remove Comments

We can submit a transaction like this to delete a comment:

```shell
aptos move run --function-id 'default::article_aggregate::remove_comment' \
--args u128:2 u64:1 \
--assume-yes
```

### CRUD Tags

```shell
aptos move run --function-id 'default::tag_aggregate::create' \
--args string:"TEST" \
--assume-yes

# Assuming article Id: "0x6880ae199b71da328ea0eee9e4435b923577d503c9c70cc0328cb29cca918a2a"
# Assuming tag Id: "0x15f14661eaafb0369e650f567f22ac6bcbbe0665ef4f0c91035d8d51b6b90f6"
# Run this to add tag to article:
#aptos move run --function-id 'default::article_aggregate::add_tag' \
#--args \
#  address:"0x6880ae199b71da328ea0eee9e4435b923577d503c9c70cc0328cb29cca918a2a" \
#  address:"0x15f14661eaafb0369e650f567f22ac6bcbbe0665ef4f0c91035d8d51b6b90f6" \
#--assume-yes
```


### Test Off-chain Service

After running the latest version of the dddappp tool, an Off-chain service project will be generated in the `aptos-java-service` directory.
It can pull application events and entity states on the chain into the off-chain database, and provides query APIs.

#### Creating and Initialize Database for Off-Chain Service

Use a MySQL client to connect to the local MySQL server and execute the following script to create an empty database (assuming the name is `test8`):

```sql
CREATE SCHEMA `test8` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
```

Go to the `aptos-java-service` directory and package the Java project:

```shell
mvn package
```

Then, run a command-line tool to initialize the database:

```shell
java -jar ./aptosblogdemo-service-cli/target/aptosblogdemo-service-cli-0.0.1-SNAPSHOT.jar ddl -d "./scripts" -c "jdbc:mysql://127.0.0.1:3306/test8?enabledTLSProtocols=TLSv1.2&characterEncoding=utf8&serverTimezone=GMT%2b0&useLegacyDatetimeCode=false" -u root -p 123456
```


#### Configuring Off-Chain Service

Open the `application-test.yml` file located in the directory `aptos-java-service/aptosblogdemo-service-rest/src/main/resources` and set the published contract address.
After setting, it should look like this:

```yaml
aptos:
  contract:
    address:
      "0x8bc9a5fab9a68b62117ac3aff4917eacf05dd633a766a689dd14707abeb51738"
    node-api:
      base-url: "https://fullnode.devnet.aptoslabs.com/v1"
```

#### Starting Off-Chain Service

In the `aptos-java-service` directory, run the following command to start the off-chain service:

```shell
mvn -pl aptosblogdemo-service-rest -am spring-boot:run
```

#### Query Off-chain Service APIs

You can use the following command to query article list:

```shell
curl http://localhost:1028/api/Articles
```

[TBD]

## Others

### A More Complex Aptos Demo

If you are interested, you can find a more complex Aptos Demo here: ["A Aptos Demo"](https://github.com/dddappp/A-Aptos-Demo).

### Blog Example for Rooch

Here is a Rooch version like this blog example: https://github.com/rooch-network/rooch/blob/main/examples/blog/README.md

