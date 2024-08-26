@echo off
chcp 65001 >nul 2>&1
if errorlevel 1 chcp 936 >nul
set "db_ip="
set /p db_ip=请输入数据库iP(默认：127.0.0.1):
if "%db_ip%" == "" (
    set "user_name=3306"
)
echo 您使用的名称是: %db_ip%
set /p db_port=请输入数据库端口(默认：3306):
if "%db_port%" == "" (
    set "db_port=3306"
)
echo 您使用的端口是: %db_port%
set /p db_user=请输入数据库用户名(默认：root):
if "%db_user%" == "" (
    set "db_user=root"
)
echo 您使用的用户名是: %db_user%
set /p db_password=请输入数据库密码(默认：root):
if "%db_password%" == "" (
    set "db_password=root"
)
echo 您使用的密码是: %db_password%
