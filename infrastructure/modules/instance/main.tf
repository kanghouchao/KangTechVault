resource "aws_instance" "this" {
  ami           = var.ami
  instance_type = var.instance_type
  key_name      = var.key_name
  subnet_id     = var.public_subnet_id
  security_groups = [var.security_group_id]

  tags = {
    Name = "CustomInstance"
  }
}

resource "aws_eip" "this" {
  vpc = true
}

resource "aws_eip_association" "this" {
  instance_id   = aws_instance.this.id
  allocation_id = aws_eip.this.id
}