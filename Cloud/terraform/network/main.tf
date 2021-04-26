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

resource "aws_security_group" "ssh" {
  description = "Allow ssh inbound traffic"
  vpc_id      = var.vpc_id

  ingress {
    description      = "ssh inbound"
    from_port        = 22
    to_port          = 22
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  tags = {
    Name = "ssh"
  }
}

resource "aws_security_group" "sg-nginx" {
  description = "Allow ssh inbound traffic"
  vpc_id      = var.vpc_id

  ingress {
    description      = "sg_nginx inbound"
    from_port        = 80
    to_port          = 80
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  tags = {
    Name = "ssh"
  }
}

resource "aws_security_group" "sg-frontend" {
  description = "Allow ssh inbound traffic"
  vpc_id      = var.vpc_id

  ingress {
    description      = "sg-frontend inbound"
    from_port        = 3000
    to_port          = 3000
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  tags = {
    Name = "ssh"
  }
}

resource "aws_security_group" "sg-backend" {
  description = "Allow ssh inbound traffic"
  vpc_id      = var.vpc_id

  ingress {
    description      = "sg-backend inbound"
    from_port        = 8081
    to_port          = 8081
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  tags = {
    Name = "ssh"
  }
}