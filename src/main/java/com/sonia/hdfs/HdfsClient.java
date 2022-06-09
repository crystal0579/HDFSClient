package com.sonia.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 创建一个客户端对象 打开目标地址session
 * 执行操作
 * 关闭资源
 * 代表: HDFS Zookeeper
 */
public class HdfsClient {

    private FileSystem fs;
    private Configuration configuration;

    @Before
    public void init() throws URISyntaxException, IOException, InterruptedException {
        //1. 获取文件系统
        configuration = new Configuration();
        /**
         * 一定要加在 fs 值获得前
         */
        configuration.set("dfs.replication", "2");//但是这一改，也是相当于改全局了，因为cf是全局的cf

        //   找到NameNode的承载者
        fs = FileSystem.get(new URI("hdfs://hadoop102:8020"), configuration, "sonia");
    }

    @After
    public void close() throws IOException {
        //3. 关闭资源
        fs.close();
    }

    /**
     * 创建文件夹
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    @Test
    public void testMkdirs()throws IOException{
        //2. 创建目录
        fs.mkdirs(new Path("/xiyou/huaguoshan"));

    }

    /**
     * 上传
     */
    @Test
    public void testPut() throws IOException {
        //参数1：是否删除源数据
        //参数2：若有重文件，是否覆盖目标文件
        //参数3：源
        //参数4：目标  /xiyou/huaguoshan  也可以写 hdfs://hadoop102:/xiyou/huaguoshan   当然后面也可以跟上目标文件名
//        fs.copyFromLocalFile(false, true, new Path("C:\\Users\\wengj\\Documents\\study\\hadoop\\test\\sunwukong.txt"), new Path("/xiyou/huaguoshan"));


        /**
         * 自从添加了hdfs-site.xml配置后，备份数直接生效
         * 可见 配置文件的优先级如下：
         * hdfs-default.xml < hdfs-site.xml < java 客户端 本地资源目录里的 hdfs-site.xml
         */
//        fs.copyFromLocalFile(false, true, new Path("C:\\Users\\wengj\\Documents\\study\\hadoop\\test\\sunwukong1.txt"), new Path("/xiyou/huaguoshan"));


        /**
         * 代码里直接设置
         * 可见优先级是
         * hdfs-default.xml < hdfs-site.xml < java 客户端 本地资源目录里的 hdfs-site.xml < 代码里直接定义该节点属性
         */
        /**
         * 这句话写在这里是不起作用的
         * 因为 fs 是从老的 configuration 那里拿来的  即
         * fs = FileSystem.get(new URI("hdfs://hadoop102:8020"), configuration, "sonia");
         * 而你下面这句话明显发生在 fs已经获得后
         */
//        configuration.set("dfs.replication", "2");//但是这一改，也是相当于改全局了，因为cf是全局的cf
        fs.copyFromLocalFile(false, true, new Path("C:\\Users\\wengj\\Documents\\study\\hadoop\\test\\sunwukong2.txt"), new Path("/xiyou/huaguoshan"));
    }

    /**
     * 下载
     */

}
