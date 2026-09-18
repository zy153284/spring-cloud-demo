# spring-cloud-demo

Spring Cloud Alibaba 微服务入门 Demo：订单 / 库存 / 积分。

## 模块说明

| 模块 | 端口 | 说明 |
|---|---|---|
| cloud-gateway | 8080 | 网关，路由到 order/stock/credit |
| stock-service | 8081 | 库存服务：扣减库存 |
| order-service | 8082 | 订单服务：下单，通过 OpenFeign 调用库存和积分 |
| credit-service | 8083 | 积分服务：增加积分 |

## 启动步骤

### 1. 启动 Nacos

```bash
cd d:\codebuddy\spring-cloud-demo
docker compose up -d
```

等待 Nacos 就绪：访问 http://127.0.0.1:8848/nacos （默认账号/密码：`nacos` / `nacos`）。

### 2. 编译

```bash
mvn clean install -DskipTests
```

### 3. 启动服务

按顺序启动（后启动的依赖先启动的）：

```bash
# 终端 1：库存服务
mvn spring-boot:run -pl stock-service

# 终端 2：积分服务
mvn spring-boot:run -pl credit-service

# 终端 3：订单服务
mvn spring-boot:run -pl order-service

# 终端 4：网关
mvn spring-boot:run -pl cloud-gateway
```

### 4. 测试接口

直接访问服务：

```bash
# 库存
http://localhost:8081/stock/deduct/1/5
http://localhost:8081/stock/getIpAndPort

# 积分
http://localhost:8083/credit/add/1/50
http://localhost:8083/credit/getIpAndPort

# 下单（会调用库存扣减 + 积分增加）
http://localhost:8082/order/create/1/1/5
http://localhost:8082/order/getIpAndPort
```

通过网关访问：

```bash
http://localhost:8080/stock/deduct/1/5
http://localhost:8080/credit/add/1/50
http://localhost:8080/order/create/1/1/5
```

## 技术栈

- Spring Boot 3.2.5 / JDK 17
- Spring Cloud 2023.0.1
- Spring Cloud Alibaba 2023.0.1.0
- Nacos 2.3.0（服务注册发现）
- Spring Cloud Gateway（网关）
- OpenFeign（服务间调用）
