# 跨进程数据不推荐使用sp共享数据
## getSharedPreferences多种方式
- **MODE_PRIVATE**：进程私有，多个进程拥有自己不同的sp文件
- **MODE_MULTI_PROCESS**：跨进程共享，但是不安全，可能会丢失数据，已经在6.0弃用
  如果多个进程在同一时间读写同一个数据，可能会读取出错

## 注意点
- getSharedPreferences后，如果其他地方修改了数据，想要获得最新数据，需要重新get
  因为sp数据会被整个缓存在内存中(猜测)