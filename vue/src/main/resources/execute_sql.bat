@echo off
chcp 65001 >nul
echo 正在执行数据库脚本...
mysql -u root -pzwj520 tlias < db_schema.sql
if %errorlevel% == 0 (
    echo 数据库表创建成功！
) else (
    echo 数据库表创建失败，请检查MySQL服务是否启动，以及用户名密码是否正确。
)
pause


