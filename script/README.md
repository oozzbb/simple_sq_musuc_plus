# linux 自动构建脚本

方式1：
./buildDOcker {端口} {挂载music路径} {挂载cache路径}  {host 网络名称} {true:false自动启动容器}
方式2：
修改脚本配置文件，然后执行脚本  ./buildDOcker

docker_port="${1:-8099}" #容器端口
docker_v_music="${2:-/music/}" #挂载的music路径地址
docker_v_cache="${3:-/cache/}" #挂载的cache路径地址（数据库地址）
docker_host_name="${4:-bridge}" #容器所属的host 如果不知道就天默认的
auto_start=${5:-true}  # 默认为 true 表示自动启动容器
