terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "3.37.0"
    }
  }
}

# Configure the AWS Provider
provider "aws" {
  version                 = "~> 3.37.0"
  region                  = "eu-west-1"
  shared_credentials_file = "~/.aws/credentials"
}

module "vpc" {
  source = "./vpc"
}

module "networks" {
  source = "./network"
  vpc_id = module.vpc.vpc_id
  subnet1a = module.vpc.subnet1a
}

module "ec2" {
  source = "./ec2"
  subnet1a = module.vpc.subnet1a
  sg-ssh = module.networks.sg-ssh
  sg-nginx = module.networks.sg-nginx
  sg-frontend = module.networks.sg-frontend
  sg-backend = module.networks.sg-backend
}