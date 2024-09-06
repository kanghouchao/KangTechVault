variable "instance_name" {
  description = "Value of the Name tag for the EC2 instance"
  type        = string
  default     = "KangTechVaultAppServerInstance"
}

variable "AWS_ACCESS_KEY_ID" {
  type = string
  description = "AWS Access Key ID for authentication"
}

variable "AWS_SECRET_ACCESS_KEY" {
  type = string
  description = "AWS Access Key for authentication"
}