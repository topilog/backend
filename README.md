## See Aslo

[**Topilog Frontend**](https://github.com/topilog/frontend)

## Build & Run

### 打包

```
mvn package
```

### 复制配置文件

```
cp -r src/main/resources/* target/resources/ 
```

### 编译前端文件

```
cd ${your_path}/topilog/frontend  # clone topilog/frontend
npm i
npm run build
```

### 复制前端文件

```
cd ${your_path}/target/resource
mkdir static
cp -r ${your_path}/topilog/frontend/dist/* static
```

### 运行

```
cd target
java -jar ${topilog.jar} --server.port=80
```



