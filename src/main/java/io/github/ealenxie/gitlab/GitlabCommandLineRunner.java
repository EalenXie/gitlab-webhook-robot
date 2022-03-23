package io.github.ealenxie.gitlab;

import io.github.ealenxie.gitlab.webhook.WebHookWay;
import io.github.ealenxie.gitlab.webhook.WebhookEndpoint;
import io.github.ealenxie.gitlab.webhook.conf.WebHookConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * Created by EalenXie on 2022/2/18 9:38
 */
@Slf4j
@Component
public class GitlabCommandLineRunner implements CommandLineRunner {


    @Resource
    private Environment environment;
    @Resource
    private WebHookConfig webHookConfig;

    /**
     * 获取Linux下的IP地址
     */
    private static String getLocalIpV4() throws SocketException, UnknownHostException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            if (!isLoopNetwork(ni)) {
                Enumeration<InetAddress> ipAddresses = ni.getInetAddresses();
                while (ipAddresses.hasMoreElements()) {
                    InetAddress address = ipAddresses.nextElement();
                    if (isNotIpv6(address)) {
                        return address.getHostAddress();
                    }
                }
            }
        }
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * 判断虚拟网卡,非环回地址,包含有效InetAddresses
     */
    public static boolean isLoopNetwork(NetworkInterface ni) {
        if (!ni.getInetAddresses().hasMoreElements()) {
            return true;
        }
        Pattern compile = Pattern.compile("docker|lo|VMware|veth|flannel|cni");
        if (!compile.matcher(ni.getName()).find() && ni.getDisplayName() != null) {
            return compile.matcher(ni.getDisplayName()).find();
        }
        return true;
    }

    public static boolean isNotIpv6(InetAddress address) {
        if (!address.isLoopbackAddress()) {
            String ip = address.getHostAddress();
            return !ip.contains("::") && !ip.contains("0:0:") && !ip.contains("fe80");
        }
        return false;
    }

    @Override
    public void run(String... args) throws Exception {
        StringBuilder sb = new StringBuilder();
        String ip = getLocalIpV4();
        String port = environment.getProperty("server.port");
        String contextPath = environment.getProperty("server.servlet.context-path");
        String contentPath;
        if (contextPath != null) {
            contentPath = String.format("%s://%s:%s%s", "http", ip, port, contextPath);
        } else {
            contentPath = String.format("%s://%s:%s", "http", ip, port);
        }
        WebHookWay way = webHookConfig.getWay();
        sb.append(String.format("The message sending way is %s", way));
        sb.append(String.format(" , Please fill in this address in your Gitlab Webhook: %s%s%n", contentPath, WebhookEndpoint.PIPELINE_ENDPOINT_URL));
        log.info(sb.toString());
    }
}
