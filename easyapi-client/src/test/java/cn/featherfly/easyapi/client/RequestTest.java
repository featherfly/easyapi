
package cn.featherfly.easyapi.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.testng.annotations.Test;

import cn.featherfly.common.http.ErrorListener;
import cn.featherfly.common.http.HttpClient;
import cn.featherfly.common.http.HttpMethod;
import cn.featherfly.easyapi.EnvironmentImpl;

/**
 * Test.
 *
 * @author zhongj
 */
public class RequestTest {

    @Test
    public void testServer() {
        HttpClient client = new HttpClient();
        DefaultHttpRequest request = new DefaultHttpRequest(new EnvironmentImpl(), client);

        String url = "http://sfp.device.cdzkdc.com/api/v1/device/bd60ee0f054f4e7d";

        Map<String, Serializable> params = new HashMap<>();
        params.put("snType", "SN");
        DeviceResult deviceResult = request.send(HttpMethod.GET, url, params, DeviceResult.class,
                (ErrorListener) error -> {
                    System.out.println(error.getMessage());
                });

        System.out.println(deviceResult);

        AtomicBoolean executed = new AtomicBoolean(false);

        request.sendCompletion(HttpMethod.GET, url, params, DeviceResult.class).success(r -> {
            System.out.println(r);
            executed.set(true);
        }).failure(r -> {
            System.err.println(r);
            executed.set(true);
        }).error(e -> {
            System.out.println(e.getMessage());
            executed.set(true);
        });

        request.sendCompletion(HttpMethod.GET, url, params, DeviceResult.class).completion(r -> {
            System.out.println("Completion2");
            System.out.println(r);
            executed.set(true);
        }, f -> {
            System.out.println("Completion2 failure");
            System.err.println(f);
            executed.set(true);
        }, e -> {
            System.out.println("Completion2 error");
            System.err.println(e.getMessage());
            executed.set(true);
        });

        request.sendObservable(HttpMethod.GET, url, params, DeviceResult.class).subscribe(r -> {
            System.out.println("Observable");
            r.completion(s -> {
                System.out.println("Observable Completion");
                System.out.println(s);
                executed.set(true);
            }, f -> {
                System.out.println("Observable Completion failure");
                System.err.println(f);
                executed.set(true);
            });
        }, e -> {
            System.out.println("Observable error");
            System.out.println(e.getMessage());
            executed.set(true);
        });

        while (!executed.get()) {

        }

        client.shutdown();
        System.out.println("end");
    }

    @Test
    public void testServer2() {
        HttpClient client = new HttpClient();
        DefaultHttpRequest request = new DefaultHttpRequest(new EnvironmentImpl(), client);

        String url = "http://sfp.device.cdzkdc.com/api/v1/device/bd60ee0f054f4e7d";

        Map<String, Serializable> params = new HashMap<>();
        params.put("snType", "SN");
        AtomicBoolean executed = new AtomicBoolean(false);

        request.sendObservable(HttpMethod.GET, url, params, DeviceResult.class).subscribe(r -> {
            System.out.println("Observable");
            Thread.sleep(5000);
            r.completion(s -> {
                System.out.println("Observable Completion");
                System.out.println(s);
                executed.set(true);
            }, f -> {
                System.out.println("Observable Completion failure");
                System.err.println(f);
                executed.set(true);
            });
        }, e -> {
            System.out.println("Observable error");
            System.out.println(e.getMessage());
            executed.set(true);
        });

        while (!executed.get()) {

        }

        client.shutdown();
        System.out.println("end");
    }
}
