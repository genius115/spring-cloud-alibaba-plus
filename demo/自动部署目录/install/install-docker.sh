#!/bin/sh
echo '解压tar包...'
sudo tar -xvf $1
echo '将docker目录移到/usr/bin目录下...'
sudo cp docker/* /usr/bin/
echo '将docker.service 移到/etc/systemd/system/ 目录...'
sudo cp docker.service /etc/systemd/system/
echo '添加文件权限...'
sudo chmod +x /etc/systemd/system/docker.service
echo '重新加载配置文件...'
sudo systemctl daemon-reload
echo '启动docker...'
sudo systemctl start docker
echo '设置开机自启...'
sudo systemctl enable docker.service
echo 'docker安装成功...'
docker -v