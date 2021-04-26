output "sg-ssh" {
    value = aws_security_group.ssh.id
}

output "sg-nginx" {
    value = aws_security_group.sg-nginx.id
}

output "sg-frontend" {
    value = aws_security_group.sg-frontend.id
}

output "sg-backend" {
    value = aws_security_group.sg-backend.id
}