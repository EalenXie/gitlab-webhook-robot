Java版 Gitlab Webhook + 机器人通知
======

这是一个简单的SpringBoot项目,主要用于接入Gitlab的webhook 通过机器人实现消息通知 支持企业微信机器人/钉钉机器人

#### 实现效果 :

##### 推送事件(Push Hook):

![image.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5b40cf05991c4e09be7b1a6cc6878bc9~tplv-k3u1fbpfcp-watermark.image?)

##### 议题事件(Issue Hook):

![image.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0bd1d11e732e45e7bd99a2e0a5731bdc~tplv-k3u1fbpfcp-watermark.image?)

##### 流水线事件(Pipeline Hook):

![image.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/be50a07007fe493c83ecb7e0491625bb~tplv-k3u1fbpfcp-watermark.image?)

##### 合并请求事件(Merge Request Hook):

![image.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/26ecf69c83b14f7ab53b3ecc974230e3~tplv-k3u1fbpfcp-watermark.image?)

#### 快速开始

##### 1. 配置项目`gitlab-webhook-robot`的机器人

例如钉钉机器人:
```yaml
webhook:
  ## webhook的消息发送方式为钉钉机器人
  way: ding
  ding:
    ## 请配置钉钉机器人的access-token
    access-token: 93axxxxahfjahklhjfxxxxx46f655ae0xxxxxxxxxx
    ## 请配置钉钉机器人sign-key
    sign-key: SECcd68c6bxxxxxxxx614xxxc926xxxxxxxxxxe811656bdd0
```
例如企业微信机器人:
```yaml
webhook:
  ## webhook的消息发送方式为企业微信机器人
  way: wechat
  wechat:
    ## 请配置企业机器人的key
    key: 1xxxxxxx-xxxx-49ce-xxxx-4cd44xxxxxxx
```


##### 2. 部署启动项目`gitlab-webhook-robot`

##### 3. 配置Gitlab项目的webhook。包括接口地址,支持的事件

`gitlab-webhook-robot`的接口为`http://ip:port/actuator/gitlab/webhook`

- 目前已实现的机器人
- [x] 钉钉机器人
- [x] 企业微信机器人


- 目前已实现对以下事件的通知
- [x] Push Hook
- [x] Pipeline Hook
- [x] Merge Request Hook
- [x] Issue Hook
- [x] Releases Hook
- [x] Note Hook
- [x] Tag Push Hook
- [x] Job Hook
- 目前暂未实现的事件
- [ ] Confidential Issue Hook
- [ ] Confidential Note Hook
- [ ] Wiki Page Hook
- [ ] Deployment Hook
- [ ] Feature Flag Hook
