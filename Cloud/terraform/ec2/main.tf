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

resource "aws_instance" "nginx" {
  ami = var.ami_id
  instance_type = var.instance_type_micro
  key_name = var.pem_key

  associate_public_ip_address = true
  subnet_id = var.subnet1a

  security_groups = [var.sg-ssh, var.sg-nginx]

  tags = {
      Name = "Nginx"
  }
}

resource "aws_instance" "frontend" {
  ami = var.ami_id
  instance_type = var.instance_type_micro
  key_name = var.pem_key

  associate_public_ip_address = true
  subnet_id = var.subnet1a

  security_groups = [var.sg-ssh, var.sg-frontend]

  tags = {
      Name = "frontend"
  }
}

resource "aws_instance" "backend" {
  ami = var.ami_id
  instance_type = var.instance_type_micro
  key_name = var.pem_key

  associate_public_ip_address = true
  subnet_id = var.subnet1a

  security_groups = [var.sg-ssh, var.sg-backend]

  tags = {
      Name = "backend"
  }
}