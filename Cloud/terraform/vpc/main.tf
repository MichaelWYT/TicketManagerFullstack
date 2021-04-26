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

resource "aws_vpc" "prod_vpc" {
  cidr_block       = "20.0.0.0/16"
  instance_tenancy = "default"

  tags = {
    Name = "prod_vpc"
  }
}

resource "aws_subnet" "subnet1a" {
  vpc_id            = aws_vpc.prod_vpc.id
  cidr_block        = "20.0.0.0/24"
  availability_zone = "eu-west-1a"

  tags = {
    Name = "subnet1a"
  }
}

resource "aws_subnet" "subnet1b" {
  vpc_id            = aws_vpc.prod_vpc.id
  cidr_block        = "20.0.1.0/24"
  availability_zone = "eu-west-1b"

  tags = {
    Name = "subnet1b"
  }
}

resource "aws_subnet" "subnet1c" {
  vpc_id            = aws_vpc.prod_vpc.id
  cidr_block        = "20.0.2.0/24"
  availability_zone = "eu-west-1c"

  tags = {
    Name = "subnet1c"
  }
}

resource "aws_internet_gateway" "prod_igw" {
  vpc_id = aws_vpc.prod_vpc.id

  tags = {
    Name = "prod-igw"
  }
}

resource "aws_route_table" "prod_route" {
  vpc_id = aws_vpc.prod_vpc.id

  route {
    cidr_block = "0.0.0.0/16"
    gateway_id = aws_internet_gateway.prod_igw.id
  }

  tags = {
    Name = "prod-route"
  }
}

resource "aws_route_table_association" "prod_public_subnet" {
  depends_on = [
    aws_route_table.prod_route
  ]
  subnet_id      = aws_subnet.subnet1a.id
  route_table_id = aws_route_table.prod_route.id
}