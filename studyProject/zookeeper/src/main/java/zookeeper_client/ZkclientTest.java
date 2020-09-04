package zookeeper_client;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

public class ZkclientTest {
    ZkClient zkClient;

    @Before
    public void init(){
        String conn = "192.168.41.4:2181";
        zkClient = new ZkClient(conn,5000,5000);
    }

    @Test
    public void createNode() throws InterruptedException {
        User user = new User("zhangsan","1");
        //zkClient.createPersistentSequential("/zkClient/zk",user);
        //zkClient.writeData("/zkClient",user);
       // User user1 = zkClient.readData("/zkClient");
       //System.out.println(user1);
       // System.out.println(zkClient.exists("/zk"));
       // List<String> children = zkClient.getChildren("/zkClient");
       // children.stream().forEach(System.out::println);
//        for (int i = 0; i < children.size(); i++) {
//            System.out.println(children.get(i));
//        }
        zkClient.subscribeChildChanges("/zkClient", (parentPath, currentChilds) -> {
            System.out.println("父节点路径parentPath："+parentPath);
            System.out.println("子节点currentChilds："+currentChilds);
        });
        zkClient.subscribeDataChanges("/zkClient/zk0000000005", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("路径："+dataPath+"，改变数据:"+data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("删除节点："+dataPath);
            }
        });
       // zkClient.writeData("/zkClient/zk0000000005","11112");
        //zkCient不能读取客户端set的数据，是由于序列化的原因
        zkClient.createPersistentSequential("/zkClient/zk","hehehe");
        Object o = zkClient.readData("/zkClient/zk0000000008");
        System.out.println(o.toString());
        Thread.sleep(Long.MAX_VALUE);
    }

}

class User implements Serializable{
    String name;
    String sex;

    public User(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}