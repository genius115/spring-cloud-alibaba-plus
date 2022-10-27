#!/bin/bash
echo "安装docker-compose"
echo "复制到安装目录" 
sudo cp ../docker-compose/docker-compose-Linux-x86_64 /usr/local/bin/docker-compose
echo "修改目录权限"
sudo chmod +x /usr/local/bin/docker-compose
echo "查看docker-compose版本"
sudo docker-compose -v
