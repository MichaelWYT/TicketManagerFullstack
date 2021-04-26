variable "vpc_id" {
  description = "vpc id for production"
  type = string
}

variable "subnet1a" {
  description = "Subnet for the ec2 created for each AZ"
  type = string
}

variable "nginx_ip" {
  description = "Subnet for the ec2 created for each AZ"
  type = string
  default = "Hello"
}