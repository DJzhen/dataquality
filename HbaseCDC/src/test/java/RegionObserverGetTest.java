import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author wang zhen
 * @version 2018/12/18
 */
public class RegionObserverGetTest {
    Configuration config = null;
    Connection connection = null;

    @Before
    public void init() throws URISyntaxException, IOException {
        config = HBaseConfiguration.create();
        config.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
        config.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));
        connection = ConnectionFactory.createConnection(config);
    }

    @After
    public void quit() throws IOException {
        connection.close();
    }

    @Test
    public void putDate() throws IOException {
        String tableName = "table4";
        HTable table = (HTable) connection.getTable(TableName.valueOf(tableName));
        String rowKey = "row3";
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes("z1"),Bytes.toBytes("1c2"),Bytes.toBytes("\\"));
        table.put(put);
        table.close();
    }

    @Test
    public void insertMutiDate() throws IOException {
        String tableName = "table4";
        HTable table =(HTable) connection.getTable(TableName.valueOf(tableName));
        String rowKey = "row3";
        long timeStamp = 3L;

/*        Put put11 = new Put(Bytes.toBytes(rowKey));
        put11.addColumn(Bytes.toBytes("z1"), Bytes.toBytes("c1"), Bytes.toBytes("\ta"));
        Put put12 = new Put(Bytes.toBytes(rowKey));
        put12.addColumn(Bytes.toBytes("z1"), Bytes.toBytes("c2"), Bytes.toBytes("a"));
        Put put13 = new Put(Bytes.toBytes(rowKey));
        put13.addColumn(Bytes.toBytes("z1"), Bytes.toBytes("c3"), Bytes.toBytes("\\b"));
        Put put21 = new Put(Bytes.toBytes(rowKey));
        put21.addColumn(Bytes.toBytes("z2"), Bytes.toBytes("c1"), Bytes.toBytes("\nc"));
        Put put22 = new Put(Bytes.toBytes(rowKey));
        put22.addColumn(Bytes.toBytes("z2"), Bytes.toBytes("c2"), Bytes.toBytes("a"));
        Put put23 = new Put(Bytes.toBytes(rowKey));
        put23.addColumn(Bytes.toBytes("z2"), Bytes.toBytes("c3"), Bytes.toBytes("a"));
        Put put31 = new Put(Bytes.toBytes(rowKey));
        put31.addColumn(Bytes.toBytes("z3"), Bytes.toBytes("c1"), Bytes.toBytes("a"));
        Put put32 = new Put(Bytes.toBytes(rowKey));
        put32.addColumn(Bytes.toBytes("z3"), Bytes.toBytes("c2"), Bytes.toBytes("a"));
        Put put33 = new Put(Bytes.toBytes(rowKey));
        put33.addColumn(Bytes.toBytes("z3"), Bytes.toBytes("c3"), Bytes.toBytes("a"));*/

        Put put11 = new Put(Bytes.toBytes(rowKey));
        put11.addColumn(Bytes.toBytes("z1"), Bytes.toBytes("c1"),timeStamp,Bytes.toBytes("\ta"));
        Put put12 = new Put(Bytes.toBytes(rowKey));
        put12.addColumn(Bytes.toBytes("z1"), Bytes.toBytes("c2"), timeStamp,Bytes.toBytes("a"));
        Put put13 = new Put(Bytes.toBytes(rowKey));
        put13.addColumn(Bytes.toBytes("z1"), Bytes.toBytes("c3"), timeStamp,Bytes.toBytes("\\b"));
        Put put21 = new Put(Bytes.toBytes(rowKey));
        put21.addColumn(Bytes.toBytes("z2"), Bytes.toBytes("c1"), timeStamp,Bytes.toBytes("\nc"));
        Put put22 = new Put(Bytes.toBytes(rowKey));
        put22.addColumn(Bytes.toBytes("z2"), Bytes.toBytes("c2"), timeStamp,Bytes.toBytes("a"));
        Put put23 = new Put(Bytes.toBytes(rowKey));
        put23.addColumn(Bytes.toBytes("z2"), Bytes.toBytes("c3"), timeStamp,Bytes.toBytes("a"));
        Put put31 = new Put(Bytes.toBytes(rowKey));
        put31.addColumn(Bytes.toBytes("z3"), Bytes.toBytes("c1"), timeStamp,Bytes.toBytes("a"));
        Put put32 = new Put(Bytes.toBytes(rowKey));
        put32.addColumn(Bytes.toBytes("z3"), Bytes.toBytes("c2"), timeStamp,Bytes.toBytes("a"));
        Put put33 = new Put(Bytes.toBytes(rowKey));
        put33.addColumn(Bytes.toBytes("z3"), Bytes.toBytes("c3"), timeStamp,Bytes.toBytes("a"));
        RowMutations rowMutations = new RowMutations(Bytes.toBytes(rowKey));
        rowMutations.add(put11);
        rowMutations.add(put12);
        rowMutations.add(put13);
        rowMutations.add(put21);
        rowMutations.add(put22);
        rowMutations.add(put23);
        rowMutations.add(put31);
        rowMutations.add(put32);
        rowMutations.add(put33);
        table.mutateRow(rowMutations);
        table.close();
    }

    @Test
    public void deleteDate() throws IOException {
        Table table = connection.getTable(TableName.valueOf("table4"));
        Delete delete = new Delete(Bytes.toBytes("row2"));
//        delete.addFamily(Bytes.toBytes("z1"));
//        delete.addFamily(Bytes.toBytes("z1"),5L);
//        delete.addColumns(Bytes.toBytes("z1"),Bytes.toBytes("1c1"));
        delete.addColumns(Bytes.toBytes("z2"),Bytes.toBytes("c1"),6);
//        delete.addColumn(Bytes.toBytes("z2"),Bytes.toBytes("c1"),6);
//        delete.addColumn(Bytes.toBytes("z1"),Bytes.toBytes("1c3"));
        table.delete(delete);
        table.close();
    }
}