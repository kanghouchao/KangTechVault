# Terraform 初級プロジェクト

このプロジェクトは、Terraformを使用して基本的なリソースをデプロイするためのコードを提供します。  
使用するには、HashiCorp Cloud Platform (HCP) に登録し、組織を設定する必要があります。

## 必要条件

- Terraformのインストールが完了していること
- HashiCorp Cloud Platform (HCP) アカウントを作成済み
- HCP内で組織を作成すること

## 使用方法

### 1. HCP Terraformアカウントの登録

Terraformを使用するには、まず[HashiCorp Cloud Platform (HCP)](https://cloud.hashicorp.com/products/terraform) にアカウントを作成し、組織を定義してください。

プロジェクトでは、`organization` のデフォルト名として `kanhouchou` を使用していますが、これは自由に変更可能です。お使いの環境に合わせて適切な名前を設定してください。

### 2. Terraform CLI ログイン

Terraform CLI でのログインについては、[公式ドキュメント](https://developer.hashicorp.com/terraform/tutorials/aws-get-started/aws-remote#authenticate-with-terraform-cloud)を参照してください。

### 3. 初期化

次に、プロジェクトディレクトリに移動し、`terraform init` コマンドを使用してTerraformを初期化します。

### 4. 実行

初期化が完了したら、`terraform apply` コマンドを使用してリソースのデプロイを実行します。

Terraformが設定に基づいてリソースを展開し、その結果を表示します。

## 注意点

- `organization` 名を変更した場合、Terraform設定ファイル内の該当箇所も合わせて更新してください。
- `terraform apply` を実行する前に、すべての設定が正しく完了していることを確認してください。

## ライセンス

このプロジェクトはMITライセンスの下で提供されています。詳細については、`LICENSE` ファイルを参照してください。