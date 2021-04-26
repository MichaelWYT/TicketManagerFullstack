output "vpc_id" {
  value = aws_vpc.prod_vpc.id
}

output "subnet1a" {
  value = aws_subnet.subnet1a.id
}

# output "subnet1b_id" {
#   value = aws_vpc.subnet1b_id.id
# }

# output "subnet1c_id" {
#   value = aws_vpc.subnet1c_id.id
# }
