module "vpc" {
  source = "./modules/vpc"

  vpc_cidr_block = "10.0.0.0/16"
  public_subnet_cidr_block = "10.0.1.0/24"
  private_subnet_cidr_block = "10.0.2.0/24"
}

module "security_group" {
  source = "./modules/security_group"

  vpc_id = module.vpc.vpc_id
}

module "instance" {
  source = "./modules/instance"

  ami           = "ami-0b2cd2a95639e0e5b"
  instance_type = "t2.micro"
  key_name      = "kang_from_mac"
  public_subnet_id = module.vpc.public_subnet_id
  security_group_id = module.security_group.sg_id
}