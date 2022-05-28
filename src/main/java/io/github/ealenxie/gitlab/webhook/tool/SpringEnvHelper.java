package io.github.ealenxie.gitlab.webhook.tool;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author EalenXie create on 2020/7/24 10:24
 */
public class SpringEnvHelper implements ApplicationContextAware {

    /**
     * 应用名
     */
    private static String appName;
    private static final String[] DEV = {"dev", "default", "develop"};
    private static final String[] SIT = {"sit", "test", "uat"};
    private static final String[] PROD = {"prod", "prd", "pro", "master"};
    private static String[] profiles;
    /**
     * 程序启动模式
     */
    private static Boolean isDebug;
    /**
     * 本机真实Ip
     */
    private static String localhostIp;
    /**
     * 当前应用进程号
     */
    private static Integer pid;
    private static Boolean isLinuxOS;
    private static final String UNKNOWN = "unknown";

    private static Integer port;

    @Override
    public void setApplicationContext(@Autowired @NonNull ApplicationContext applicationContext) {
        setAppName(applicationContext);
        setProfiles(applicationContext);
        setPort(applicationContext);
        System.getProperties().setProperty("info.tags.mode", isDebugMode() ? "DEBUG" : "RUNNING");
    }

    /**
     * 获取应用名称
     */
    public static String getAppName() {
        return appName;
    }

    /**
     * 设置应用名称
     */
    public static void setAppName(String name) {
        appName = name;
    }


    /**
     * 判断是否是开发环境
     */
    public static boolean isDev() {
        return isEnv(DEV);
    }

    /**
     * 判断是否是测试环境
     */
    public static boolean isSit() {
        return isEnv(SIT);
    }

    /**
     * 判断是否是生产环境
     */
    public static boolean isProd() {
        return isEnv(PROD);
    }

    /**
     * 程序是否以DEBUG模式运行
     */
    public static boolean isDebugMode() {
        if (isDebug == null) {
            isDebug = false;
            final Pattern debugPattern = Pattern.compile("-Xdebug|jdwp");
            RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
            List<String> inputArguments = mxBean.getInputArguments();
            for (String arg : inputArguments) {
                if (debugPattern.matcher(arg).find()) {
                    isDebug = true;
                    break;
                }
            }
        }
        return isDebug;
    }

    public static Integer getPort() {
        return port;
    }

    /**
     * 获取当前进程号
     */
    public static int getProcessId() {
        if (pid == null) {
            try {
                pid = Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
            } catch (Exception e) {
                e.printStackTrace();
                pid = 0;
            }
        }
        return pid;
    }

    /**
     * 判断是否是linux操作系统
     */
    public static boolean isLinuxOS() {
        if (isLinuxOS == null) {
            isLinuxOS = "Linux".equalsIgnoreCase(System.getProperty("os.name"));
        }
        return isLinuxOS;
    }

    /**
     * 获取本机真实IP
     */
    public static String getLocalhostIp() {
        if (localhostIp == null) {
            try {
                localhostIp = getLocalIpAddress0();
            } catch (IOException e) {
                e.printStackTrace();
                localhostIp = UNKNOWN.toUpperCase();
            }
        }
        return localhostIp;
    }

    /**
     * 判断环境
     */
    public static boolean isEnv(String... envKeys) {
        if (profiles == null) {
            throw new NoSuchBeanDefinitionException(SpringEnvHelper.class);
        }
        if (envKeys == null || envKeys.length == 0) {
            return false;
        }
        if (profiles == envKeys) {
            return true;
        }
        for (String profile : profiles) {
            for (String env : envKeys) {
                if (env.equalsIgnoreCase(profile)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 设置程序profile
     */
    private static void setProfiles(ApplicationContext applicationContext) {
        Environment environment = applicationContext.getEnvironment();
        profiles = environment.getActiveProfiles();
        if (profiles.length == 0) {
            profiles = environment.getDefaultProfiles();
        }
        if (profiles.length == 0) {
            profiles = DEV;
        }
    }


    /**
     * 设置程序profile
     */
    private static void setPort(ApplicationContext applicationContext) {
        Environment environment = applicationContext.getEnvironment();
        String portString = environment.getProperty("server.port");
        port = Integer.parseInt(StringUtils.isEmpty(portString) ? "8080" : portString);

    }


    /**
     * 设置应用名称
     */
    private static void setAppName(ApplicationContext applicationContext) {
        Environment environment = applicationContext.getEnvironment();
        String name = environment.getProperty("spring.application.name");
        if (name != null) {
            appName = name;
            return;
        }
        if (applicationContext.getId() != null) {
            appName = applicationContext.getId();
            return;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if ("main".equals(stackTraceElement.getMethodName())) {
                name = stackTraceElement.getFileName();
                break;
            }
        }
        if (name != null) {
            appName = name;
            return;
        }
        appName = applicationContext.getApplicationName();
    }


    /**
     * 判断非虚拟网卡,非环回地址,包含有效InetAddresses
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

    /**
     * 判断非IPV6地址
     */
    public static boolean isNotIpV6(InetAddress address) {
        Pattern compile = Pattern.compile("::|0:0:|fe80");
        String ip = address.getHostAddress();
        return !compile.matcher(ip).find();
    }

    /**
     * 获取本机网卡第一个IPv4 非 docker 网卡/非环回地址
     *
     * @return Get the first IPv4 of the native network card
     * @throws IOException Resolving native Ip may throw UnknownHostException
     */
    public static String getLocalIpAddress0() throws IOException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            if (!isLoopNetwork(ni)) {
                Enumeration<InetAddress> ipAddresses = ni.getInetAddresses();
                while (ipAddresses.hasMoreElements()) {
                    InetAddress address = ipAddresses.nextElement();
                    if (isNotIpV6(address)) {
                        return address.getHostAddress();
                    }
                }
            }
        }
        throw new UnsupportedOperationException(new UnknownHostException());
    }


    /**
     * 获取请求客户端真实IP
     */
    public static String getWebServletClientIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String clientIp = request.getHeader("x-forwarded-for");
        if (!StringUtils.isEmpty(clientIp) && !UNKNOWN.equals(clientIp)) {
            return clientIp.contains(",") ? clientIp.substring(0, clientIp.indexOf(",")) : clientIp;
        }
        clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (!StringUtils.isEmpty(clientIp) && !UNKNOWN.equals(clientIp)) return clientIp;
        clientIp = request.getHeader("Proxy-Client-IP");
        if (!StringUtils.isEmpty(clientIp) && !UNKNOWN.equals(clientIp)) return clientIp;
        clientIp = request.getHeader("WL-Proxy-Client-IP");
        if (!StringUtils.isEmpty(clientIp) && !UNKNOWN.equals(clientIp)) return clientIp;
        clientIp = request.getHeader("HTTP_CLIENT_IP");
        if (!StringUtils.isEmpty(clientIp) && !UNKNOWN.equals(clientIp)) return clientIp;
        clientIp = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(clientIp) && !UNKNOWN.equals(clientIp)) return clientIp;
        return request.getRemoteAddr();
    }
}
