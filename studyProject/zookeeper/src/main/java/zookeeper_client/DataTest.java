package zookeeper_client;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataTest {
    ZooKeeper zooKeeper;

    @Before
    public void init() throws IOException {
        String conn = "192.168.41.4:2181";
        zooKeeper = new ZooKeeper(conn, 4000, event -> {
            System.out.println(event.getPath());
            System.out.println(event);
        });
    }
    @Test
    public  void getData() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/test", false, null);
        System.out.println(new String(data));
    }

    @Test
    public  void getData2() throws KeeperException, InterruptedException {
        //watch 为true是添加监听
        byte[] data = zooKeeper.getData("/test", true, null);
        System.out.println(new String(data));
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public  void getData3() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        //watch 为true是添加一次性监听，自定义监听
        byte[] data = zooKeeper.getData("/test", new Watcher(){
            @Override
            public void process(WatchedEvent event) {
                try {
                    //重新建立监听
                    zooKeeper.getData(event.getPath(),this,null);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("my watcher"+event.getPath());
                System.out.println("my watcher"+event);
            }
        },stat);
        System.out.println(new String(data));
        System.out.println(stat);
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public  void getData4() throws KeeperException, InterruptedException {
       zooKeeper.getData("/test", false, (rc, path, ctx, data, stat) -> {
          System.out.println(rc);
           System.out.println(path);
           System.out.println(stat);
       },"");
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void getChild() throws KeeperException, InterruptedException {
        //获取子节点
        List<String> children = zooKeeper.getChildren("/test", false);
        children.stream().forEach(System.out::println);
    }

    @Test
    public void getChild2() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/test", event -> {
            System.out.println(event.getPath());
            try {
                zooKeeper.getChildren(event.getPath(), true);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        children.stream().forEach(System.out::println);
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void getChild3() throws KeeperException, InterruptedException {
        zooKeeper.getChildren("/test/client2", null).stream().forEach(System.out::println);
        Stat stat = new Stat();
        zooKeeper.getChildren("/test/client2", null, stat);
        System.out.println(stat);
    }

    @Test
   public  void  createNode() throws KeeperException, InterruptedException {
       List<ACL> list= new ArrayList<>();
       int perm = ZooDefs.Perms.CREATE|ZooDefs.Perms.READ;
       ACL acl = new ACL(perm,new Id("world","anyone"));
       list.add(acl);
       zooKeeper.create("/test/client","hello".getBytes(),list, CreateMode.PERSISTENT_SEQUENTIAL);
   }
}
