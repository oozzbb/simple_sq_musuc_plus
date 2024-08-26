@echo off
chcp 65001 >nul 2>&1
if errorlevel 1 chcp 936 >nul

set repo_owner=59799517
set repo_name=simple_sq_musuc_plus
@REM
@REM set "db_ip="
@REM set /p db_ip=请输入数据库iP(默认：127.0.0.1):
@REM if "%db_ip%" == "" (
@REM     set "db_ip=127.0.0.1"
@REM )
@REM echo 您使用的名称是: %db_ip%
@REM set /p db_port=请输入数据库端口(默认：3306):
@REM if "%db_port%" == "" (
@REM     set "db_port=3306"
@REM )
@REM echo 您使用的端口是: %db_port%
@REM set /p db_user=请输入数据库用户名(默认：root):
@REM if "%db_user%" == "" (
@REM     set "db_user=root"
@REM )
@REM echo 您使用的用户名是: %db_user%
@REM set /p db_password=请输入数据库密码(默认：root):
@REM if "%db_password%" == "" (
@REM     set "db_password=root"
@REM )
@REM echo 您使用的密码是: %db_password%

@REM echo 正在下载依赖...
@REM echo 下载jar包...
set api_url=https://api.github.com/repos/%repo_owner%/%repo_name%/releases/latest
::发送HTTP GET请求并解析JSON响应
for /f "usebackq tokens=*" %%G in (`powershell -Command "& {iwr -UseBasicParsing '%api_url%' | ConvertFrom-Json}"`) do (
    set tag_name=%%G.tag_name
    for /f "usebackq tokens=*" %%H in (`powershell -Command "& {iwr -UseBasicParsing '%api_url%' | ConvertFrom-Json | Select-Object -ExpandProperty assets | Where-Object { $_.name -like 'MusicServer*.jar' } | Select-Object -First 1}"`) do (
        set asset_name=%%H.name
        set asset_url=%%H.browser_download_url
    )
)
echo tag_name： %tag_name%
echo asset_name： %asset_name%
echo asset_url： %asset_url%


set /p db_password=回车退出
