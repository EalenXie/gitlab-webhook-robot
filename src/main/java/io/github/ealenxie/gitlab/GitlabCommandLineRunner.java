package io.github.ealenxie.gitlab;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * Created by EalenXie on 2022/2/18 9:38
 */
@Slf4j
@Component
public class GitlabCommandLineRunner implements CommandLineRunner {


    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Resource
    private Environment environment;

    /**
     * 获取Linux下的IP地址
     */
    private static String getLinuxLocalIp() throws SocketException, UnknownHostException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            if (isNotLoopNetwork(ni)) {
                Enumeration<InetAddress> ipAddresses = ni.getInetAddresses();
                while (ipAddresses.hasMoreElements()) {
                    InetAddress address = ipAddresses.nextElement();
                    if (isNotLoopIp(address)) {
                        return address.getHostAddress();
                    }
                }
            }
        }
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static boolean isNotLoopNetwork(NetworkInterface ni) {
        return !ni.getName().contains("docker") && !ni.getName().contains("lo");
    }

    public static boolean isNotLoopIp(InetAddress address) {
        if (!address.isLoopbackAddress()) {
            String ip = address.getHostAddress();
            return !ip.contains("::") && !ip.contains("0:0:") && !ip.contains("fe80");
        }
        return false;
    }

    @Override
    public void run(String... args) throws Exception {
        String ip;
        if ("Linux".equalsIgnoreCase(System.getProperty("os.name"))) {
            ip = getLinuxLocalIp();
        } else {
            ip = InetAddress.getLocalHost().getHostAddress();
        }
        String port = environment.getProperty("server.port");
        String contextPath = environment.getProperty("server.servlet.context-path");
        String contentPath;
        if (contextPath != null) {
            contentPath = String.format("%s://%s:%s%s", "http", ip, port, contextPath);
        } else {
            contentPath = String.format("%s://%s:%s", "http", ip, port);
        }
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            HandlerMethod value = m.getValue();
            if (value.getBeanType().equals(WebhookEndpoint.class)) {
                StringBuilder sb = new StringBuilder();
                RequestMappingInfo key = m.getKey();
                PatternsRequestCondition con = key.getPatternsCondition();
                if (!con.isEmpty()) {
                    Set<String> patterns = con.getPatterns();
                    sb.append(String.format("%s%s", contentPath, patterns.size() == 1 ? patterns.iterator().next() : patterns));
                }
                log.info("Please fill in this address in your Gitlab Webhook: {}", sb);
                break;
            }
        }
    }
}
