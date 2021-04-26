variable "ami_id" {
  description = "AMI ID of ubuntu 20.04 LTS eu-west-1"
  default     = "ami-08bac620dc84221eb"
}

variable "instance_type_micro" {
  description = "Free tier EC2 Instance type"
  default     = "t2.micro"
}

variable "pem_key" {
  description = "Associated Key to SSH into the EC2 Instance"
  default     = "my-first-instance"
}

variable "subnet1a" {
  description = "Subnet for the ec2 created for each AZ"
  type = string
}

variable "sg-ssh" {
  description = "ssh sg group"
  type = string
}

variable "sg-nginx" {
  description = "nginx sg group"
  type = string
}

variable "sg-frontend" {
  description = "frontend sg group"
  type = string
}

variable "sg-backend" {
  description = "backend sg group"
  type = string
}